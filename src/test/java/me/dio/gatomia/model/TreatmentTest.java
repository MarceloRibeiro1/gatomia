package me.dio.gatomia.model;

import me.dio.gatomia.enumeration.BehaviorType;
import me.dio.gatomia.enumeration.MeowType;
import me.dio.gatomia.enumeration.Solutions;
import me.dio.gatomia.handler.AppInvalidModelException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class TreatmentTest {

    Cats cat = new Cats();
    Owner owner = new Owner();
    Treatment treatment = new Treatment().builder().id(1L).build();

    @BeforeEach
    void setUp() {
        cat.setName("cat");
        cat.setId(1L);
        owner.setName("owner");
    }

    @Test
    void treat() {
        cat.setBehavior(BehaviorType.CARINHOSO);
        treatment.setMeow(MeowType.VARIOSCURTOS);
        treatment.setCat(cat);
        assertEquals(treatment.treat(), Solutions.FOME);
        assertTrue(treatment.isTreated());
    }

    @Test
    void NoBehaviorTreat() {
        AppInvalidModelException thrownNull = assertThrows(AppInvalidModelException.class, () -> {
            treatment.setMeow(MeowType.VARIOSCURTOS);
            treatment.treat();
        });
        assertEquals(thrownNull.getMessage(), "Cat is required");
    }

    @Test
    void NoMeowTreat() {
        AppInvalidModelException thrownNull = assertThrows(AppInvalidModelException.class, () -> {
            cat.setBehavior(BehaviorType.CARINHOSO);
            treatment.setCat(cat);
            treatment.treat();
        });
        assertEquals(thrownNull.getMessage(), "Cat meow is required");
    }

    @Test
    void CatAlreadyTreated() {
        cat.setBehavior(BehaviorType.CARINHOSO);
        treatment.setMeow(MeowType.VARIOSCURTOS);
        treatment.setCat(cat);
        treatment.treat()
        assertThatThrownBy(() -> treatment.treat())
                .isInstanceOf(AppInvalidModelException.class)
                .hasMessageContaining("Cat already treated");

    }

    @Test
    void getTreat() {
        cat.setBehavior(BehaviorType.CARINHOSO);
        treatment.setMeow(MeowType.VARIOSCURTOS);
        treatment.setCat(cat);
        assertEquals(treatment.getTreat(), Solutions.FOME);
    }

    @Test
    void getNoBehaviorTreat() {
        AppInvalidModelException thrownNull = assertThrows(AppInvalidModelException.class, () -> {
            treatment.setMeow(MeowType.VARIOSCURTOS);
            treatment.getTreat();
        });
        assertEquals(thrownNull.getMessage(), "Cat is required");
    }

    @Test
    void getNoMeowTreat() {
        AppInvalidModelException thrownNull = assertThrows(AppInvalidModelException.class, () -> {
            cat.setBehavior(BehaviorType.CARINHOSO);
            treatment.setCat(cat);
            treatment.getTreat();
        });
        assertEquals(thrownNull.getMessage(), "Cat meow is required");
    }

    @Test
    void setOwner() {
        treatment.setOwner(owner);
        assertEquals(owner, treatment.getOwner());
    }

    @Test
    void setNullOwner() {
        AppInvalidModelException thrownNull = assertThrows(AppInvalidModelException.class, () -> {
            treatment.setOwner(null);
        });
        assertEquals("Owner must not be null", thrownNull.getMessage());
    }

    @Test
    void setCat() {
        cat.setBehavior(BehaviorType.CARINHOSO);
        treatment.setCat(cat);
        assertEquals(cat, treatment.getCat());
    }

    @Test
    void setNullCat() {
        AppInvalidModelException thrownNull = assertThrows(AppInvalidModelException.class, () -> {
            treatment.setCat(null);
        });
        assertEquals("Cat must not be null", thrownNull.getMessage());
    }

    @Test
    void setCatWithNoBehavior() {
        AppInvalidModelException thrownNull = assertThrows(AppInvalidModelException.class, () -> {
            treatment.setCat(cat);
        });
        assertEquals("Invalid cat: " + cat.getId(), thrownNull.getMessage());
    }

    @Test
    void setMeow() {
        treatment.setMeow(MeowType.INTERCALADO);
        assertEquals(MeowType.INTERCALADO, treatment.getMeow());
    }

    @Test
    void setNullMeow() {
        AppInvalidModelException thrownNull = assertThrows(AppInvalidModelException.class, () -> {
            treatment.setMeow(null);
        });
        assertEquals("Meow must not be null", thrownNull.getMessage());
    }

    @Test
    void shouldGetId() {
        assertEquals(treatment.getId(), 1L);
    }

    @Test
    void shouldSetId() {
        treatment.setId(2L);
        assertEquals(treatment.getId(), 2L);
    }

    @Test
    void ShouldBuild() {
        Treatment treatment1 = Treatment.builder()
                .id(1L)
                .cat(cat)
                .isTreated(false)
                .meow(MeowType.INTERCALADO)
                .owner(owner)
                .build();
        assertEquals(treatment1.getId(), 1L);
        assertFalse(treatment1.isTreated());
        assertEquals(treatment1.getMeow(), MeowType.INTERCALADO);
        assertEquals(treatment1.getOwner(), owner);
        assertInstanceOf(Treatment.class, treatment1);
    }

}