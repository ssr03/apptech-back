package com.platform.apptechback.domain.app.entity;

import com.platform.apptechback.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "apptech_profit_favorite")
public class ProfitFavorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "app_profit_id", referencedColumnName = "id")
    private Profit profit;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public ProfitFavorite(){

    }
    public ProfitFavorite(Profit profit, User user){
        this.profit = profit;
        this.user = user;
    }
}
