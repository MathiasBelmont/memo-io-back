package br.aluno.ueg.memo_io_back.services;

import br.aluno.ueg.memo_io_back.models.NoteModel;

import java.util.List;

public interface INoteService {
    NoteModel create(NoteModel note);
    List<NoteModel> getAll();
    NoteModel get(Long id);
    NoteModel updateById(Long id);
    NoteModel deleteById(Long id);
    void updateBD(NoteModel noteOld, NoteModel noteNew);
}
