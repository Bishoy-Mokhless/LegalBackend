package com.kazyonplus.CasesProcuration.controller;

import com.kazyonplus.CasesProcuration.model.Case;
import com.kazyonplus.CasesProcuration.model.request.CaseRequest;
import com.kazyonplus.CasesProcuration.repository.CaseRepository;
import com.kazyonplus.CasesProcuration.service.CaseService;
import com.kazyonplus.CasesProcuration.service.SessionService;
import com.kazyonplus.contracts.model.Contract;
import com.kazyonplus.files.model.Doc;
import com.kazyonplus.files.repository.DocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.status;


@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins ={"http://localhost:4200/", "https://legalsystem.netlify.app/"
        , "http://localhost:80","http://localhost"} ,
        methods = {RequestMethod.GET,RequestMethod.DELETE,
                RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS,
                RequestMethod.POST},
        allowedHeaders = {"*"})
@RequestMapping("/case")
public class CaseController {

    public static final String DIRECTORY = System.getProperty("user.home") + "/Downloads/uploads/";

    private final CaseService caseService;
    private final SessionService sessionService ;
    private final CaseRepository caseRepository;
    @Autowired
    DocRepository docRepository;

    @Autowired
    public CaseController(CaseService caseService, SessionService sessionService,
                          CaseRepository caseRepository) {
        this.caseService = caseService;
        this.sessionService = sessionService;
        this.caseRepository = caseRepository;
    }

    //Tested
    @PostMapping
    public ResponseEntity<CaseRequest> AddCase(@RequestBody final CaseRequest caseDTO){
        Case mycase = caseService.addCase(Case.from(caseDTO));
        return new ResponseEntity<>(CaseRequest.from(mycase), OK);
    }
    //Tested
    @GetMapping
    public ResponseEntity<List<CaseRequest>> GetCases(){
        List<Case> myCases = caseService.getCases();
        List<CaseRequest> caseDTOS = myCases.stream().map(CaseRequest::from).collect(Collectors.toList());
        return new ResponseEntity<>(caseDTOS, OK);
    }
    //Tested
    @GetMapping(value = "/{id}")
    public ResponseEntity<CaseRequest> GetCaseByID(@PathVariable final Long id){
        Case mycase = caseService.getCaseById(id) ;
        return new ResponseEntity<>(CaseRequest.from(mycase), OK);
    }


    //Tested
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CaseRequest> DeleteCaseByID(@PathVariable final Long id){
        Case mycase = caseService.deleteCaseByID(id);
        return new ResponseEntity<>(CaseRequest.from(mycase), OK);
    }
    //tested
    @PostMapping(value = "/{id}")
    public ResponseEntity<CaseRequest> EditCaseByID(@PathVariable  long id,
                                                    @RequestBody  CaseRequest caseDTO){
        Case mycase = caseService.editCase(id, Case.from(caseDTO));
        return new ResponseEntity<>(CaseRequest.from(mycase), OK);
    }

    //Tested
    @GetMapping(value = "/get-name")
    public ResponseEntity<List<Case>> GetCaseByName(
            @RequestParam String name) {

        List<Case> mycases = caseService.getCaseByName(name);
        List<CaseRequest> caseDTOS = mycases.stream().map(CaseRequest::from).collect(Collectors.toList());
        return new ResponseEntity<>(mycases, OK);
    }


    @PostMapping(value = "file/upload")
    public ResponseEntity<Object> fileUpload(@RequestParam("File") MultipartFile file) throws IOException{
        File myFile = new File(DIRECTORY+file.getOriginalFilename());
        myFile.createNewFile();
        FileOutputStream fos =new FileOutputStream(myFile);
        fos.write(file.getBytes());
        fos.close();
        return new ResponseEntity<Object>("The File Uploaded Successfully", OK);
    }



//    @GetMapping(value = "file/download/{filename}")
//    public ResponseEntity<Resource> downloadFiles(@PathVariable("filename") String filename) throws IOException {
//        Path filePath = get(DIRECTORY).toAbsolutePath().normalize().resolve(filename);
//        if(!Files.exists(filePath)) {
//            throw new FileNotFoundException(filename + " was not found on the server");
//        }
//        Resource resource = (Resource) new UrlResource(filePath.toUri());
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("File-Name", filename);
//        httpHeaders.add(CONTENT_DISPOSITION, "attachment;File-Name=");
//        return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
//                .headers(httpHeaders).body(resource);
//    }







    @DeleteMapping(value = "/{caseId}/session/{sessionId}/remove")
    public ResponseEntity<CaseRequest> RemoveSessionFromCase(@PathVariable final Long caseId,
                                                             @PathVariable final Long sessionId){
        Case mycase = caseService.removeSessionFromCase(caseId,sessionId) ;
        return new ResponseEntity<>(CaseRequest.from(mycase), OK);
    }
    @PostMapping("/addFile/{id}")
    public ResponseEntity<String> updateContractFile(@PathVariable("id") long id, @RequestParam("files") MultipartFile files) throws IOException {

        return status(OK).body(caseService.addFile(id,files));
    }
    @GetMapping("/downloadFile/{id}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable ("id") int id) {
        Case _case = caseRepository.getById((long) id);
        int idd = _case.getAttachment().getId();
        Doc doc = docRepository.getById(idd);
//get file from repo
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(doc.getDocType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=newfile.pdf")
                .body(new ByteArrayResource(doc.getData()));
    }
}