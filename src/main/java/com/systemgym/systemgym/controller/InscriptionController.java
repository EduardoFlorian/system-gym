package com.systemgym.systemgym.controller;

import com.systemgym.systemgym.dto.request.CreateInscriptionDTO;
import com.systemgym.systemgym.dto.response.ResponseInscriptionDTO;
import com.systemgym.systemgym.model.Inscription;
import com.systemgym.systemgym.service.IInscriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inscriptions")
public class InscriptionController {

    private final IInscriptionService inscriptionService;

    public InscriptionController(IInscriptionService inscriptionService) {
        this.inscriptionService = inscriptionService;
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseInscriptionDTO> save(@RequestBody CreateInscriptionDTO createInscriptionDTO) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(inscriptionService.saveInscription(createInscriptionDTO));
    }
}
