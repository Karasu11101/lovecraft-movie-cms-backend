package it.jdk.openlab.lovecraftmoviemanagementsql.repository.user;

import it.jdk.openlab.lovecraftmoviemanagementsql.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserSpringDataRepository extends JpaRepository<UserEntity, Integer> {
    @Query("select u from UserEntity u where u.id = :id")
    public Optional<UserEntity> findById(@Param("id") Integer id);
    @Query("select u from UserEntity u where u.username = :username")
    public Optional<UserEntity> findByUsername(@Param("username") String username);

//    public List<Book> showFavoriteBooks(Integer id);
//    public List<Movie> showFavoriteMovies(Integer id);

}
