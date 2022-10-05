package me.dio.gatomia.service.implement;

import me.dio.gatomia.enumeration.BehaviorType;
import me.dio.gatomia.handler.AppRepositoryException;
import me.dio.gatomia.model.Cats;
import me.dio.gatomia.model.Owner;
import me.dio.gatomia.model.Treatment;
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

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class OwnerServiceImplementsTest {

    @Mock
    OwnerRepository ownerRepository;
    String ownerName1 = "OwnerName1";
    Long ownerId1 = 1L;
    String ownerName2 = "OwnerName2";
    Long ownerId2 = 2L;
    List<Treatment> treatment = List.of(new Treatment());
    Cats cat = Cats.builder()
            .id(1L)
            .name("CatName")
            .behavior(BehaviorType.ARISCO)
            .build();
    List<Cats> cats2 = List.of(cat);
    Owner owner2 = new Owner(ownerId2, ownerName2, cats2, treatment);
    Owner owner1;
    @InjectMocks
    OwnerServiceImplements ownerServiceImplements;

    @BeforeEach
    void setUp() {
        Mockito.when(ownerRepository.findById(ownerId2)).thenReturn(Optional.ofNullable(owner2));
    }

    @Test
    void ShouldBeAbleToCreateOwner() {
        ownerServiceImplements.createOwner(ownerName1);
        ArgumentCaptor<Owner> ownerCaptor = ArgumentCaptor.forClass(Owner.class);
        Mockito.verify(ownerRepository).save(ownerCaptor.capture());
        assertEquals(ownerCaptor.getValue().getName(), ownerName1);
    }

    @Test
    void ShouldBeAbleToThrowWhenCreatingOwner() {
        ownerServiceImplements.createOwner(ownerName1);
        ArgumentCaptor<Owner> ownerCaptor = ArgumentCaptor.forClass(Owner.class);
        given(ownerRepository.save(ownerCaptor.capture())).willThrow(new RuntimeException());
        assertThatThrownBy(() -> ownerServiceImplements.createOwner(ownerName1))
                .isInstanceOf(AppRepositoryException.class)
                .hasMessageContaining("Could not create owner");
    }

    @Test
    void ShouldBeAbleToGetOwner() {
        ownerServiceImplements.getOwner(ownerId2);
        ArgumentCaptor<Long> ownerCaptor = ArgumentCaptor.forClass(Long.class);
        Mockito.verify(ownerRepository).findById(ownerCaptor.capture());
        assertEquals(ownerCaptor.getValue(), ownerId2);
    }

    @Test
    void InvalidOwnerShouldThrow() {
        AppRepositoryException assertTrows = assertThrows(AppRepositoryException.class, () -> {
            ownerServiceImplements.getOwner(ownerId1);
        });
        assertEquals(assertTrows.getMessage(), "Could not find owner: " + ownerId1);
    }

    @Test
    void editOwnerName() {
        ownerServiceImplements.editOwnerName(ownerId2, ownerName1);
        ArgumentCaptor<Owner> ownerCaptor = ArgumentCaptor.forClass(Owner.class);
        Mockito.verify(ownerRepository).save(ownerCaptor.capture());
        assertEquals(ownerCaptor.getValue().getName(), ownerName1);
    }

    @Test
    void editOwnerNameWhenTrowError() {
        ownerServiceImplements.editOwnerName(ownerId2, ownerName1);
        ArgumentCaptor<Owner> ownerCaptor = ArgumentCaptor.forClass(Owner.class);
        given(ownerRepository.save(ownerCaptor.capture())).willThrow(new RuntimeException());
        assertThatThrownBy(() -> ownerServiceImplements.editOwnerName(ownerId2, ownerName1))
                .isInstanceOf(AppRepositoryException.class)
                .hasMessageContaining("Could not update owner: " + ownerId2);
    }

    @Test
    void deleteUser() {
        ownerServiceImplements.deleteUser(ownerId2);
        ArgumentCaptor<Owner> ownerCaptor = ArgumentCaptor.forClass(Owner.class);
        Mockito.verify(ownerRepository).delete(ownerCaptor.capture());
        assertEquals(ownerCaptor.getValue().getName(), ownerName2);
    }

    @Test
    void deleteOwnerNameWhenTrowError() {
        ownerServiceImplements.deleteUser(ownerId2);
        ArgumentCaptor<Owner> ownerCaptor = ArgumentCaptor.forClass(Owner.class);
        doThrow(new RuntimeException()).when(ownerRepository).delete(ownerCaptor.capture());
        assertThatThrownBy(() -> ownerServiceImplements.deleteUser(ownerId2))
                .isInstanceOf(AppRepositoryException.class)
                .hasMessageContaining("Could not delete owner: " + ownerId2);
    }
}