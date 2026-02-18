package com.systemgym.systemgym.service.implement;

import com.systemgym.systemgym.dto.request.CreateTrainerDTO;
import com.systemgym.systemgym.dto.request.UpdateTrainerDTO;
import com.systemgym.systemgym.dto.response.ResponseTrainerDTO;
import com.systemgym.systemgym.exception.ResourceNotFoundException;
import com.systemgym.systemgym.mapper.TrainerMapper;
import com.systemgym.systemgym.model.Trainer;
import com.systemgym.systemgym.repository.ITrainerRepository;
import com.systemgym.systemgym.service.ITrainerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerServiceImpl implements ITrainerService {

    private final TrainerMapper trainerMapper;
    private final ITrainerRepository itrainerRepository;

    public TrainerServiceImpl(TrainerMapper trainerMapper, ITrainerRepository itrainerRepository) {
        this.trainerMapper = trainerMapper;
        this.itrainerRepository = itrainerRepository;
    }

    @Override
    public ResponseTrainerDTO saveTrainer(CreateTrainerDTO createTrainerDTO) throws Exception {

        Trainer trainerEntity = itrainerRepository.save(trainerMapper.convertRequestToEntity(createTrainerDTO));

        return trainerMapper.convertEntityToResponseDto(trainerEntity);

    }

    @Override
    public ResponseTrainerDTO findByIdTrainer(Integer id) throws Exception {

        Trainer trainerEntity = itrainerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Trainer not found"));

        return trainerMapper.convertEntityToResponseDto(trainerEntity);

    }

    @Override
    public ResponseTrainerDTO updateTrainer(Integer id, UpdateTrainerDTO updateTrainerDTO) throws Exception {

        Trainer trainerEntity = itrainerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Trainer not found"));

        trainerEntity.setFirstName(updateTrainerDTO.firstName());
        trainerEntity.setLastName(updateTrainerDTO.lastName());

        itrainerRepository.save(trainerEntity);

        return trainerMapper.convertEntityToResponseDto(trainerEntity);
    }

    @Override
    public List<ResponseTrainerDTO> listTrainers() throws Exception {

        List<Trainer> trainers = itrainerRepository.findAll();
        List<ResponseTrainerDTO> responseTrainerDTOs = trainers.stream().map(e->trainerMapper.convertEntityToResponseDto(e)).toList();

        return responseTrainerDTOs;

    }

    @Override
    public Trainer findByIdTrainerEntity(Integer id) throws Exception {
        Trainer objTrainer = itrainerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Trainer no encontrado"));

        return objTrainer;
    }
}
