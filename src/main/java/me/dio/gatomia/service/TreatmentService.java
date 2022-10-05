package me.dio.gatomia.service;

import me.dio.gatomia.enumeration.MeowType;
import me.dio.gatomia.enumeration.Solutions;
import me.dio.gatomia.model.Treatment;
import org.springframework.stereotype.Service;

@Service
public interface TreatmentService {

    Treatment NewTreatment(Long ownerId, Long catId, MeowType meow);

    Solutions Treat(Long treatmentId);

    Solutions ConsultTreatment(Long treatmentId);

    Treatment GetTreatment(Long treatmentId);

    Treatment EditTreatment(Long treatmentId, MeowType meow);

    Treatment EditTreatment(Long treatmentId, Long ownerId, Long catId);


    void DeleteTreatment(Long treatmentId);
}
