package br.aluno.ueg.memo_io_back.mappers;

import br.aluno.ueg.memo_io_back.models.UserModel;
import br.aluno.ueg.memo_io_back.models.dtos.UserCreateDTO;
import br.aluno.ueg.memo_io_back.models.dtos.UserUpdateDTO;
import jakarta.validation.Valid;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserModel toUserModel(@Valid UserCreateDTO userCreateDTO) {
        UserModel userModel = new UserModel();
        userModel.setName(userCreateDTO.getName());
        userModel.setEmail(userCreateDTO.getEmail());
        userModel.setPassword(userCreateDTO.getPassword());
        return userModel;
    }

    public UserModel toUserModel(@Valid UserUpdateDTO userUpdateDTO) {
        UserModel userModel = new UserModel();
        userModel.setName(userUpdateDTO.getName());
        userModel.setEmail(userUpdateDTO.getEmail());
        userModel.setPassword(userUpdateDTO.getPassword());
        return userModel;
    }
}
