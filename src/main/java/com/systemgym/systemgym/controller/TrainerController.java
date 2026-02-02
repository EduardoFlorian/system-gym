package com.systemgym.systemgym.controller;

import com.systemgym.systemgym.dto.request.CreateTrainerDTO;
import com.systemgym.systemgym.dto.request.UpdateTrainerDTO;
import com.systemgym.systemgym.dto.response.ResponseTrainerDTO;
import com.systemgym.systemgym.service.ITrainerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainers")
public class TrainerController {

    private final ITrainerService iTrainerService;

    public TrainerController(ITrainerService iTrainerService) {
        this.iTrainerService = iTrainerService;
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseTrainerDTO> saveTrainer(@Valid @RequestBody CreateTrainerDTO createTrainerDTO) throws Exception {

        ResponseTrainerDTO obj = iTrainerService.saveTrainer(createTrainerDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(obj);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseTrainerDTO> updateTrainer(@PathVariable Integer id, @Valid @RequestBody UpdateTrainerDTO updateTrainerDTO) throws Exception {

        ResponseTrainerDTO obj = iTrainerService.updateTrainer(id, updateTrainerDTO);

        return ResponseEntity.status(HttpStatus.OK).body(obj);

    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ResponseTrainerDTO> find(@PathVariable Integer id) throws Exception {

        return ResponseEntity.ok(iTrainerService.findByIdTrainer(id));

    }

    @GetMapping("/list")
    public ResponseEntity<List<ResponseTrainerDTO>> findAllTrainers() throws Exception {

        return ResponseEntity.ok(iTrainerService.listTrainers());

    }
}
