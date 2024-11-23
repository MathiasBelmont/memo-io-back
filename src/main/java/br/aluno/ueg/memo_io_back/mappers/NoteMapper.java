package br.aluno.ueg.memo_io_back.mappers;

import br.aluno.ueg.memo_io_back.models.NoteModel;
import br.aluno.ueg.memo_io_back.models.UserModel;
import br.aluno.ueg.memo_io_back.models.dtos.NoteCreateDTO;
import br.aluno.ueg.memo_io_back.models.dtos.NoteUpdateDTO;
import br.aluno.ueg.memo_io_back.services.UserService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NoteMapper {

    @Autowired
    private UserService userService;

    public NoteModel toNoteModel(@Valid NoteCreateDTO noteCreateDTO) {
        NoteModel noteModel = new NoteModel();
        noteModel.setTitle(noteCreateDTO.getTitle());
        noteModel.setContent(noteCreateDTO.getContent());
        UserModel author = userService.internalGet(noteCreateDTO.getAuthorId());
        noteModel.setAuthor(author);
        return noteModel;
    }

    public NoteModel toNoteModel(@Valid NoteUpdateDTO noteUpdateDTO) {
        NoteModel noteModel = new NoteModel();
        noteModel.setTitle(noteUpdateDTO.getTitle());
        noteModel.setContent(noteUpdateDTO.getContent());
        return noteModel;
    }
}
