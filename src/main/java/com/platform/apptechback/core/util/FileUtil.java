package com.platform.apptechback.core.util;

import com.platform.apptechback.core.exception.ErrorCode;
import com.platform.apptechback.core.exception.FileException;
import com.platform.apptechback.domain.common.dto.FileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class FileUtil {
    private final Path fileStorageLocation;

    public Resource loadFileAsResource(String fileName) throws FileNotFoundException {
        Path filePath = fileStorageLocation.resolve(fileName).normalize();

        try {
            Resource resource = new UrlResource(filePath.toUri());

            if(!resource.exists()){
                throw new FileNotFoundException(fileName + " 을 찾을 수 없습니다.");
            }

            return resource;

        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new FileNotFoundException(fileName+ " 을 찾을 수 없습니다.");
        }
    }

    /**
     * 파일업로드
     * @param prefix root folder
     * @param file
     */
    public FileDto uploadFile(String prefix, MultipartFile file) {
        // 테스트 코드 만들기
        if (!prefix.endsWith("/") ) {
            prefix = prefix + "/";
        }
        if (!prefix.startsWith("/")){
            prefix = "/" + prefix;
        }
        Path prefixLocation = Path.of(this.fileStorageLocation.toString() + prefix);
        try {
            Files.createDirectories(prefixLocation);
        } catch (IOException e) {
            throw new FileException(ErrorCode.FILE_ERROR, prefixLocation + "파일 경로를 생성할 수 없습니다. 다시 시도해 보세요.");
        }
        FileDto fileDto = copyFile(prefixLocation,file);
        fileDto.updateStoreName(prefix);
        return fileDto;
    }


    /**
     * 파일업로드
     * @param file
     */
    public FileDto uploadFile(MultipartFile file) {
        return copyFile(this.fileStorageLocation, file);
    }

    private FileDto copyFile(Path fileLocation, MultipartFile file){
        String uploadFileName = file.getOriginalFilename();
        System.out.println(uploadFileName);
        //IE has file path
        uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(uploadFileName));
        String ext = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
        Long fileSize = file.getSize();

        UUID uuid = UUID.randomUUID();
        String storedName = uuid + ext;

        if(fileName.contains("..")) {
            throw new FileException(ErrorCode.FILE_ERROR,"파일명에 허용되지 않는 문자가 포함되어 있습니다." + fileName);
        }

        /*
         * File IO on Server
         * */
        Path targetLocation = fileLocation.resolve(storedName);
        try{
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileException(ErrorCode.FILE_ERROR, "파일 " + fileName + "을 저장할 수 없습니다. 다시 시도해 보세요.");
        }

        return new FileDto(fileName, storedName, ext, fileSize);
    }

    /**
     * 파일업로드
     * @param files
     */
    public List<FileDto> uploadFiles(MultipartFile[] files) {
        List<FileDto> list = new ArrayList<>();

        for(MultipartFile file: files){
            FileDto fileDto = uploadFile(file);
            list.add(fileDto);
        }

        return list;
    }
}
