package me.dio.gatomia.service.implement;

import lombok.RequiredArgsConstructor;
import me.dio.gatomia.dto.TreatmentDto;
import me.dio.gatomia.enumeration.Solutions;
import me.dio.gatomia.handler.AppRepositoryException;
import me.dio.gatomia.model.Treatment;
import me.dio.gatomia.repository.CatsRepository;
import me.dio.gatomia.repository.OwnerRepository;
import me.dio.gatomia.repository.TreatmentRepository;
import me.dio.gatomia.service.TreatmentService;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TreatmentServiceImplements implements TreatmentService {

    private final TreatmentRepository treatmentRepository;
    private final CatsRepository catsRepository;
    private final OwnerRepository ownerRepository;


    @Override
    public TreatmentDto NewTreatment(TreatmentDto treatmentDto) {
        Treatment treatment = new Treatment();
        treatment.setOwner(ownerRepository.findById(treatmentDto.getOwnerId()).orElseThrow(() -> new AppRepositoryException("Could not find owner")));
        treatment.setCat(catsRepository.findById(treatmentDto.getCatId()).orElseThrow(() -> new AppRepositoryException("Could not find cat")));
        treatment.setMeow(treatmentDto.getType());
        treatmentRepository.save(treatment);
        return new TreatmentDto(treatment);
    }

    @Override
    public Solutions Treat(TreatmentDto treatmentDto) {
        return this.GetTreatment(treatmentDto).treat();
    }

    @Override
    public Solutions ConsultTreatmentSolution(TreatmentDto treatmentDto) {
        return this.GetTreatment(treatmentDto).getTreat();
    }

    @Override
    public Treatment GetTreatment(TreatmentDto treatmentDto) {
        return treatmentRepository.findById(treatmentDto.getTreatmentId())
                .orElseThrow(() -> new RuntimeException("Treatment not found: " + treatmentDto.getTreatmentId()));

    }

    @Override
    public TreatmentDto EditTreatment(TreatmentDto treatmentDto) {
        Treatment treatment = this.GetTreatment(treatmentDto);
        treatment.setMeow(treatmentDto.getType());
        treatment.setOwner(ownerRepository.findById(treatmentDto.getOwnerId()).orElseThrow(() -> new AppRepositoryException("Could not find owner")));
        treatment.setCat(catsRepository.findById(treatmentDto.getCatId()).orElseThrow(() -> new AppRepositoryException("Could not find cat")));
        treatmentRepository.save(treatment);
        return new TreatmentDto(treatment);
    }

    @Override
    public void DeleteTreatment(TreatmentDto treatmentDto) {
        treatmentRepository.deleteById(treatmentDto.getTreatmentId());
    }
}
