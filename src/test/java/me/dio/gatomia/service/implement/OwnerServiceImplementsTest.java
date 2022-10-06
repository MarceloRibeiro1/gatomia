package me.dio.gatomia.service.implement;

import me.dio.gatomia.dto.owner.CreateOwnerDto;
import me.dio.gatomia.dto.owner.OwnerDto;
import me.dio.gatomia.model.Owner;
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

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class OwnerServiceImplementsTest {

    @Mock
    OwnerRepository ownerRepository;
    String ownerName1 = "OwnerName1";
    String ownerName2 = "OwnerName2";
    Long ownerId2 = 2L;
    
    Owner owner2 = new Owner(ownerId2, ownerName2);

    OwnerDto ownerDto;
    CreateOwnerDto createOwnerDto = new CreateOwnerDto(ownerName1);
    @InjectMocks
    OwnerServiceImplements ownerServiceImplements;

    @BeforeEach
    void setUp() {
        ownerDto = new OwnerDto(ownerId2, ownerName2);
        Mockito.when(ownerRepository.findById(ownerId2)).thenReturn(Optional.ofNullable(owner2));
    }

    @Test
    void ShouldBeAbleToCreateOwner() {
        ownerServiceImplements.createOwner(createOwnerDto);
        ArgumentCaptor<Owner> ownerCaptor = ArgumentCaptor.forClass(Owner.class);
        Mockito.verify(ownerRepository).save(ownerCaptor.capture());
        assertEquals(ownerCaptor.getValue().getName(), ownerName1);
    }

    @Test
    void ShouldBeAbleToGetOwner() {
        ownerServiceImplements.getOwner(ownerDto.getOwnerId());
        ArgumentCaptor<Long> ownerCaptor = ArgumentCaptor.forClass(Long.class);
        Mockito.verify(ownerRepository).findById(ownerCaptor.capture());
        assertEquals(ownerCaptor.getValue(), ownerId2);
    }

    @Test
    void editOwnerName() {
        OwnerDto editedOwner = new OwnerDto(ownerId2, ownerName1);
        ownerServiceImplements.editOwner(editedOwner);
        ArgumentCaptor<Owner> ownerCaptor = ArgumentCaptor.forClass(Owner.class);
        Mockito.verify(ownerRepository).save(ownerCaptor.capture());
        assertEquals(ownerCaptor.getValue().getName(), ownerName1);
    }

    @Test
    void deleteUser() {
        ownerServiceImplements.deleteOwner(ownerDto.getOwnerId());
        ArgumentCaptor<Long> ownerCaptor = ArgumentCaptor.forClass(Long.class);
        Mockito.verify(ownerRepository).deleteById(ownerCaptor.capture());
        assertEquals(ownerCaptor.getValue(), ownerId2);
    }

}