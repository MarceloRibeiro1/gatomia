package me.dio.gatomia.service.implement;

import me.dio.gatomia.dto.cat.CatDto;
import me.dio.gatomia.dto.cat.CreateCatDto;
import me.dio.gatomia.enumeration.BehaviorType;
import me.dio.gatomia.model.Cats;
import me.dio.gatomia.model.Owner;
import me.dio.gatomia.repository.CatsRepository;
import me.dio.gatomia.repository.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class CatsServiceImplementsTest {

    @Mock
    CatsRepository catsRepository;
    @Mock
    OwnerRepository ownerRepository;
    @InjectMocks
    CatsServiceImplements catsServiceImplements;

    String catName = "cat";
    String editedName = "edited";
    BehaviorType catBehavior = BehaviorType.ARISCO;
    BehaviorType editedBehavior = BehaviorType.BRINCALHAO;
    Long catId = 1L;
    CatDto editCatDto;
    CatDto editedCatDto;
    CreateCatDto createCatDto;
    Owner owner = new Owner().builder().id(1L).build();
    Owner editedOwner = new Owner().builder().id(2L).build();

    @BeforeEach
    void setUp() {
        editCatDto = new CatDto(catId, catName, catBehavior, owner.getId());
        editedCatDto = new CatDto(catId, editedName, editedBehavior, editedOwner.getId());
        createCatDto = new CreateCatDto(catName, catBehavior, owner.getId());
        Mockito.when(catsRepository.findById(catId)).thenReturn(Optional.ofNullable(Cats.builder()
                .name(catName)
                .behavior(catBehavior)
                .owner(owner)
                .build()
        ));
        Mockito.when(ownerRepository.findById(1L)).thenReturn(Optional.ofNullable(owner));
        Mockito.when(ownerRepository.findById(2L)).thenReturn(Optional.ofNullable(editedOwner));
    }

    @Test
    void ShouldCreateCats() {
        catsServiceImplements.createCats(createCatDto);
        ArgumentCaptor<Cats> catsCaptor = ArgumentCaptor.forClass(Cats.class);
        Mockito.verify(catsRepository).save(catsCaptor.capture());
        assertEquals(catsCaptor.getValue().getName(), catName);
        assertEquals(catsCaptor.getValue().getBehavior(), catBehavior);
        assertEquals(catsCaptor.getValue().getOwner(), owner);
    }

    @Test
    void ShouldGetCat() {
        catsServiceImplements.findCat(editCatDto.getCatId());
        Mockito.verify(catsRepository).findById(catId);
    }


    @Test
    void ShouldEditCatName() {
        catsServiceImplements.editCat(editedCatDto);
        ArgumentCaptor<Cats> catsCaptor = ArgumentCaptor.forClass(Cats.class);
        Mockito.verify(catsRepository).save(catsCaptor.capture());
        assertEquals(catsCaptor.getValue().getName(), editedName);
    }

    @Test
    void ShouldEditCatBehavior() {
        catsServiceImplements.editCat(editedCatDto);
        ArgumentCaptor<Cats> catsCaptor = ArgumentCaptor.forClass(Cats.class);
        Mockito.verify(catsRepository).save(catsCaptor.capture());
        assertEquals(catsCaptor.getValue().getBehavior(), editedBehavior);
    }

    @Test
    void deleteCat() {
        catsServiceImplements.deleteCat(editCatDto.getCatId());
        ArgumentCaptor<Long> catsCaptor = ArgumentCaptor.forClass(Long.class);
        Mockito.verify(catsRepository).deleteById(catsCaptor.capture());
        assertEquals(catsCaptor.getValue(), catId);
    }

}