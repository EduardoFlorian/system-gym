package com.systemgym.systemgym.service.implement;

import com.systemgym.systemgym.dto.request.CreatePlanDTO;
import com.systemgym.systemgym.dto.request.UpdatePlanDTO;
import com.systemgym.systemgym.dto.response.ResponsePlanDTO;
import com.systemgym.systemgym.mapper.PlanMapper;
import com.systemgym.systemgym.model.Duration;
import com.systemgym.systemgym.model.Partner;
import com.systemgym.systemgym.model.Plan;
import com.systemgym.systemgym.repository.IDurationRepository;
import com.systemgym.systemgym.repository.IPlanRepository;
import com.systemgym.systemgym.service.IPlanService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PlanServiceImpl implements IPlanService {

    private final IPlanRepository iPlanRepository;
    private final IDurationRepository iDurationRepository;
    private final PlanMapper planMapper;

    public PlanServiceImpl(IPlanRepository iPlanRepository, IDurationRepository iDurationRepository, PlanMapper planMapper) {
        this.iPlanRepository = iPlanRepository;
        this.iDurationRepository = iDurationRepository;
        this.planMapper = planMapper;
    }

    @Override
    public ResponsePlanDTO savePlan(CreatePlanDTO createPlanDTO) throws Exception {

        //1. Validar si es valido el id duration recibido
        if(createPlanDTO.idDuration()==null || createPlanDTO.idDuration()<=0){
            throw new Exception("El ID de duration no es válido");
        }

        //2. Verificar si el id duration existe en los registros de BD
        Duration objDuration = iDurationRepository.findById(createPlanDTO.idDuration()).orElseThrow(() -> new Exception("No se encontró duration con ID: " + createPlanDTO.idDuration()));

        //3. Convertir DTO a entidad el request recibido
        //Aqui un ocurre un punto excepcional con el campo idDuration del DTO, ya que este campo está registrado unicamente como un numero (Integer) dentro del DTO, sin embargo la entidad Plan para el campo duration espera un
        //objeto de tipo Duration, por lo cual no hay un match en el mapeo del modelmapper y esto hace que la entidad nazca con este campo en valor null, lo cual nos ocasionaria un error al momento de la inserción del registro
        Plan planEntity = planMapper.convertRequestToEntity(createPlanDTO);

        //4.Vincular la relación (Esto evita el error de null en la DB). Aqui si pasamos un objeto de tipo Duration al objeto ya antes creado
        planEntity.setDuration(objDuration);

        //5. Guardar objeto
        Plan savedPlan = iPlanRepository.save(planEntity);

        return planMapper.convertEntityToResponseDTO(savedPlan);
    }

    @Override
    public ResponsePlanDTO updatePlan(UUID id, UpdatePlanDTO updatePlanDTO) throws Exception {

        Plan obj = iPlanRepository.findById(id).orElseThrow(() -> new Exception("Plan not found"));

        obj.setPrice(updatePlanDTO.price());

        iPlanRepository.save(obj);

        return planMapper.convertEntityToResponseDTO(obj);

    }

    @Override
    public ResponsePlanDTO findById(UUID idPlan) throws Exception {
        Plan obj = iPlanRepository.findById(idPlan).orElseThrow(() -> new Exception("Plan not found"));

        return planMapper.convertEntityToResponseDTO(obj);
    }

    @Override
    public List<ResponsePlanDTO> findAll() throws Exception {

        List<Plan> plans = iPlanRepository.findAll();
        List<ResponsePlanDTO> responsePlanDTOS = plans.stream().map(e->planMapper.convertEntityToResponseDTO(e)).toList();

        return responsePlanDTOS;
    }

    //Metodo para poder retornar una entidad por id (Metodo de uso para otros servicios en casos de persistencia)
    @Override
    public Plan findByIdPlanEntity(UUID id) throws Exception {
        Plan objPlanEntity = iPlanRepository.findById(id).orElseThrow(() -> new Exception("Plan not found"));
        return objPlanEntity;
    }

}
