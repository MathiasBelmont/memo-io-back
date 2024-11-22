package br.aluno.ueg.memo_io_back.services;

import br.aluno.ueg.memo_io_back.models.UserModel;
import br.aluno.ueg.memo_io_back.repositories.UserRepository; // Assuming you have a UserRepository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository repository; // Updated to UserRepository

    @Override
    public UserModel create(UserModel user) {
        user.setId(null);
        return repository.save(user);
    }

    @Override
    public List<UserModel> getAll() {
        return repository.findAll();
    }

    @Override
    public UserModel get(Long id) {
        return repository.findById(id).orElse(null); // Assuming you have a method to find by ID
    }

    @Override
    public UserModel updateById(Long id) {
        // Implement the update logic here
        return null;
    }

    @Override
    public UserModel deleteById(Long id) {
        // Implement the delete logic here
        return null;
    }

    @Override
    public void updateBD(UserModel userOld, UserModel userNew) {
        // Implement the update logic here
    }
}
