package me.dio.gatomia.service.implement;

import lombok.RequiredArgsConstructor;
import me.dio.gatomia.dto.cat.CatDto;
import me.dio.gatomia.dto.cat.CreateCatDto;
import me.dio.gatomia.handler.AppRepositoryException;
import me.dio.gatomia.model.Cats;
import me.dio.gatomia.model.Owner;
import me.dio.gatomia.repository.CatsRepository;
import me.dio.gatomia.repository.OwnerRepository;
import me.dio.gatomia.service.CatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CatsServiceImplements implements CatsService {

    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private CatsRepository catsRepository;

    @Override
    public CatDto createCats(CreateCatDto createCatDto) {
        Cats cat = new Cats();
        Owner owner = ownerRepository.findById(createCatDto.getOwnerId())
                .orElseThrow(() -> new AppRepositoryException("Could not find owner" + createCatDto.getOwnerId()));
        cat.setName(createCatDto.getCatName());
        cat.setOwner(owner);
        cat.setBehavior(createCatDto.getBehavior());
        catsRepository.save(cat);
        return new CatDto(cat);
    }

    @Override
    public Cats findCat(Long catId) {
        return catsRepository.findById(catId)
                .orElseThrow(() -> new AppRepositoryException("Cat not found in Repository: " + catId));
    }

    @Override
    public CatDto editCat(CatDto catDto) {
        Cats cat = this.findCat(catDto.getCatId());
        cat.setName(catDto.getCatName());
        cat.setBehavior(catDto.getBehavior());
        catsRepository.save(cat);
        return new CatDto(cat);
    }


    @Override
    public void deleteCat(Long catId) {
        catsRepository.deleteById(catId);
    }
}
