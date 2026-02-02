package com.systemgym.systemgym.controller;

import com.systemgym.systemgym.dto.request.CreatePartnerDTO;
import com.systemgym.systemgym.dto.request.UpdatePartnerDTO;
import com.systemgym.systemgym.dto.response.ResponsePartnerDTO;
import com.systemgym.systemgym.service.IPartnerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/partners")
public class PartnerController {

    private final IPartnerService iPartnerService;

    public PartnerController(IPartnerService iPartnerService) {
        this.iPartnerService = iPartnerService;
    }

    @PostMapping("/save")
    public ResponseEntity<ResponsePartnerDTO> savePartner(@Valid @RequestBody CreatePartnerDTO request) throws Exception{

        ResponsePartnerDTO obj = iPartnerService.savePartner(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponsePartnerDTO>  updatePartner(@PathVariable Integer id, @Valid @RequestBody UpdatePartnerDTO request) throws Exception{

        ResponsePartnerDTO obj = iPartnerService.updatePartner(id, request);

        return ResponseEntity.status(HttpStatus.OK).body(obj);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ResponsePartnerDTO>> findAllPartners() throws Exception {

        return ResponseEntity.ok( iPartnerService.listPartners());

    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ResponsePartnerDTO> findByIdPartner (@PathVariable Integer id) throws Exception{

        return ResponseEntity.ok(iPartnerService.findByIdPartner(id));

    }
}
