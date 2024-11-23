package br.aluno.ueg.memo_io_back.controllers;

import br.aluno.ueg.memo_io_back.mappers.UserMapper;
import br.aluno.ueg.memo_io_back.models.UserModel;
import br.aluno.ueg.memo_io_back.models.dtos.UserDTO;
import br.aluno.ueg.memo_io_back.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/memo-io-back/user")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private UserMapper mapper;

    @PostMapping
    @Operation(description = "Endpoint para adicionar usuários")
    public ResponseEntity<Object> create(@Valid @RequestBody UserDTO dto) {
        try {
            UserModel model = service.create(mapper.toModel(dto));
            return ResponseEntity.ok(model);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    @Operation(description = "Endpoint para listar todos os usuários")
    public ResponseEntity<Object> getAll() {
        try {
            return ResponseEntity.of(Optional.ofNullable(service.getAll()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
