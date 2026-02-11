package com.systemgym.systemgym.dto.response;

import com.systemgym.systemgym.model.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

public record ResponseInscriptionDTO(

        Integer id,
        LocalDateTime dateRegistration,
        ResponsePartnerDTO partner,
        StatusInscription status,
        List<ResponseInscriptionDetailDTO> inscriptionDetails

) {

    public static ResponseInscriptionDTO fromEntity(Inscription inscription){
        if (inscription == null) return null;
        return new ResponseInscriptionDTO(
                inscription.getId(),
                inscription.getDateRegistration(),
                ResponsePartnerDTO.fromEntity(inscription.getPartner()),
                inscription.getStatus(),
                inscription.getInscriptionDetails().stream().map(e-> ResponseInscriptionDetailDTO.fromEntity(e)).toList()
        );
    }

}
