package com.platform.apptechback.core.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ImageUtilTest {

    @Autowired
    private ImageUtil imageUtil;

    @Test
    void getLogoImage() {
        ResponseEntity<Resource> result = imageUtil.getLogoImage(1L);

        assertNotNull(result);
    }
}