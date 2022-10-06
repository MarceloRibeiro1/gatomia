package me.dio.gatomia.service.implement;

import me.dio.gatomia.enumeration.BehaviorType;
import me.dio.gatomia.handler.AppRepositoryException;
import me.dio.gatomia.model.Cats;
import me.dio.gatomia.model.Owner;
import me.dio.gatomia.repository.CatsRepository;
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

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class CatsServiceImplementsTest {

    @Mock
    CatsRepository catsRepository;
    @Mock
    OwnerServiceImplements ownerServiceImplements;
    @InjectMocks
    CatsServiceImplements catsServiceImplements;

    String catName = "cat";
    String editedName = "edited";
    BehaviorType catBehavior = BehaviorType.ARISCO;
    BehaviorType editedBehavior = BehaviorType.BRINCALHAO;
    Long catId = 1L;
    Owner owner = Owner.builder().id(1L).build();
    Owner editedOwner = Owner.builder().id(2L).build();
    Cats cat = new Cats();

    @BeforeEach
    void setUp() {
        Mockito.when(catsRepository.findById(catId)).thenReturn(Optional.ofNullable(Cats.builder()
                .name(catName)
                .behavior(catBehavior)
                .owner(owner)
                .build()
        ));
        Mockito.when(ownerServiceImplements.getOwner(1L)).thenReturn(owner);
    }

    @Test
    void ShouldCreateCats() {
        catsServiceImplements.createCats(catName, owner.getId(), catBehavior);
        ArgumentCaptor<Cats> catsCaptor = ArgumentCaptor.forClass(Cats.class);
        Mockito.verify(catsRepository).save(catsCaptor.capture());
        assertEquals(catsCaptor.getValue().getName(), catName);
        assertEquals(catsCaptor.getValue().getBehavior(), catBehavior);
        assertEquals(catsCaptor.getValue().getOwner(), owner);
    }

    @Test
    void CreateCatsCanThrow() {
        catsServiceImplements.createCats(catName, owner.getId(), catBehavior);
        ArgumentCaptor<Cats> catsCaptor = ArgumentCaptor.forClass(Cats.class);
        given(catsRepository.save(catsCaptor.capture())).willThrow(new RuntimeException());
        assertThatThrownBy(() -> catsServiceImplements.createCats(catName, owner.getId(), catBehavior))
                .isInstanceOf(AppRepositoryException.class)
                .hasMessageContaining("Could not create cat");
    }

    @Test
    void ShouldGetCat() {
        catsServiceImplements.getCat(catId);
        ArgumentCaptor<Cats> catsCaptor = ArgumentCaptor.forClass(Cats.class);
        Mockito.verify(catsRepository).findById(catId);
    }

    @Test
    void ShouldNotGetCatAndThrow() {
        catsServiceImplements.getCat(1L);
        ArgumentCaptor<Long> catsCaptor = ArgumentCaptor.forClass(Long.class);
        given(catsRepository.findById(catsCaptor.capture())).willThrow(new RuntimeException());
        assertThatThrownBy(() -> catsServiceImplements.getCat(1L))
                .isInstanceOf(AppRepositoryException.class)
                .hasMessageContaining("Cat not found: " + catsCaptor.getValue());
    }

    @Test
    void ShouldEditCatName() {
        catsServiceImplements.editCat(catId, editedName);
        ArgumentCaptor<Cats> catsCaptor = ArgumentCaptor.forClass(Cats.class);
        Mockito.verify(catsRepository).save(catsCaptor.capture());
        assertEquals(catsCaptor.getValue().getName(), editedName);
    }

    @Test
    void ShouldNotEditCatNameAndThrow() {
        catsServiceImplements.editCat(catId, editedName);
        ArgumentCaptor<Cats> catsCaptor = ArgumentCaptor.forClass(Cats.class);
        given(catsRepository.save(catsCaptor.capture())).willThrow(new RuntimeException());
        assertThatThrownBy(() -> catsServiceImplements.editCat(catId, editedName))
                .isInstanceOf(AppRepositoryException.class)
                .hasMessageContaining("Could not update cat name");
    }

    @Test
    void ShouldEditCatOwner() {
        catsServiceImplements.editCat(catId, owner.getId());
        ArgumentCaptor<Cats> catsCaptor = ArgumentCaptor.forClass(Cats.class);
        Mockito.verify(catsRepository).save(catsCaptor.capture());
        assertEquals(catsCaptor.getValue().getOwner(), owner);
    }

    @Test
    void ShouldNotEditCatOwnerAndThrow() {
        catsServiceImplements.editCat(catId, owner.getId());
        ArgumentCaptor<Cats> catsCaptor = ArgumentCaptor.forClass(Cats.class);
        given(catsRepository.save(catsCaptor.capture())).willThrow(new RuntimeException());
        assertThatThrownBy(() -> catsServiceImplements.editCat(catId, owner.getId()))
                .isInstanceOf(AppRepositoryException.class)
                .hasMessageContaining("Could not update cat owner");
    }

    @Test
    void ShouldEditCatBehavior() {
        catsServiceImplements.editCat(catId, editedBehavior);
        ArgumentCaptor<Cats> catsCaptor = ArgumentCaptor.forClass(Cats.class);
        Mockito.verify(catsRepository).save(catsCaptor.capture());
        assertEquals(catsCaptor.getValue().getBehavior(), editedBehavior);
    }

    @Test
    void ShouldNotEditCatBehaviorAndThrow() {
        catsServiceImplements.editCat(catId, editedBehavior);
        ArgumentCaptor<Cats> catsCaptor = ArgumentCaptor.forClass(Cats.class);
        given(catsRepository.save(catsCaptor.capture())).willThrow(new RuntimeException());
        assertThatThrownBy(() -> catsServiceImplements.editCat(catId, editedBehavior))
                .isInstanceOf(AppRepositoryException.class)
                .hasMessageContaining("Could not update cat behavior");
    }


    @Test
    void deleteCat() {
        catsServiceImplements.deleteCat(catId);
        ArgumentCaptor<Long> catsCaptor = ArgumentCaptor.forClass(Long.class);
        Mockito.verify(catsRepository).deleteById(catsCaptor.capture());
        assertEquals(catsCaptor.getValue(), catId);
    }

    @Test
    void deleteCatAndThrow() {
        catsServiceImplements.deleteCat(catId);
        ArgumentCaptor<Long> catsCaptor = ArgumentCaptor.forClass(Long.class);
        doThrow(new RuntimeException()).when(catsRepository).deleteById(catsCaptor.capture());
        assertThatThrownBy(() -> catsServiceImplements.deleteCat(catId))
                .isInstanceOf(AppRepositoryException.class)
                .hasMessageContaining("Could not delete cat");

    }
}