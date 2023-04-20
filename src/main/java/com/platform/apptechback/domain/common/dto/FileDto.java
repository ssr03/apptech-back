package com.platform.apptechback.domain.common.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FileDto {
    String fileName;
    String storedName;
    String ext;
    Long fileAmount;
}
