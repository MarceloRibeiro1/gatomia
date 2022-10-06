package me.dio.gatomia.service;

import me.dio.gatomia.dto.OwnerDto;
import me.dio.gatomia.model.Owner;
import org.springframework.stereotype.Service;

@Service
public interface OwnerService {
    OwnerDto createOwner(OwnerDto ownerDto);

    Owner getOwner(OwnerDto ownerDto);

    OwnerDto editOwner(OwnerDto ownerDto);

    void deleteUser(OwnerDto ownerDto);
}
