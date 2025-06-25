package br.aluno.ueg.memo_io_back.controllers;

import br.aluno.ueg.memo_io_back.mappers.NoteMapper;
import br.aluno.ueg.memo_io_back.models.NoteModel;
import br.aluno.ueg.memo_io_back.models.UserModel;
import br.aluno.ueg.memo_io_back.models.dtos.NoteCreateDTO;
import br.aluno.ueg.memo_io_back.models.dtos.NoteUpdateDTO;
import br.aluno.ueg.memo_io_back.services.NoteService;
import br.aluno.ueg.memo_io_back.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/memo-io-back/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private UserService userService;

    @Autowired
    private NoteMapper noteMapper;

    @PostMapping
    @Operation(description = "Endpoint para adicionar uma nota")
    public ResponseEntity<Object> create(@Valid @RequestBody NoteCreateDTO noteCreateDTO) {
        try {
            Optional<UserModel> author = userService.validateUserExists(noteCreateDTO.getAuthorId());
            if (author.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
            }
            NoteModel note = noteService.create(noteMapper.toNoteModel(noteCreateDTO));
            return ResponseEntity.status(HttpStatus.CREATED).body(note);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    @Operation(description = "Endpoint para listar todas as notas")
    public ResponseEntity<Object> getAll() {
        try {
            List<NoteModel> notes = noteService.getAll();
            if (notes == null || notes.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
            }
            return ResponseEntity.ok(notes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @Operation(description = "Endpoint para exibir os dados de uma nota pelo ID")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        try {
            Optional<NoteModel> note = noteService.getById(id);
            if (note.isPresent()) {
                return ResponseEntity.ok(note);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/author/{id}")
    @Operation(description = "Endpoint para listar todas as notas de um autor pelo ID")
    public ResponseEntity<Object> getAllByAuthorId(@PathVariable Long id) {
        try {
            List<NoteModel> notes = noteService.getAllByAuthorId(id);
            if (notes == null || notes.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
            }
            return ResponseEntity.ok(notes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/author/{authorId}/search")
    @Operation(description = "Endpoint para buscar notas de um autor pelo conteúdo")
    public ResponseEntity<Object> searchByAuthorAndContent(@PathVariable Long authorId, @RequestParam("content") String content) {
        try {
            Optional<UserModel> author = userService.validateUserExists(authorId);
            if (author.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Autor não encontrado.");
            }
            List<NoteModel> notes = noteService.searchByAuthorAndContent(authorId, content);
            if (notes.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma nota encontrada com o conteúdo fornecido para este autor.");
            }
            return ResponseEntity.ok(notes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(description = "Endpoint para atualizar os dados de uma nota pelo ID")
    public ResponseEntity<Object> updateById(@PathVariable Long id, @Valid @RequestBody NoteUpdateDTO noteUpdateDTO) {
        try {
            Optional<NoteModel> note = noteService.updateById(id, noteMapper.toNoteModel(noteUpdateDTO));
            if (note.isPresent()) {
                return ResponseEntity.ok(note);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Endpoint para deletar uma nota pelo ID")
    public ResponseEntity<Object> deleteById(@PathVariable("id") Long id) {
        try {
            Optional<NoteModel> note = noteService.deleteById(id);
            if (note.isPresent()) {
                return ResponseEntity.ok(note);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}

