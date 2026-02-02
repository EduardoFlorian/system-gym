package com.systemgym.systemgym.controller;

import com.systemgym.systemgym.dto.request.CreateInscriptionDTO;
import com.systemgym.systemgym.dto.response.ResponseInscriptionDTO;
import com.systemgym.systemgym.service.IInscriptionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inscriptions")
public class InscriptionController {

    private final IInscriptionService inscriptionService;

    public InscriptionController(IInscriptionService inscriptionService) {
        this.inscriptionService = inscriptionService;
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseInscriptionDTO> save(@Valid @RequestBody CreateInscriptionDTO createInscriptionDTO) throws Exception {

        return ResponseEntity.status(HttpStatus.CREATED).body(inscriptionService.saveInscription(createInscriptionDTO));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ResponseInscriptionDTO> findById(@PathVariable Integer id) throws Exception {

        return ResponseEntity.ok().body(inscriptionService.findInscriptionById(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ResponseInscriptionDTO>> listInscriptions() throws  Exception {
        return ResponseEntity.ok(inscriptionService.findAllInscriptions());
    }

}
