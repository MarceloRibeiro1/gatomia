package me.dio.gatomia.service;

import me.dio.gatomia.model.Owner;
import org.springframework.stereotype.Service;

@Service
public interface OwnerService {
    Owner createOwner(String ownerName);

    Owner getOwner(Long ownerId);

    Owner editOwnerName(Long ownerId, String ownerName);

    void deleteUser(Long ownerId);
}
