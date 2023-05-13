package com.platform.apptechback.core.util;

import com.platform.apptechback.domain.app.entity.App;
import com.platform.apptechback.domain.app.repository.AppRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequiredArgsConstructor
@RestController
@RequestMapping("/image")
public class ImageUtil {

    private final AppRepository appRepository;
    private final Path fileStorageLocation;
    @GetMapping("/logo")
    public ResponseEntity<Resource> getLogoImage(@RequestParam Long appId) {

        App app = appRepository.findById(appId)
                            .orElseThrow(IllegalArgumentException::new);

        String appLogoFile = app.getAppLogoFile();

        String imageRoot = this.fileStorageLocation.toString() + "/appLogo/" + appLogoFile;

        Resource resource = new FileSystemResource(imageRoot);

        if(!resource.exists()){
            return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
        }

        HttpHeaders header = new HttpHeaders();
        Path filePath = null;
        try{
            filePath = Paths.get(imageRoot);
            header.add("Content-Type", Files.probeContentType(filePath));
        }catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
    }
}