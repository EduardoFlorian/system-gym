package com.systemgym.systemgym.dto.response;

import com.systemgym.systemgym.model.Plan;
import com.systemgym.systemgym.model.StatusSubscription;
import com.systemgym.systemgym.model.Subscription;

import java.time.LocalDateTime;

public record ResponseSubscriptionDTO(

         Integer id,
         LocalDateTime startDate,
         LocalDateTime endDate,
         StatusSubscription statusSubscription,
         ResponsePartnerDTO partner,
         ResponsePlanDTO plan
        
) {

    public static ResponseSubscriptionDTO fromEntity(Subscription subscription) {
        if (subscription == null) return null;
        return new ResponseSubscriptionDTO(
                subscription.getId(),
                subscription.getStartDate(),
                subscription.getEndDate(),
                subscription.getStatusSubscription(),
                ResponsePartnerDTO.fromEntity(subscription.getPartner()),
                ResponsePlanDTO.fromEntity(subscription.getPlan())
        );
    }

}
