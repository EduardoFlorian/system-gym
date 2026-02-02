package com.systemgym.systemgym.controller;

import com.systemgym.systemgym.dto.request.CreateDurationDTO;
import com.systemgym.systemgym.dto.response.ResponseDurationDTO;
import com.systemgym.systemgym.service.IDurationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/durations")
public class DurationController {

    private final IDurationService durationService;

    public DurationController(IDurationService durationService) {
        this.durationService = durationService;
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseDurationDTO> saveDuration(@Valid @RequestBody CreateDurationDTO createDurationDTO) throws Exception{

        ResponseDurationDTO obj = durationService.saveDuration(createDurationDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(obj);

    }

    @GetMapping("/list")
    public ResponseEntity<List<ResponseDurationDTO>> findAll() throws Exception {

        return ResponseEntity.ok(durationService.findAllDuration());

    }
}
