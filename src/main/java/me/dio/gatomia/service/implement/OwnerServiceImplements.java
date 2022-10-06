package me.dio.gatomia.service.implement;

import lombok.RequiredArgsConstructor;
import me.dio.gatomia.dto.owner.CreateOwnerDto;
import me.dio.gatomia.dto.owner.OwnerDto;
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
    public OwnerDto createOwner(CreateOwnerDto createOwnerDto) {
        Owner owner = new Owner();
        owner.setName(createOwnerDto.getOwnerName());
        ownerRepository.save(owner);
        return new OwnerDto(owner);
    }

    @Override
    public Owner getOwner(Long ownerId) {
        return ownerRepository.findById(ownerId).orElseThrow(() -> new AppRepositoryException("Could not find owner: " + ownerId));
    }

    @Override
    public OwnerDto editOwner(OwnerDto ownerDto) {
        Owner owner = this.getOwner(ownerDto.getOwnerId());
        owner.setName(ownerDto.getOwnerName());
        ownerRepository.save(owner);
        return new OwnerDto(owner);
    }

    @Override
    public void deleteOwner(Long ownerId) {
        ownerRepository.deleteById(ownerId);
    }
}
