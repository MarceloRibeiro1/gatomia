package me.dio.gatomia.service;

import me.dio.gatomia.enumeration.BehaviorType;
import me.dio.gatomia.model.Cats;
import org.springframework.stereotype.Service;

@Service
public interface CatsService {
    Cats createCats(String catName, Long ownerId, BehaviorType behavior);

    Cats getCat(Long catId);

    Cats editCat(Long catId, String catName);

    Cats editCat(Long catId, Long ownerId);

    Cats editCat(Long catId, BehaviorType behavior);

    void deleteCat(Long catId);
}
