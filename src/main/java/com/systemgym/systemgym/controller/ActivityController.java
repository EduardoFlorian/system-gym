package com.systemgym.systemgym.controller;

import com.systemgym.systemgym.dto.request.CreateActivityDTO;
import com.systemgym.systemgym.dto.request.UpdateActivityDTO;
import com.systemgym.systemgym.dto.response.ResponseActivityDTO;
import com.systemgym.systemgym.service.IActivityService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    private final IActivityService iActivityService;

    public ActivityController(IActivityService iActivityService) {
        this.iActivityService = iActivityService;
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseActivityDTO> created (@Valid @RequestBody CreateActivityDTO createActivityDTO) throws Exception {

        return ResponseEntity.status(HttpStatus.CREATED).body(iActivityService.saveActivity(createActivityDTO));
    }
}
