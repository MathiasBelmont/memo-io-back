package br.aluno.ueg.memo_io_back.services;

import br.aluno.ueg.memo_io_back.models.UserModel;
import br.aluno.ueg.memo_io_back.repositories.UserRepository;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserModel create(@Valid UserModel userModel) {
        Optional<UserModel> existingUser = validateUserExistsByEmail(userModel.getEmail());

        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        return repository.save(userModel);
    }

    private Optional<UserModel> validateUserExistsByEmail(String email) {
        return repository.findByEmail(email);
    }

    public List<UserModel> getAll() {
        return repository.findAll();
    }

    public Optional<UserModel> getById(Long id) {
        return validateUserExists(id);
    }

    public Optional<UserModel> updateById(Long id, @Valid UserModel userUpdate) {
        Optional<UserModel> userOpt = validateUserExists(id);
        if (userOpt.isPresent()) {
            UserModel user = userOpt.get();
            updateDataDB(user, userUpdate);
            repository.save(user);
            return Optional.of(user);
        }
        return Optional.empty();
    }

    public Optional<UserModel> deleteById(Long id) {
        Optional<UserModel> userOpt = validateUserExists(id);
        if (userOpt.isPresent()) {
            UserModel user = userOpt.get();
            repository.delete(user);
            return Optional.of(user);
        }
        return Optional.empty();
    }

    public Optional<UserModel> validateUserExists(Long id) {
        UserModel userModel = null;
        if (Objects.nonNull(id)) {
            userModel = this.internalGet(id);
        }
        return Optional.ofNullable(userModel);
    }

    public UserModel internalGet(Long id) {
        Optional<UserModel> userModel = repository.findById(id);
        return userModel.orElse(null);
    }

    public void updateDataDB(@Valid UserModel user, @Valid UserModel userUpdate) {
        if (Objects.nonNull(userUpdate.getName())) {
            user.setName(userUpdate.getName());
        }
        if (Objects.nonNull(userUpdate.getEmail())) {
            user.setEmail(userUpdate.getEmail());
        }
        if (Objects.nonNull(userUpdate.getPassword())) {
            user.setPassword(userUpdate.getPassword());
        }
    }
}
