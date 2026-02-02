package com.systemgym.systemgym.service.implement;

import com.systemgym.systemgym.dto.request.CreateDurationDTO;
import com.systemgym.systemgym.dto.response.ResponseDurationDTO;
import com.systemgym.systemgym.mapper.DurationMapper;
import com.systemgym.systemgym.model.Duration;
import com.systemgym.systemgym.repository.IDurationRepository;
import com.systemgym.systemgym.service.IDurationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DurationServiceImpl implements IDurationService {

    private final IDurationRepository iDurationRepository;
    private final DurationMapper durationMapper;

    public DurationServiceImpl(IDurationRepository iDurationRepository, DurationMapper durationMapper) {
        this.iDurationRepository = iDurationRepository;
        this.durationMapper = durationMapper;
    }

    @Override
    public ResponseDurationDTO saveDuration(CreateDurationDTO createDurationDTO) throws Exception {

        Duration duration = iDurationRepository.save(durationMapper.convertRequestToEntity(createDurationDTO));

        return durationMapper.convertEntityToResponseDto(duration);

    }

    @Override
    public List<ResponseDurationDTO> findAllDuration() throws Exception {

        List<Duration> durations = iDurationRepository.findAll();
        List<ResponseDurationDTO> responseDurationDTOS = durations.stream().map(e-> durationMapper.convertEntityToResponseDto(e)).toList();

        return responseDurationDTOS;
    }
}
