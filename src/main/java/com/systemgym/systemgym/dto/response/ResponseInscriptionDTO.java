package com.systemgym.systemgym.dto.response;

import com.systemgym.systemgym.model.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public record ResponseInscriptionDTO(

         Integer id,
         LocalDateTime dateRegistration,
         ResponseActivityDTO activity,
         ResponsePartnerDTO partner,
         StatusInscription status
        
) {
    public static ResponseInscriptionDTO fromEntity(Inscription inscription){
        if (inscription == null) return null;
        return new ResponseInscriptionDTO(
                inscription.getId(),
                inscription.getDateRegistration(),
                ResponseActivityDTO.fromEntity(inscription.getActivity()),
                ResponsePartnerDTO.fromEntity(inscription.getPartner()),
                inscription.getStatus()
        );
    }
}
