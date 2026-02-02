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
public class Inscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDateTime dateRegistration;

    @ManyToOne
    @JoinColumn(name = "id_activity", nullable = false, foreignKey = @ForeignKey(name = "fk_inscription_activity"))
    private Activity activity;

    @ManyToOne
    @JoinColumn(name = "id_partner", nullable = false, foreignKey = @ForeignKey(name = "fk_inscription_partner"))
    private Partner partner;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusInscription status;

}
