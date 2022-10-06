package me.dio.gatomia.service;

import me.dio.gatomia.dto.cat.CatDto;
import me.dio.gatomia.dto.cat.CreateCatDto;
import me.dio.gatomia.model.Cats;
import org.springframework.stereotype.Service;

@Service
public interface CatsService {
    CatDto createCats(CreateCatDto createCatDto);

    Cats findCat(Long catId);

    CatDto editCat(CatDto catDto);

    void deleteCat(Long catId);
}
