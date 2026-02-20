package com.systemgym.systemgym.repository;

import com.systemgym.systemgym.model.Inscription;
import com.systemgym.systemgym.model.InscriptionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IInscriptionDetailRepository extends JpaRepository<InscriptionDetail, Integer> {

    //Devuelve la lista de detalles de una inscripcion en particular
    List<InscriptionDetail> findByInscription(Inscription inscription);

    //Devuelve la cantidad de inscritos vigentes a una actividad en particular
    @Query("SELECT COUNT(idpt) FROM InscriptionDetail idpt " + // 1. Contamos los detalles
            "WHERE idpt.activity.id = :activityId " +           // 2. Que pertenezcan a esta actividad
            "AND idpt.inscription.status IN ('REGISTERED','INPROGRESS')")       // 3. Y que tengan inscripciones vigentes ¡Aquí está la clave!
    Integer countParticipantsByActivityId(@Param("activityId") Integer activityId);


}
