package com.platform.apptechback.domain.ranking.entity;

import com.platform.apptechback.domain.app.entity.App;
import com.platform.apptechback.domain.ranking.dto.UserProfitRequest;
import com.platform.apptechback.domain.user.entity.User;
import com.platform.apptechback.domain.ranking.enums.AdminStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "apptech_user_profit")
public class UserProfit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "app_id", referencedColumnName = "id")
    private App app;

    @Column(name = "profit_date")
    private LocalDate profitDate;
    @Column(name = "profit")
    private Long profit;
    @Column(name = "profit_image_file")
    private String profitImageFile;
    @Column(name = "admin_status")
    private AdminStatus adminStatus;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public UserProfit(User user, LocalDate profitDate, Long profit, String profitImageFile, AdminStatus adminStatus){
        this.user = user;
        this.profitDate = profitDate;
        this.profit = profit;
        this.profitImageFile = profitImageFile;
        this.adminStatus = adminStatus;
    }

    public void newUserProfit(User user, App app, UserProfitRequest userProfitRequest, String profitImagePath){
        this.user = user;
        this.app = app;
        this.profitDate = LocalDate.parse(userProfitRequest.getProfitDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.profit = userProfitRequest.getProfit();
        this.profitImageFile = profitImagePath;
        this.adminStatus = AdminStatus.Y;
    }

    public void modifyUserProfit(UserProfitRequest userProfitRequest, String profitImagePath){
        this.profitDate = LocalDate.parse(userProfitRequest.getProfitDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.profit = userProfitRequest.getProfit();
        this.profitImageFile = profitImagePath;
    }
}
