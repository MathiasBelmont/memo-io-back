package br.aluno.ueg.memo_io_back.controllers;

import br.aluno.ueg.memo_io_back.mappers.NoteMapper;
import br.aluno.ueg.memo_io_back.models.NoteModel;
import br.aluno.ueg.memo_io_back.models.dtos.NoteCreateDTO;
import br.aluno.ueg.memo_io_back.models.dtos.NoteUpdateDTO;
import br.aluno.ueg.memo_io_back.services.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/memo-io-back/note")
public class NoteController {

    @Autowired
    private NoteService service;

    @Autowired
    private NoteMapper mapper;

    @PostMapping
    @Operation(description = "Endpoint para adicionar notas")
    public ResponseEntity<Object> create(@Valid @RequestBody NoteCreateDTO dto) {
        try {
            NoteModel model = service.create(mapper.toModel(dto));
            return ResponseEntity.ok(model);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    @Operation(description = "Endpoint para listar todas as notas")
    public ResponseEntity<Object> getAll() {
        try {
            return ResponseEntity.of(Optional.ofNullable(service.getAll()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
