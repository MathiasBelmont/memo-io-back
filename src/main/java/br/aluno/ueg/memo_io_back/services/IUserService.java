package br.aluno.ueg.memo_io_back.services;

import br.aluno.ueg.memo_io_back.models.UserModel;

import java.util.List;

public interface IUserService {
    UserModel create(UserModel user);
    List<UserModel> getAll();
    UserModel get(Long id);
    UserModel updateById(Long id);
    UserModel deleteById(Long id);
    void updateBD(UserModel userOld, UserModel userNew);
}
