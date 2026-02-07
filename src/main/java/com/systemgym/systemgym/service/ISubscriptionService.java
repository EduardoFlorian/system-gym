package com.systemgym.systemgym.service;

import com.systemgym.systemgym.dto.request.CreateSubscriptionDTO;
import com.systemgym.systemgym.dto.response.ResponseSubscriptionDTO;
import com.systemgym.systemgym.model.Subscription;

import java.util.List;

public interface ISubscriptionService {

    ResponseSubscriptionDTO saveSubscription(CreateSubscriptionDTO createSubscriptionDTO) throws Exception;
    ResponseSubscriptionDTO getSubscriptionById(Integer id) throws Exception;
    List<ResponseSubscriptionDTO> findAllSubscriptions() throws Exception;
    void cancelSubscription(Integer idPartner,Integer idSubscription) throws Exception;
}
