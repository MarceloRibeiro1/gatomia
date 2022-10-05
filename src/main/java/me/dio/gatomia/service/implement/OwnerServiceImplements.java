package me.dio.gatomia.service.implement;

import lombok.RequiredArgsConstructor;
import me.dio.gatomia.handler.AppRepositoryException;
import me.dio.gatomia.model.Owner;
import me.dio.gatomia.repository.OwnerRepository;
import me.dio.gatomia.service.OwnerService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerServiceImplements implements OwnerService {

    private final OwnerRepository ownerRepository;

    @Override
    public Owner createOwner(String ownerName) {
        try {
            Owner owner = new Owner();
            owner.setName(ownerName);
            return ownerRepository.save(owner);
        } catch (Exception e) {
            throw new AppRepositoryException("Could not create owner", e);
        }
    }

    @Override
    public Owner getOwner(Long ownerId) {
        try {
            return ownerRepository.findById(ownerId).orElseThrow(() -> new AppRepositoryException("Could not find owner: " + ownerId));
        } catch (AppRepositoryException e) {
            throw new AppRepositoryException("Could not find owner: " + ownerId, e);
        }
    }

    @Override
    public Owner editOwnerName(Long ownerId, String ownerName) {
        try {
            Owner owner = this.getOwner(ownerId);
            owner.setName(ownerName);
            return ownerRepository.save(owner);
        } catch (Exception e) {
            throw new AppRepositoryException("Could not update owner: " + ownerId, e);
        }
    }

    @Override
    public void deleteUser(Long ownerId) {
        try {
            Owner owner = this.getOwner(ownerId);
            ownerRepository.delete(owner);
        } catch (Exception e) {
            throw new AppRepositoryException("Could not delete owner: " + ownerId, e);
        }
    }
}
