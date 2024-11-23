package br.aluno.ueg.memo_io_back.controllers;

import br.aluno.ueg.memo_io_back.mappers.UserMapper;
import br.aluno.ueg.memo_io_back.models.UserModel;
import br.aluno.ueg.memo_io_back.models.dtos.UserCreateDTO;
import br.aluno.ueg.memo_io_back.models.dtos.UserUpdateDTO;
import br.aluno.ueg.memo_io_back.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/memo-io-back/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping
    @Operation(description = "Endpoint para adicionar um usuário")
    public ResponseEntity<Object> create(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        try {
            UserModel user = userService.create(userMapper.toUserModel(userCreateDTO));
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    @Operation(description = "Endpoint para listar todos os usuários")
    public ResponseEntity<Object> getAll() {
        try {
            List<UserModel> users = userService.getAll();
            if (users == null || users.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
            }
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @Operation(description = "Endpoint para exibir os dados de um usuário pelo ID")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        try {
            Optional<UserModel> user = userService.getById(id);
            if (user.isPresent()) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(description = "Endpoint para atualizar os dados de um usuário pelo ID")
    public ResponseEntity<Object> updateById(@PathVariable Long id, @Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        try {
            Optional<UserModel> user = userService.updateById(id, userMapper.toUserModel(userUpdateDTO));
            if (user.isPresent()) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Endpoint para deletar um usuário pelo ID")
    public ResponseEntity<Object> deleteById(@PathVariable("id") long id) {
        try {
            Optional<UserModel> user = userService.deleteById(id);
            if (user.isPresent()) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
