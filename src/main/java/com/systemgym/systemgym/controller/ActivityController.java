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

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseActivityDTO>  updated (@PathVariable int id, @Valid @RequestBody UpdateActivityDTO updateActivityDTO) throws Exception {

        return ResponseEntity.status(HttpStatus.OK).body(iActivityService.updateActivity(id, updateActivityDTO));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ResponseActivityDTO> findById(@PathVariable int id) throws Exception{

        return ResponseEntity.ok(iActivityService.findActivityById(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ResponseActivityDTO>> listActivities() throws Exception{

        return ResponseEntity.ok(iActivityService.findAllActivities());
    }
}
