package com.platform.apptechback.domain.app.entity;


import com.platform.apptechback.domain.app.dto.ProfitResponse;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "apptech_profit")
public class Profit {
    @Id
    private Long id;

    @Column(name = "app_id")
    private Long appId;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "profit_name")
    private String profitName;
    @Column(name = "profit_desc")
    private String profitDesc;
    @Column(name = "order_no")
    private Long orderNo;
    @Column(name = "quiz_yn")
    private boolean quizYn;
    @Column(name = "start_date")
    private LocalDateTime startDate;
    @Column(name = "end_date")
    private LocalDateTime endDate;
    @Column(name = "admin_status")
    private String adminStatus;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    public ProfitResponse getProfitResponse(){
        return new ProfitResponse(id, appId, userId, profitName, profitDesc, orderNo, quizYn,
                startDate, endDate, adminStatus);
    }
}
