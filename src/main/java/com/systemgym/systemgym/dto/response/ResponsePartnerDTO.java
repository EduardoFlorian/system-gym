package com.systemgym.systemgym.dto.response;

import com.systemgym.systemgym.model.Partner;

import java.time.LocalDateTime;

public record ResponsePartnerDTO(

        Integer id,

        String firstName,

        String lastName,

        String email,

        String phoneNumber,

        LocalDateTime registrationDate,

        boolean active

) {
    public static ResponsePartnerDTO fromEntity(Partner partner) {
        if (partner == null) return null;
        return new ResponsePartnerDTO(
                partner.getId(),
                partner.getFirstName(),
                partner.getLastName(),
                partner.getEmail(),
                partner.getPhoneNumber(),
                partner.getRegistrationDate(),
                partner.isActive()
        );
    }

}
