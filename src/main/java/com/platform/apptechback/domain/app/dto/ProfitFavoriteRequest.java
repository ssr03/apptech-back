package com.platform.apptechback.domain.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfitFavoriteRequest {
    private long profitId;
    private long userId;
}
