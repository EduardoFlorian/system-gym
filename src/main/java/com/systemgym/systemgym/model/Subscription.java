package com.systemgym.systemgym.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusSubscription statusSubscription;

    @ManyToOne
    @JoinColumn(name = "id_partner", nullable = false, foreignKey = @ForeignKey(name = "fk_subscription_partner"))
    private Partner partner;

    @ManyToOne
    @JoinColumn(name = "id_plan", nullable = false, foreignKey = @ForeignKey(name = "fk_subscription_plan"))
    private Plan plan;

}
