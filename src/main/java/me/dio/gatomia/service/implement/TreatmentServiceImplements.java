package me.dio.gatomia.service.implement;

import lombok.RequiredArgsConstructor;
import me.dio.gatomia.dto.treatment.CreateTreatmentDto;
import me.dio.gatomia.dto.treatment.TreatmentDto;
import me.dio.gatomia.enumeration.Solutions;
import me.dio.gatomia.handler.AppRepositoryException;
import me.dio.gatomia.model.Treatment;
import me.dio.gatomia.repository.CatsRepository;
import me.dio.gatomia.repository.OwnerRepository;
import me.dio.gatomia.repository.TreatmentRepository;
import me.dio.gatomia.service.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TreatmentServiceImplements implements TreatmentService {

    @Autowired
    private final TreatmentRepository treatmentRepository;
    @Autowired
    private final CatsRepository catsRepository;
    @Autowired
    private final OwnerRepository ownerRepository;


    @Override
    public TreatmentDto createTreatment(CreateTreatmentDto createTreatmentDto) {
        Treatment treatment = new Treatment();
        treatment.setOwner(ownerRepository.findById(createTreatmentDto.getOwnerId()).orElseThrow(() -> new AppRepositoryException("Could not find owner")));
        treatment.setCat(catsRepository.findById(createTreatmentDto.getCatId()).orElseThrow(() -> new AppRepositoryException("Could not find cat")));
        treatment.setMeow(createTreatmentDto.getType());
        treatmentRepository.save(treatment);
        return new TreatmentDto(treatment);
    }

    @Override
    public void treat(TreatmentDto treatmentDto) {
        Treatment treatment = this.findTreatment(treatmentDto.getTreatmentId());
        treatment.treat();
        treatmentRepository.save(treatment);
    }

    @Override
    public Solutions consultTreatmentSolution(Long treatmentId) {
        return this.findTreatment(treatmentId).getTreat();
    }

    @Override
    public Treatment findTreatment(Long treatmentId) {
        return treatmentRepository.findById(treatmentId)
                .orElseThrow(() -> new RuntimeException("Treatment not found: " + treatmentId));

    }

    @Override
    public TreatmentDto editTreatment(TreatmentDto treatmentDto) {
        Treatment treatment = this.findTreatment(treatmentDto.getTreatmentId());
        treatment.setMeow(treatmentDto.getType());
        treatment.setCat(catsRepository.findById(treatmentDto.getCatId()).orElseThrow(() -> new AppRepositoryException("Could not find cat")));
        treatmentRepository.save(treatment);
        return new TreatmentDto(treatment);
    }

    @Override
    public void deleteTreatment(Long treatmentId) {
        treatmentRepository.deleteById(treatmentId);
    }
}
