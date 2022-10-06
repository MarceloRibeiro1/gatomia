package me.dio.gatomia.service;

import me.dio.gatomia.dto.treatment.CreateTreatmentDto;
import me.dio.gatomia.dto.treatment.TreatmentDto;
import me.dio.gatomia.enumeration.Solutions;
import me.dio.gatomia.model.Treatment;
import org.springframework.stereotype.Service;

@Service
public interface TreatmentService {

    TreatmentDto createTreatment(CreateTreatmentDto createTreatmentDto);

    void treat(TreatmentDto treatmentDto);

    Solutions consultTreatmentSolution(Long treatmentId);

    Treatment findTreatment(Long treatmentId);

    TreatmentDto editTreatment(TreatmentDto treatmentDto);

    void deleteTreatment(Long treatmentId);
}
