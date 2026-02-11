package com.systemgym.systemgym.dto.request;

import java.util.List;

public record CreateInscriptionDTO (

        Integer idPartner,

        List<CreateInscriptionDetailDTO> details

) {
}
