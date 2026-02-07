package com.systemgym.systemgym.controller;

import com.systemgym.systemgym.dto.request.CreateSubscriptionDTO;
import com.systemgym.systemgym.dto.response.ResponseSubscriptionDTO;
import com.systemgym.systemgym.service.ISubscriptionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private final ISubscriptionService subscriptionService;

    public SubscriptionController(ISubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseSubscriptionDTO> save(@Valid @RequestBody CreateSubscriptionDTO createSubscriptionDTO) throws Exception {

        return ResponseEntity.status(HttpStatus.CREATED).body(subscriptionService.saveSubscription(createSubscriptionDTO));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ResponseSubscriptionDTO> findById(@PathVariable Integer id) throws Exception {

        return ResponseEntity.ok().body(subscriptionService.getSubscriptionById(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ResponseSubscriptionDTO>> listAll() throws Exception {

        return ResponseEntity.ok().body(subscriptionService.findAllSubscriptions());
    }

    @PostMapping("/cancel")
    //Marcamos el required como false ya que el service de por si ya hace la validacion de q el parametro no sea nulo o menor a 1
    public void cancel(@RequestParam(value = "idPartner", required = false) Integer idPartner,@RequestParam(value = "idSubscription", required = false) Integer idSubscription) throws Exception {

        subscriptionService.cancelSubscription(idPartner,idSubscription);
    }

}
