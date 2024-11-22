package br.aluno.ueg.memo_io_back.mappers;

import br.aluno.ueg.memo_io_back.models.NoteModel;
import br.aluno.ueg.memo_io_back.models.dtos.NoteCreateDTO;
import br.aluno.ueg.memo_io_back.models.dtos.NoteUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

@Component
public class NoteMapper {
    public NoteModel toModel(@Valid NoteCreateDTO noteUpdateDTO) {
        NoteModel noteModel = new NoteModel();
        noteModel.setTitle(noteUpdateDTO.getTitle());
        noteModel.setContent(noteUpdateDTO.getContent());
        return noteModel;
    }
    public NoteUpdateDTO toDTO(NoteModel noteModel) {
        NoteUpdateDTO noteUpdateDTO = new NoteUpdateDTO();
        noteUpdateDTO.setTitle(noteModel.getTitle());
        noteUpdateDTO.setContent(noteModel.getContent());
        return noteUpdateDTO;
    }
}
