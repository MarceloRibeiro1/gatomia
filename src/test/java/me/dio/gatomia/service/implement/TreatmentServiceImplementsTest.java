package me.dio.gatomia.service.implement;

import me.dio.gatomia.enumeration.BehaviorType;
import me.dio.gatomia.enumeration.MeowType;
import me.dio.gatomia.handler.AppRepositoryException;
import me.dio.gatomia.model.Cats;
import me.dio.gatomia.model.Owner;
import me.dio.gatomia.model.Treatment;
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

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class TreatmentServiceImplementsTest {

    @Mock
    TreatmentRepository treatmentRepository;
    @Mock
    CatsServiceImplements catsServiceImplements;
    @Mock
    OwnerServiceImplements ownerServiceImplements;
    @InjectMocks
    TreatmentServiceImplements treatmentServiceImplements;

    Cats cat = new Cats().builder().id(1L).behavior(BehaviorType.ARISCO).build();
    Cats editedCat = new Cats().builder().id(2L).build();
    Owner owner = new Owner().builder().id(1L).build();
    Owner editedOwner = new Owner().builder().id(2L).build();
    MeowType meowType = MeowType.INTERCALADO;
    MeowType editedMeowType = MeowType.UMCURTO;
    Treatment treatment = new Treatment().builder().owner(owner).meow(meowType).cat(cat).build();

    @BeforeEach
    public void setUp() throws Exception {
        Mockito.when(catsServiceImplements.getCat(cat.getId())).thenReturn(cat);
        Mockito.when(ownerServiceImplements.getOwner(owner.getId())).thenReturn(owner);
    }

    @Test
    void ShouldCreateNewTreatment() {
        treatmentServiceImplements.NewTreatment(owner.getId(), cat.getId(), meowType);
        ArgumentCaptor<Treatment> treatCaptor = ArgumentCaptor.forClass(Treatment.class);
        Mockito.verify(treatmentRepository).save(treatCaptor.capture());
        assertEquals(treatCaptor.getValue().getOwner(), owner);
        assertEquals(treatCaptor.getValue().getCat(), cat);
        assertEquals(treatCaptor.getValue().getMeow(), meowType);
    }

    @Test
    void ShouldThrowsException() {
        treatmentServiceImplements.NewTreatment(owner.getId(), cat.getId(), meowType);
        ArgumentCaptor<Treatment> treatCaptor = ArgumentCaptor.forClass(Treatment.class);
        given(treatmentRepository.save(treatCaptor.capture())).willThrow(new RuntimeException());
        assertThatThrownBy(() -> treatmentServiceImplements.NewTreatment(owner.getId(), cat.getId(), meowType))
                .isInstanceOf(AppRepositoryException.class)
                .hasMessageContaining("Could not create treatment");
    }

    @Test
    void treat() {
    }

    @Test
    void consultTreatment() {
    }

    @Test
    void getTreatment() {
    }

    @Test
    void editTreatment() {
    }

    @Test
    void testEditTreatment() {
    }

    @Test
    void deleteTreatment() {
    }
}