package com.kazyonplus.files.controller;

import com.kazyonplus.files.model.Doc;
import com.kazyonplus.files.service.DocStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class DocController {

	@Autowired 
	private DocStorageService docStorageService;
	
	@GetMapping("/")
	public String get(Model model) {
		List<Doc> docs = docStorageService.getFiles();
		model.addAttribute("docs", docs);
		return "doc";
	}
	
	@PostMapping("/uploadFiles")
	public String uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
		for (MultipartFile file: files) {
			docStorageService.saveFile(file);
			
		}
		return "redirect:/";
	}
	@GetMapping("/downloadFile/{id}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable ("id") int id){
		/*int id =0;
		List<Doc> docs = docStorageService.getFiles();
		for (int i=0;i<docs.size();i++)
		{
			if (docs.get(i).getCategory().equals(category))
			{
				if (docs.get(i).getCategoryId()==categoryId)
					id = docs.get(i).getId();

			}
		}
*/
		Doc doc = docStorageService.getFile(id).get();
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(doc.getDocType()))
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+doc.getDocName()+"\"")
				.body(new ByteArrayResource(doc.getData()));
	}
	
}
