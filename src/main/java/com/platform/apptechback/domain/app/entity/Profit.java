package com.platform.apptechback.domain.app.entity;

import com.platform.apptechback.domain.app.dto.ProfitRequest;
import com.platform.apptechback.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "apptech_profit")
public class Profit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne
    @JoinColumn(name = "app_id", referencedColumnName = "id")
    private App app;
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
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Profit(){

    }
    public Profit(User user, App app, String profitName, String profitDesc, Long orderNo, boolean quizYn
            , LocalDateTime startDate, LocalDateTime endDate, String adminStatus){
        this.user = user;
        this.app = app;
        this.profitName = profitName;
        this.profitDesc = profitDesc;
        this.orderNo = orderNo;
        this.quizYn = quizYn;
        this.startDate = startDate;
        this.endDate = endDate;
        this.adminStatus = adminStatus;
    }

    public void newProfit(User user, App app, ProfitRequest profitRequest){
        this.user = user;
        this.app = app;
        this.profitName = profitRequest.getProfitName();
        this.profitDesc = profitRequest.getProfitDesc();
        this.orderNo = profitRequest.getOrderNo();
        this.quizYn = profitRequest.isQuizYn();
        this.startDate = profitRequest.getStartDate();
        this.endDate = profitRequest.getEndDate();
        this.adminStatus = "WAITING";
    }
}
