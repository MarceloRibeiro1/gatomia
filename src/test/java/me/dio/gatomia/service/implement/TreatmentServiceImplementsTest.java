package me.dio.gatomia.service.implement;

import me.dio.gatomia.dto.treatment.CreateTreatmentDto;
import me.dio.gatomia.dto.treatment.TreatmentDto;
import me.dio.gatomia.enumeration.BehaviorType;
import me.dio.gatomia.enumeration.MeowType;
import me.dio.gatomia.enumeration.Solutions;
import me.dio.gatomia.model.Cats;
import me.dio.gatomia.model.Owner;
import me.dio.gatomia.model.Treatment;
import me.dio.gatomia.repository.CatsRepository;
import me.dio.gatomia.repository.OwnerRepository;
import me.dio.gatomia.repository.TreatmentRepository;
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
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class TreatmentServiceImplementsTest {

    @Mock
    TreatmentRepository treatmentRepository;
    @Mock
    CatsRepository catsRepository;
    @Mock
    OwnerRepository ownerRepository;
    @InjectMocks
    TreatmentServiceImplements treatmentServiceImplements;

    Cats cat = new Cats().builder().id(1L).behavior(BehaviorType.ARISCO).build();
    Owner owner = new Owner().builder().id(1L).build();
    MeowType meowType = MeowType.INTERCALADO;
    TreatmentDto treatmentDto;
    CreateTreatmentDto createTreatmentDto = new CreateTreatmentDto(meowType, owner.getId(), cat.getId());

    @BeforeEach
    public void setUp() throws Exception {
        treatmentDto = new TreatmentDto(1L, meowType, owner.getId(), cat.getId());
        Mockito.when(catsRepository.findById(cat.getId())).thenReturn(Optional.ofNullable(cat));
        Mockito.when(ownerRepository.findById(owner.getId())).thenReturn(Optional.ofNullable(owner));
        Mockito.when(treatmentRepository.findById(treatmentDto.getTreatmentId())).thenReturn(Optional.ofNullable(new Treatment().builder()
                .id(1L)
                .cat(cat)
                .meow(meowType)
                .owner(owner)
                .isTreated(false)
                .build()));
    }

    @Test
    void ShouldCreateNewTreatment() {
        treatmentServiceImplements.createTreatment(createTreatmentDto);
        ArgumentCaptor<Treatment> treatCaptor = ArgumentCaptor.forClass(Treatment.class);
        Mockito.verify(treatmentRepository).save(treatCaptor.capture());
        assertEquals(treatCaptor.getValue().getOwner(), owner);
        assertEquals(treatCaptor.getValue().getCat(), cat);
        assertEquals(treatCaptor.getValue().getMeow(), meowType);
    }

    @Test
    void treat() {
        treatmentServiceImplements.treat(treatmentDto);
        ArgumentCaptor<Treatment> treatCaptor = ArgumentCaptor.forClass(Treatment.class);
        Mockito.verify(treatmentRepository).save(treatCaptor.capture());
        assertEquals(treatCaptor.getValue().getId(), treatmentDto.getTreatmentId());
        assertTrue(treatCaptor.getValue().isTreated());
    }

    @Test
    void consultTreatment() {
        Treatment a = new Treatment().builder()
                .id(1L)
                .cat(cat)
                .meow(meowType)
                .owner(owner)
                .isTreated(false)
                .build();
        Solutions solution = treatmentServiceImplements.consultTreatmentSolution(treatmentDto.getTreatmentId());
        assertEquals(solution, a.getTreat());
    }

    @Test
    void getTreatment() {
        treatmentServiceImplements.findTreatment(treatmentDto.getTreatmentId());
        ArgumentCaptor<Long> treatCaptor = ArgumentCaptor.forClass(Long.class);
        Mockito.verify(treatmentRepository).findById(treatCaptor.capture());
        assertEquals(treatCaptor.getValue(), treatmentDto.getTreatmentId());
    }

    @Test
    void editTreatment() {
        treatmentServiceImplements.editTreatment(treatmentDto);
        ArgumentCaptor<Treatment> treatCaptor = ArgumentCaptor.forClass(Treatment.class);
        Mockito.verify(treatmentRepository).save(treatCaptor.capture());
        assertEquals(treatCaptor.getValue().getOwner().getId(), treatmentDto.getOwnerId());
        assertEquals(treatCaptor.getValue().getCat().getId(), treatmentDto.getCatId());
        assertEquals(treatCaptor.getValue().getMeow(), treatmentDto.getType());
    }

    @Test
    void deleteTreatment() {
        treatmentServiceImplements.deleteTreatment(treatmentDto.getTreatmentId());
        ArgumentCaptor<Long> treatCaptor = ArgumentCaptor.forClass(Long.class);
        Mockito.verify(treatmentRepository).deleteById(treatCaptor.capture());
        assertEquals(treatCaptor.getValue(), treatmentDto.getOwnerId());
    }
}