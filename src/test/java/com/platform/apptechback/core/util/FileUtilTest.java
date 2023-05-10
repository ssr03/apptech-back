package com.platform.apptechback.core.util;

import com.platform.apptechback.domain.common.dto.FileDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FileUtilTest {

    @Autowired
    private FileUtil fileUtil;

    @Test
    void uploadFile() {
        String fileName = "test.jpg";
        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "test-file",
                fileName,
                "image/jpeg",
                "file content".getBytes()
        );
        String prefix = "/profit/test";

        FileDto result = fileUtil.uploadFile(prefix, mockMultipartFile);
        Path resultPath = Path.of(result.getStoredName());
        assertEquals(fileName, result.getFileName());
        assertEquals(Path.of(prefix), resultPath.getParent());
    }
}