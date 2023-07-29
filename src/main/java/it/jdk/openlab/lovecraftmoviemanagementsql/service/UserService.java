package it.jdk.openlab.lovecraftmoviemanagementsql.service;

import it.jdk.openlab.lovecraftmoviemanagementsql.$exception.SuperException;
import it.jdk.openlab.lovecraftmoviemanagementsql.entities.UserEntity;
import it.jdk.openlab.lovecraftmoviemanagementsql.models.User;
import it.jdk.openlab.lovecraftmoviemanagementsql.repository.user.UserSpringDataRepository;
import it.jdk.openlab.lovecraftmoviemanagementsql.validation.user.group.UserValidationGroup.CreateValidationGroup;
import it.jdk.openlab.lovecraftmoviemanagementsql.validation.user.group.UserValidationGroup.DeleteValidationGroup;
import it.jdk.openlab.lovecraftmoviemanagementsql.validation.user.group.UserValidationGroup.UpdateValidationGroup;
import it.jdk.openlab.lovecraftmoviemanagementsql.validation.user.model.UserValidationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserSpringDataRepository userRepository;
    private final UserValidationModel userValidation;

    @Autowired
    public UserService(UserSpringDataRepository userRepository, UserValidationModel userValidation) {
        this.userRepository = userRepository;
        this.userValidation = userValidation;
    }

    @Transactional(readOnly = false)
    public User insertUser(User user) throws SuperException {
        this.userValidation.validate(user, "One or more arguments are invalid", CreateValidationGroup.class);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss z");
        String registrationDate = ZonedDateTime.now().format(formatter);
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getUsername());
        userEntity.setName(user.getName());
        userEntity.setSurname(user.getSurname());
        userEntity.setEmail(user.getEmail());
        userEntity.setDateOfBirth(user.getDateOfBirth());
        userEntity.setPassword(user.getPassword());
        userEntity.setRegistrationDate(registrationDate);
        userEntity.setNote(user.getNote());
        userEntity.setRole("user");
//        userEntity.setFavoriteBooks(new ArrayList<>());
//        userEntity.setFavoriteMovies(new ArrayList<>());

        this.userRepository.save(userEntity);

        user.setRole(userEntity.getRole());
        user.setRegistrationDate(registrationDate);
        user.setId(userEntity.getId());
        user.setVersion(0);

        return user;
    }

    @Transactional(readOnly = false)
    public User updateUser(User user) throws SuperException {
        this.userValidation.validate(user, "One or more arguments are invalid", UpdateValidationGroup.class);

        Optional<UserEntity> userEntity = this.userRepository.findById(user.getId());

        UserEntity userEntityObj = userEntity.orElseThrow(
                () -> new RuntimeException("User with id " + user.getId() + " not found"));
        if(userEntityObj.getVersion() != user.getVersion()) {
            throw new OptimisticLockingFailureException("Entity version and DTO version do not match");
        }
        userEntityObj.setUsername(user.getUsername());
        userEntityObj.setEmail(user.getEmail());
        userEntityObj.setPassword(user.getPassword());
        userEntityObj.setNote(user.getNote());

        user.setVersion(user.getVersion() + 1);

        return user;
    }

    @Transactional(readOnly = false)
    public void deleteUser(User user) throws SuperException {
        this.userValidation.validate(user, "A user with id " + user.getId() + " was not found", DeleteValidationGroup.class);

        Optional<UserEntity> userEntity = this.userRepository.findById(user.getId());
        UserEntity userEntityObj = userEntity.orElseThrow(
                () -> new RuntimeException("User with id " + user.getId() + " not found"));
        if(userEntityObj.getVersion() != user.getVersion()) {
            throw new OptimisticLockingFailureException("Entity version and DTO version do not match");
        }
        this.userRepository.delete(userEntityObj);
    }

//    @Transactional(readOnly = false)
//    public List<>

    public Optional<User> showUserById(User user) {
        Optional<UserEntity> userEntity = this.userRepository.findById(user.getId());
        UserEntity userEntityObj = userEntity.orElseThrow(
                () -> new RuntimeException("User with id " + user.getId() + " not found"));

//        List<Book> favoriteBooks = this.convertBookEntity(userEntityObj.getFavoriteBooks());
//        List<Movie> favoriteMovies = this.convertMovieEntity(userEntityObj.getFavoriteMovies());

//        return Optional.of(new User(userEntityObj.getId(), userEntityObj.getUsername(), userEntityObj.getName(),
//                userEntityObj.getSurname(), userEntityObj.getEmail(), userEntityObj.getPassword(),
//                userEntityObj.getDateOfBirth(), userEntityObj.getNote(), userEntityObj.getRegistrationDate(),
//                favoriteMovies, favoriteBooks));

        return Optional.of(new User(userEntityObj.getId(), userEntityObj.getUsername(), userEntityObj.getName(),
                userEntityObj.getSurname(), userEntityObj.getEmail(), userEntityObj.getPassword(),
                userEntityObj.getDateOfBirth(), userEntityObj.getNote(), userEntityObj.getRole(), userEntityObj.getRegistrationDate()));
    }

    public Optional<User> showUserByUsername(String username) {
        Optional<UserEntity> userEntity = this.userRepository.findByUsername(username);
        UserEntity userEntityObj = userEntity.orElseThrow(
                () -> new RuntimeException("User with username \"" + username + "\" not found"));

//        List<Book> favoriteBooks = this.convertBookEntity(userEntityObj.getFavoriteBooks());
//        List<Movie> favoriteMovies = this.convertMovieEntity(userEntityObj.getFavoriteMovies());

//        return Optional.of(new User(userEntityObj.getId(), userEntityObj.getUsername(), userEntityObj.getName(),
//                userEntityObj.getSurname(), userEntityObj.getEmail(), userEntityObj.getPassword(),
//                userEntityObj.getDateOfBirth(), userEntityObj.getNote(), userEntityObj.getRegistrationDate(),
//                favoriteMovies, favoriteBooks));

        return Optional.of(new User(userEntityObj.getId(), userEntityObj.getUsername(), userEntityObj.getName(),
                userEntityObj.getSurname(), userEntityObj.getEmail(), userEntityObj.getPassword(),
                userEntityObj.getDateOfBirth(), userEntityObj.getNote(), userEntityObj.getRole(), userEntityObj.getRegistrationDate()));
    }

//    private List<Book> convertBookEntity(List<BookEntity> books) {
//        List<Book> bookList = new ArrayList<>();
//        for(BookEntity entity : books) {
//            bookList.add(new Book(entity.getId(), entity.getTitle(), entity.getPlot(),
//                    entity.getImage(), entity.getPublished(), entity.getVersion()));
//        }
//        return bookList;
//    }
//
//    private List<Movie> convertMovieEntity(List<MovieEntity> movies) {
//        List<Movie> movieList = new ArrayList<>();
//        for(MovieEntity entity : movies) {
//            movieList.add(new Movie(entity.getId(), entity.getTitle(), entity.getDescription(),
//                    entity.getImage(), entity.getVersion()));
//        }
//        return movieList;
//    }
}
