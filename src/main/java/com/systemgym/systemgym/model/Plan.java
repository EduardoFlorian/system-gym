package com.systemgym.systemgym.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 80)
    private String description;

    @Column(nullable = false, columnDefinition = "decimal(6,2)")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "id_duration", nullable = false, foreignKey = @ForeignKey(name = "fk_plan_duration"))
    private Duration duration;

}
