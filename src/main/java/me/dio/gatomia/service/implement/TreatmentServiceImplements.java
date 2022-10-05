package me.dio.gatomia.service.implement;

import lombok.RequiredArgsConstructor;
import me.dio.gatomia.enumeration.MeowType;
import me.dio.gatomia.enumeration.Solutions;
import me.dio.gatomia.handler.AppRepositoryException;
import me.dio.gatomia.model.Treatment;
import me.dio.gatomia.repository.TreatmentRepository;
import me.dio.gatomia.service.TreatmentService;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TreatmentServiceImplements implements TreatmentService {

    private final TreatmentRepository treatmentRepository;
    private final CatsServiceImplements catsServiceImplements;
    private final OwnerServiceImplements ownerServiceImplements;


    @Override
    public Treatment NewTreatment(Long ownerId, Long catId, MeowType meow) {
        try {
            Treatment treatment = new Treatment();
            treatment.setOwner(ownerServiceImplements.getOwner(ownerId));
            treatment.setCat(catsServiceImplements.getCat(catId));
            treatment.setMeow(meow);
            return treatmentRepository.save(treatment);
        } catch (Exception e) {
            throw new AppRepositoryException("Could not create treatment", e);
        }
    }

    @Override
    public Solutions Treat(Long treatmentId) {
        return this.GetTreatment(treatmentId).getTreat();
    }

    @Override
    public Solutions ConsultTreatment(Long treatmentId) {
        return this.GetTreatment(treatmentId).treat();
    }

    @Override
    public Treatment GetTreatment(Long treatmentId) {
        try {
            return treatmentRepository.findById(treatmentId).orElseThrow(() -> new RuntimeException("Treatment not found: " + treatmentId));
        } catch (RuntimeException e) {
            throw new AppRepositoryException("Could not find treatment: " + treatmentId, e);
        }
    }

    @Override
    public Treatment EditTreatment(Long treatmentId, MeowType meow) {
        try {
            Treatment treatment = this.GetTreatment(treatmentId);
            treatment.setMeow(meow);
            return treatmentRepository.save(treatment);
        } catch (Exception e) {
            throw new AppRepositoryException("Could not update treatment meow: " + treatmentId, e);
        }
    }

    @Override
    public Treatment EditTreatment(Long treatmentId, Long ownerId, Long catId) {
        try {
            Treatment treatment = this.GetTreatment(treatmentId);
            treatment.setOwner(ownerServiceImplements.getOwner(ownerId));
            treatment.setCat(catsServiceImplements.getCat(catId));
            return treatmentRepository.save(treatment);
        } catch (Exception e) {
            throw new AppRepositoryException("Could not update treatment " + treatmentId, e);
        }
    }

    @Override
    public void DeleteTreatment(Long treatmentId) {
        try {
            Treatment treatment = this.GetTreatment(treatmentId);
            treatmentRepository.delete(treatment);
        } catch (Exception e) {
            throw new AppRepositoryException("Could not delete treatment " + treatmentId, e);
        }
    }
}
