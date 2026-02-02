package com.systemgym.systemgym.controller;

import com.systemgym.systemgym.dto.request.CreatePlanDTO;
import com.systemgym.systemgym.dto.request.UpdatePlanDTO;
import com.systemgym.systemgym.dto.response.ResponsePlanDTO;
import com.systemgym.systemgym.service.IPlanService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/plans")
public class PlanController {

    private final IPlanService iPlanService;

    public PlanController(IPlanService iPlanService) {
        this.iPlanService = iPlanService;
    }

    @PostMapping("/save")
    public ResponseEntity<ResponsePlanDTO> save(@Valid @RequestBody CreatePlanDTO createPlanDTO) throws Exception {

        return ResponseEntity.status(HttpStatus.CREATED).body(iPlanService.savePlan(createPlanDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponsePlanDTO> update(@PathVariable UUID id, @Valid @RequestBody UpdatePlanDTO updatePlanDTO) throws Exception {

        return ResponseEntity.status(HttpStatus.OK).body(iPlanService.updatePlan(id, updatePlanDTO));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ResponsePlanDTO> findByIdPlan(@PathVariable UUID id) throws Exception{

        return ResponseEntity.ok(iPlanService.findById(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ResponsePlanDTO>> listAll() throws Exception {

        return ResponseEntity.ok().body(iPlanService.findAll());
    }

}
