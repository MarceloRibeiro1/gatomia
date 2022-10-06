package me.dio.gatomia.service.implement;

import lombok.RequiredArgsConstructor;
import me.dio.gatomia.dto.OwnerDto;
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
    public OwnerDto createOwner(OwnerDto ownerDto) {
        Owner owner = new Owner();
        owner.setName(ownerDto.getOwnerName());
        ownerRepository.save(owner);
        return new OwnerDto(owner);
    }

    @Override
    public Owner getOwner(OwnerDto ownerDto) {
        return ownerRepository.findById(ownerDto.getOwnerId()).orElseThrow(() -> new AppRepositoryException("Could not find owner: " + ownerDto.getOwnerId()));
    }

    @Override
    public OwnerDto editOwner(OwnerDto ownerDto) {
        Owner owner = this.getOwner(ownerDto);
        owner.setName(ownerDto.getOwnerName());
        ownerRepository.save(owner);
        return new OwnerDto(owner);
    }

    @Override
    public void deleteUser(OwnerDto ownerDto) {
        ownerRepository.deleteById(ownerDto.getOwnerId());
    }
}
