package me.dio.gatomia.service.implement;

import lombok.RequiredArgsConstructor;
import me.dio.gatomia.dto.CatDto;
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
    public CatDto createCats(CatDto catDto) {
        Cats cat = new Cats();
        cat.setName(catDto.getCatName());
        cat.setOwner(ownerServiceImplements.getOwner(catDto.getOwnerId()));
        cat.setBehavior(catDto.getBehavior());
        catsRepository.save(cat);
        return new CatDto(cat);
    }

    @Override
    public Cats getCat(CatDto catDto) {
        return catsRepository.findById(catDto.getCatId()).orElseThrow(() -> new AppRepositoryException("Cat not found in Repository: " + catDto.getCatId()));
    }

    @Override
    public CatDto editCat(CatDto catDto) {
        Cats cat = this.getCat(catDto);
        cat.setName(catDto.getCatName());
        cat.setOwner(ownerServiceImplements.getOwner(catDto.getOwnerId()));
        cat.setBehavior(catDto.getBehavior());
        catsRepository.save(cat);
        return new CatDto(cat);
    }


    @Override
    public void deleteCat(CatDto catDto) {
        catsRepository.deleteById(catDto.getCatId());
    }
}
