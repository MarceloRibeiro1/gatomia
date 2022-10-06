package me.dio.gatomia.service;

import me.dio.gatomia.dto.CatDto;
import me.dio.gatomia.model.Cats;
import org.springframework.stereotype.Service;

@Service
public interface CatsService {
    CatDto createCats(CatDto catDto);

    Cats getCat(CatDto catDto);

    CatDto editCat(CatDto catDto);

    void deleteCat(CatDto catDto);
}
