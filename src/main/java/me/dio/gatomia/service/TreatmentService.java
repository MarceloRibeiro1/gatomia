package me.dio.gatomia.service;

import me.dio.gatomia.dto.TreatmentDto;
import me.dio.gatomia.enumeration.Solutions;
import me.dio.gatomia.model.Treatment;
import org.springframework.stereotype.Service;

@Service
public interface TreatmentService {

    TreatmentDto NewTreatment(TreatmentDto treatmentDto);

    Solutions Treat(TreatmentDto treatmentDto);

    Solutions ConsultTreatmentSolution(TreatmentDto treatmentDto);

    Treatment GetTreatment(TreatmentDto treatmentDto);

    TreatmentDto EditTreatment(TreatmentDto treatmentDto);

    void DeleteTreatment(TreatmentDto treatmentDto);
}
