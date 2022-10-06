package me.dio.gatomia.service;

import me.dio.gatomia.dto.owner.CreateOwnerDto;
import me.dio.gatomia.dto.owner.OwnerDto;
import me.dio.gatomia.model.Owner;
import org.springframework.stereotype.Service;

@Service
public interface OwnerService {
    OwnerDto createOwner(CreateOwnerDto createOwnerDto);

    Owner getOwner(Long ownerId);

    OwnerDto editOwner(OwnerDto editOwnerDto);

    void deleteOwner(Long ownerId);
}
