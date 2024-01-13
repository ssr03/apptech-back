package com.platform.apptechback.domain.ranking.entity;

import com.platform.apptechback.domain.app.entity.App;
import com.platform.apptechback.domain.ranking.enums.RankType;
import com.platform.apptechback.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "apptech_rank")
public class Rank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "app_id", referencedColumnName = "id")
    private App app;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private RankType type;

    @Column(name = "ranking_no")
    private Long rankingNo;

    @Column(name = "profit_date")
    private LocalDate profitDate;
    @Column(name = "total_profit")
    private Long totalProfit;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
