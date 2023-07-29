package it.jdk.openlab.lovecraftmoviemanagementsql.repository.movie;

import it.jdk.openlab.lovecraftmoviemanagementsql.entities.MovieEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MovieSpringDataRepository extends JpaRepository<MovieEntity, Integer> {
    @Query("SELECT m FROM MovieEntity m WHERE m.title = :title")
    public Optional<MovieEntity> findByTitle(@Param("title") String title);
    @Query("SELECT m FROM MovieEntity m")
    public Page<MovieEntity> findAll(Pageable pageable);
}
