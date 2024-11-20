package br.aluno.ueg.memo_io_back.mappers;

import br.aluno.ueg.memo_io_back.models.NoteModel;
import br.aluno.ueg.memo_io_back.models.dtos.NoteDTO;
import org.springframework.stereotype.Component;

@Component
public class NoteMapper {
    public NoteModel toModel(NoteDTO noteDTO) {
        NoteModel noteModel = new NoteModel();
        noteModel.setTitle(noteDTO.getTitle());
        noteModel.setContent(noteDTO.getContent());
        return noteModel;
    }
    public NoteDTO toDTO(NoteModel noteModel) {
        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setTitle(noteModel.getTitle());
        noteDTO.setContent(noteModel.getContent());
        return noteDTO;
    }
}
