package it.jdk.openlab.lovecraftmoviemanagementsql.repository.book;

import it.jdk.openlab.lovecraftmoviemanagementsql.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookSpringDataRepository extends JpaRepository<BookEntity, Integer> {

    @Query("select b from BookEntity b where b.id = :id")
    public Optional<BookEntity> findById(@Param("id") Integer id);
    @Query("select b from BookEntity b")
    public List<BookEntity> findAll();

//    @Query("select b from BookEntity b")
//    public Page<BookEntity> findAll(Pageable pageable);
}
