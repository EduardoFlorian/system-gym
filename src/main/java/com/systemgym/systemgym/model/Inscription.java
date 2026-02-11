package com.systemgym.systemgym.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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
    @JoinColumn(name = "id_partner", nullable = false, foreignKey = @ForeignKey(name = "fk_inscription_partner"))
    private Partner partner;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusInscription status;

    //Relacion bidireccional. Es decir cuenta con un OneToMany aqui y un ManyToOne en inscriptiondetail.
    //Esta anotacion no crea una columna en BD ya que solo es de uso logico. El ManyToOne si lo hace pq gestiona la creacion de la FK
    @OneToMany(mappedBy = "inscription", cascade = CascadeType.ALL) //En el mappedBy debe ir el nombre de la variable Sale que esta en SaleDetail
    private List<InscriptionDetail> inscriptionDetails;

}
