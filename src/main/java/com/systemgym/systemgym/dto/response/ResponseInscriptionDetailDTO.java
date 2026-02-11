package com.systemgym.systemgym.dto.response;

import com.systemgym.systemgym.model.InscriptionDetail;

public record ResponseInscriptionDetailDTO(

        Integer idActivity,

        String descriptionActivity,

        String scheduleActivity


) {

    public static ResponseInscriptionDetailDTO fromEntity(InscriptionDetail inscriptionDetail){
        if (inscriptionDetail == null) return null;
        return new ResponseInscriptionDetailDTO(
                inscriptionDetail.getActivity().getId(),
                inscriptionDetail.getActivity().getDescription(),
                inscriptionDetail.getActivity().getSchedule()
        );
    }

}
