package me.dio.gatomia.model;

import me.dio.gatomia.enumeration.BehaviorType;
import me.dio.gatomia.handler.AppInvalidModelException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CatsTest {

    Owner validOwner = new Owner();
    Cats cat;
    Long catId = 1L;
    String catName = "test";
    Treatment treatment = new Treatment();
    BehaviorType behaviorType = BehaviorType.BRINCALHAO;
    Long cat2Id = 2L;
    String cat2Name = "SecondName";
    Owner validOwner2 = new Owner();
    List<Treatment> treatment2 = List.of(new Treatment());
    BehaviorType behaviorType2 = BehaviorType.ARISCO;
    Cats fullCat = new Cats(cat2Id, cat2Name, validOwner2, treatment2, behaviorType2);

    @BeforeEach
    void setUp() {
        cat = new Cats();
    }

    @Test
    void setValidName() {
        String validName = "nome1";
        cat.setName(validName);

        assertEquals(validName, cat.getName());
    }

    @Test
    void setInvalidName() {
        String blankName = "";
        String nullName = null;

        AppInvalidModelException thrownBlank = assertThrows(AppInvalidModelException.class, () -> {
            cat.setName(blankName);
        });

        Assertions.assertEquals("Name must not be empty", thrownBlank.getMessage());

        AppInvalidModelException thrownNull = assertThrows(AppInvalidModelException.class, () -> {
            cat.setName(nullName);
        });

        Assertions.assertEquals("Name must not be empty", thrownNull.getMessage());
    }

    @Test
    void setOwner() {
        cat.setOwner(validOwner);
        assertEquals(validOwner, cat.getOwner());
    }

    @Test
    void setInvalidOwner() {
        AppInvalidModelException thrownNull = assertThrows(AppInvalidModelException.class, () -> {
            cat.setOwner(null);
        });

        Assertions.assertEquals("Owner must not be null", thrownNull.getMessage());
    }

    @Test
    void setBehavior() {
        cat.setBehavior(BehaviorType.ARISCO);
        assertEquals(BehaviorType.ARISCO, cat.getBehavior());
    }

    @Test
    void getBehavior() {
        assertEquals(BehaviorType.ARISCO, fullCat.getBehavior());
    }

    @Test
    void setInvalidBehavior() {
        AppInvalidModelException thrownNull = assertThrows(AppInvalidModelException.class, () -> {
            cat.setBehavior(null);
        });

        Assertions.assertEquals("Behavior must not be null", thrownNull.getMessage());
    }

    @Test
    void shouldGetId() {
        assertEquals(fullCat.getId(), cat2Id);
    }

    @Test
    void shouldSetId() {
        cat.setId(catId);
        assertEquals(cat.getId(), catId);
    }

    @Test
    void shouldGetName() {
        assertEquals(fullCat.getName(), cat2Name);
    }

    @Test
    void shouldSetName() {
        cat.setName(catName);
        assertEquals(cat.getName(), catName);
    }

    @Test
    void shouldGetOwner() {
        assertEquals(fullCat.getOwner(), validOwner2);
    }

    @Test
    void shouldSetOwner() {
        cat.setOwner(validOwner);
        assertEquals(cat.getOwner(), validOwner);
    }

    @Test
    void shouldGetTreatment() {
        assertEquals(fullCat.getTreatment(), treatment2);
    }

    @Test
    void noArgsConstructor() {
        assertInstanceOf(Cats.class, new Cats());
    }

    @Test
    void AllArgsConstructor() {
        Cats catConstructor = new Cats(catId, catName, validOwner, List.of(treatment), behaviorType);
        assertEquals(catConstructor.getId(), catId);
        assertEquals(catConstructor.getName(), catName);
        assertEquals(catConstructor.getOwner(), validOwner);
        assertEquals(catConstructor.getTreatment(), List.of(treatment));
        assertEquals(catConstructor.getBehavior(), behaviorType);
        assertInstanceOf(Cats.class, catConstructor);
    }

    @Test
    void catsBuilder() {
        Cats catBuilder = Cats.builder()
                .id(catId)
                .name(catName)
                .owner(validOwner)
                .treatment(List.of(treatment))
                .behavior(behaviorType)
                .build();
        assertEquals(catBuilder.getId(), catId);
        assertEquals(catBuilder.getName(), catName);
        assertEquals(catBuilder.getOwner(), validOwner);
        assertEquals(catBuilder.getTreatment(), List.of(treatment));
        assertEquals(catBuilder.getBehavior(), behaviorType);
        assertInstanceOf(Cats.class, catBuilder);
    }

}