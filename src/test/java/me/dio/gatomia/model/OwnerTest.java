package me.dio.gatomia.model;

import me.dio.gatomia.handler.AppInvalidModelException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OwnerTest {

    Owner owner = new Owner();

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
}