package com.systemgym.systemgym.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InscriptionDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_inscription", nullable = false, foreignKey = @ForeignKey(name = "fk_inscriptiondetail_inscription"))
    private Inscription inscription;

    @ManyToOne
    @JoinColumn(name = "id_activity", nullable = false, foreignKey = @ForeignKey(name = "fk_inscriptiondetail_activity"))
    private Activity activity;

}
