package me.dio.gatomia.repository;

import me.dio.gatomia.model.Cats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatsRepository extends JpaRepository<Cats, Long> {
    Cats findByName(String catName);
}
