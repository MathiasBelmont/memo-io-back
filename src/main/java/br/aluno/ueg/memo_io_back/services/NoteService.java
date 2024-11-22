package br.aluno.ueg.memo_io_back.services;

import br.aluno.ueg.memo_io_back.models.NoteModel;
import br.aluno.ueg.memo_io_back.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService implements INoteService {

    @Autowired
    private NoteRepository repository;

    @Override
    public NoteModel create(NoteModel note) {
        note.setId(null);
        return repository.save(note);
    }

    @Override
    public List<NoteModel> getAll() {
        return repository.findAll();
    }

    @Override
    public NoteModel get(Long id) {
        return null;
    }

    @Override
    public NoteModel updateById(Long id) {
        return null;
    }

    @Override
    public NoteModel deleteById(Long id) {
        return null;
    }

    @Override
    public void updateBD(NoteModel noteOld, NoteModel noteNew) {

    }
}
