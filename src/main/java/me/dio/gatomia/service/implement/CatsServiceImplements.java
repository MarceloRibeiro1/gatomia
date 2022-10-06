package me.dio.gatomia.service.implement;

import lombok.RequiredArgsConstructor;
import me.dio.gatomia.enumeration.BehaviorType;
import me.dio.gatomia.handler.AppRepositoryException;
import me.dio.gatomia.model.Cats;
import me.dio.gatomia.repository.CatsRepository;
import me.dio.gatomia.service.CatsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CatsServiceImplements implements CatsService {

    private OwnerServiceImplements ownerServiceImplements;
    private CatsRepository catsRepository;

    @Override
    public Cats createCats(String catName, Long ownerId, BehaviorType behavior) {
        try {
            Cats cat = new Cats();
            cat.setName(catName);
            cat.setOwner(ownerServiceImplements.getOwner(ownerId));
            cat.setBehavior(behavior);
            catsRepository.save(cat);
            return cat;
        } catch (RuntimeException e) {
            throw new AppRepositoryException("Could not create cat", e);
        }
    }

    @Override
    public Cats getCat(Long catId) {
        try {
            return catsRepository.findById(catId).orElseThrow(() -> new AppRepositoryException("Cat not found in Repository: " + catId));
        } catch (RuntimeException e) {
            throw new AppRepositoryException("Cat not found: " + catId, e);
        }
    }

    @Override
    public Cats editCat(Long catId, String catName) {
        try {
            Cats cat = this.getCat(catId);
            cat.setName(catName);
            return catsRepository.save(cat);
        } catch (Exception e) {
            throw new AppRepositoryException("Could not update cat name", e);
        }
    }

    @Override
    public Cats editCat(Long catId, Long ownerId) {
        try {
            Cats cat = this.getCat(catId);
            cat.setOwner(ownerServiceImplements.getOwner(ownerId));
            return catsRepository.save(cat);
        } catch (Exception e) {
            throw new AppRepositoryException("Could not update cat owner", e);
        }
    }

    @Override
    public Cats editCat(Long catId, BehaviorType behavior) {
        try {
            Cats cat = this.getCat(catId);
            cat.setBehavior(behavior);
            return catsRepository.save(cat);
        } catch (Exception e) {
            throw new AppRepositoryException("Could not update cat behavior", e);
        }
    }

    @Override
    public void deleteCat(Long catId) {
        try {
            catsRepository.deleteById(catId);
        } catch (Exception e) {
            throw new AppRepositoryException("Could not delete cat", e);
        }
    }
}
