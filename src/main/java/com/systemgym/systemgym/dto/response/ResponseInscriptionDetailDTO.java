package com.systemgym.systemgym.dto.response;

import com.systemgym.systemgym.model.InscriptionDetail;

import java.util.List;

public record ResponseInscriptionDetailDTO(

        Integer idActivity,

        String descriptionActivity,

        List<ResponseScheduleDTO> schedules


) {

    public static ResponseInscriptionDetailDTO fromEntity(InscriptionDetail inscriptionDetail){
        if (inscriptionDetail == null) return null;
        return new ResponseInscriptionDetailDTO(
                inscriptionDetail.getActivity().getId(),
                inscriptionDetail.getActivity().getDescription(),
                inscriptionDetail.getActivity().getSchedules().stream().map(e->ResponseScheduleDTO.fromEntity(e)).toList()
        );
    }

}
