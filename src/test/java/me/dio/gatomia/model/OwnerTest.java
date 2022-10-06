package me.dio.gatomia.model;

import me.dio.gatomia.handler.AppInvalidModelException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OwnerTest {

    Owner owner = new Owner();
    List<Cats> cat = List.of(new Cats());
    List<Treatment> treatment = List.of(new Treatment());
    Long id = 1L;

    Owner fullOwner = new Owner().builder()
            .TreatedCats(treatment)
            .cats(cat)
            .build();

    @Test
    void setName() {
        owner.setName("foo");
        assertEquals("foo", owner.getName());
    }

    @Test
    void setNameNull() {
        AppInvalidModelException thrownNull = assertThrows(AppInvalidModelException.class, () -> {
            owner.setName(null);
        });
        assertEquals("Name is required", thrownNull.getMessage());
    }

    @Test
    void setNameEmpty() {
        AppInvalidModelException thrownEmpty = assertThrows(AppInvalidModelException.class, () -> {
            owner.setName("");
        });
        assertEquals("Name is required", thrownEmpty.getMessage());
    }

    @Test
    void shouldGetCatsList() {
        assertEquals(fullOwner.getCats(), cat);
    }

    @Test
    void shouldSetId() {
        fullOwner.setId(id);
        assertEquals(fullOwner.getId(), id);
    }

    @Test
    void shouldGetTreatment() {
        assertEquals(fullOwner.getTreatedCats(), treatment);
    }

}