package com.systemgym.systemgym.task;

import com.systemgym.systemgym.model.StatusSubscription;
import com.systemgym.systemgym.model.Subscription;
import com.systemgym.systemgym.repository.ISubscriptionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class SubscriptionTask {

    private final ISubscriptionRepository subscriptionRepository;

    public SubscriptionTask(ISubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    //Funcion para pasar a Expirada una suscripcion vencida y a la ves pasar a inactive el estado del socio.

    @Scheduled(cron = "0 0 0 * * *") //- Se ejecuta todos los días a la medianoche "0 0 0 * * *" significa: segundo 0, minuto 0, hora 0
    //@Scheduled(fixedRate = 5000) //Se ejecuta cada 5 segundos (Para pruebas)
    @Transactional
    public void expiredSubscriptions() {

        log.info("Iniciando proceso de expiración de suscripciones...");

        //1. Buscamos las suscripciones "ACTIVAS" que su fecha de expiracion sea menor a la fecha actual, asi identificamos que deben vencer
        List<Subscription> affectedSubscriptions = subscriptionRepository.findAllByStatusSubscriptionAndEndDateBefore(StatusSubscription.ACTIVE, LocalDateTime.now());

        //2. Si existen registros en el listado, realizar acciones. Sino dejar intacto los registros
        if (!affectedSubscriptions.isEmpty()) {

            affectedSubscriptions.stream().forEach(e -> {
                e.setStatusSubscription(StatusSubscription.EXPIRED);
                e.getPartner().setActive(false);
            });

        }
        // 3. Guardar cambios
        subscriptionRepository.saveAll(affectedSubscriptions);
        log.info("Se han expirado {} suscripciones.", affectedSubscriptions.size());

    }

}
