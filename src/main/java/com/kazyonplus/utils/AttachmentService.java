package com.kazyonplus.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import com.kazyonplus.CasesProcuration.model.Case;
import com.kazyonplus.CasesProcuration.model.Procuration;
import com.kazyonplus.CasesProcuration.repository.CaseRepository;
import com.kazyonplus.CasesProcuration.repository.ProcurationRepository;
import com.kazyonplus.contracts.model.Contract;
import com.kazyonplus.contracts.repository.ContractRepository;
import com.kazyonplus.labeltrademark.model.Label.Label;
import com.kazyonplus.labeltrademark.model.Trademark.Trademark;
import com.kazyonplus.labeltrademark.repository.LabelRepository;
import com.kazyonplus.labeltrademark.repository.TrademarkRepository;
import com.kazyonplus.licenses.model.Branch.Branch;
import com.kazyonplus.licenses.repository.BranchRepository;
import com.kazyonplus.warehouse.model.WarehouseLicense;
import com.kazyonplus.warehouse.repository.WarehouseRepository;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static com.kazyonplus.utils.SystemType.*;

import static java.nio.file.Path.*;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.lang.String.valueOf;

@Service
public class AttachmentService {
    private final File fileToRead = new File("D:\\uploads");
    private final Path root = fileToRead.toPath();

    //private final Path root = Paths.get("C:\\Users\\Perry\\Documents\\Kazyon\\uploads");

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    BranchRepository branchRepository;

    @Autowired
    ContractRepository contractRepository;

    @Autowired
    CaseRepository caseRepository;

    @Autowired
    ProcurationRepository procurationRepository;
    @Autowired
    LabelRepository labelRepository;

    @Autowired
    TrademarkRepository trademarkRepository;

    @Autowired
    WarehouseRepository warehouseRepository;
    public void init() {
        try {
            Files.createDirectory(root);
            for(SystemType type: SystemType.values()) {
                Files.createDirectory(  of(root + "/" + type));
                Files.createDirectory(of(root + "/" + type + "/temp"));
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String save(MultipartFile[] files, Long id, SystemType type) throws IOException {
        final Path cur = Paths.get(root + "/" + type + "/temp");
        List<String> fileNames= new LinkedList<>();
        for (MultipartFile file : Arrays.asList(files)) {
            try {
                Files.copy(file.getInputStream(), cur.resolve(file.getOriginalFilename()), REPLACE_EXISTING);
                fileNames.add(file.getOriginalFilename());
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return archive(id, type);
    }
    private List<String> getAllFileNames(SystemType type){
        File f = new File(root + "/" + type + "/temp");
        List<String> fileNames = new LinkedList<>();
        for(String fileName: f.list()){
            fileNames.add(fileName);
        }
        return fileNames;
    }
    private String archive(Long id, SystemType type) throws IOException {
        String zipName = valueOf(id);

        FileOutputStream fos = new FileOutputStream(root + "/" + type + "/" + zipName+".zip");
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        for (String srcFile : getAllFileNames(type)) {
            File fileToZip = new File(root +"/" + type +"/temp/" + srcFile);
            FileInputStream fis = new FileInputStream(fileToZip);
            ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
            zipOut.putNextEntry(zipEntry);
            byte[] bytes = new byte[1024];
            int length;
            while((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
            fis.close();
        }
        zipOut.close();
        fos.close();
        FileUtils.cleanDirectory(new File(root + "/" + type + "/temp"));
        toggleEntityHasAttachment(id, type);
        return zipName;
    }
    private void toggleEntityHasAttachment(Long id, SystemType type) {
        if (type == contracts) {
            Contract contract = contractRepository.findById(id).get();
            contract.setHasAttachment(true);
            contractRepository.save(contract);
        } else if (type == cases) {
            Case _case = caseRepository.findById(id).get();
            _case.setHasAttachment(true);
            caseRepository.save(_case);
        } else if (type == procurations) {
            Procuration procuration = procurationRepository.findById(id).get();
            procuration.setHasAttachment(true);
            procurationRepository.save(procuration);
        } else if (type == labels){
            Label label = labelRepository.findById(id).get();
            label.setHasAttachment(true);
            labelRepository.save(label);
        } else if (type == trademarks){
            Trademark trademark = trademarkRepository.findById(id).get();
            trademark.setHasAttachment(true);
            trademarkRepository.save(trademark);
        } else if(type == workingLicense){
            Branch branch = branchRepository.findById(id).get();
            branch.setHasWorkingLicense(true);
            branchRepository.save(branch);
        }  else if(type == advertisementLicense){
            Branch branch = branchRepository.findById(id).get();
            branch.setHasAdvertisementLicense(true);
            branchRepository.save(branch);
        } else if(type == warehouseContract){
            WarehouseLicense warehouseLicense = warehouseRepository.findById(id).get();
            warehouseLicense.setHasContractAttachment(true);
            warehouseRepository.save(warehouseLicense);
        } else if(type == warehouseLicense){
            WarehouseLicense warehouseLicense = warehouseRepository.findById(id).get();
            warehouseLicense.setHasLicenseAttachment(true);
            warehouseRepository.save(warehouseLicense);
        }  else if(type == warehouseExternalApproval){
            WarehouseLicense warehouseLicense = warehouseRepository.findById(id).get();
            warehouseLicense.setHasExternalApprovalAttachment(true);
            warehouseRepository.save(warehouseLicense);
        }
    }
    public Resource loadFileAsResource(String fileName, SystemType type) throws MalformedURLException {
        Path filePath = root.resolve(type + "/" + fileName+".zip").normalize();
        Resource resource = new UrlResource(filePath.toUri());
        return resource;
    }
    public String appendFileToZip(MultipartFile[] file, Long id, SystemType type) throws IOException {
        unZip(id, type);
        return save(file, id, type);
    }
    private void unZip(Long id, SystemType type) throws IOException {
        String fileZip = root + "/" + type + "/" + id + ".zip";
        File destDir = new File(root +"/" + type + "/temp");
        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip));
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null) {
            File newFile = new File(destDir, String.valueOf(zipEntry));
            if (zipEntry.isDirectory()) {
                if (!newFile.isDirectory() && !newFile.mkdirs()) {
                    throw new IOException("Failed to create directory " + newFile);
                }
            } else {
                // fix for Windows-created archives
                File parent = newFile.getParentFile();
                if (!parent.isDirectory() && !parent.mkdirs()) {
                    throw new IOException("Failed to create directory " + parent);
                }

                // write file content
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
            }
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
    }
}
