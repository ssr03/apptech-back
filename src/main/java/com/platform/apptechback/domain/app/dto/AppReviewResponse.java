package com.platform.apptechback.domain.app.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppReviewResponse implements Serializable {
    private Long appId;
    private Long rate;
}
