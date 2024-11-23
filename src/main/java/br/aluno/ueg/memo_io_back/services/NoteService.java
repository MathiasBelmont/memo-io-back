package br.aluno.ueg.memo_io_back.services;

import br.aluno.ueg.memo_io_back.models.NoteModel;
import br.aluno.ueg.memo_io_back.repositories.NoteRepository;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private NoteRepository repository;

    public NoteModel create(@Valid NoteModel note) {
        return repository.save(note);
    }

    public List<NoteModel> getAll() {
        return repository.findAll();
    }

    public Optional<NoteModel> getById(Long id) {
        return validateNoteExists(id);
    }

    public Optional<NoteModel> updateById(Long id, @Valid NoteModel noteUpdate) {
        Optional<NoteModel> noteOpt = validateNoteExists(id);
        if (noteOpt.isPresent()) {
            NoteModel note = noteOpt.get();
            updateDataDB(note, noteUpdate);
            repository.save(note);
            return Optional.of(note);
        }
        return Optional.empty();
    }

    public Optional<NoteModel> deleteById(Long id) {
        Optional<NoteModel> noteOpt = validateNoteExists(id);
        if (noteOpt.isPresent()) {
            NoteModel note = noteOpt.get();
            repository.delete(note);
            return Optional.of(note);
        }
        return Optional.empty();
    }

    public Optional<NoteModel> validateNoteExists(Long id) {
        NoteModel noteModel = null;
        if (Objects.nonNull(id)) {
            noteModel = this.internalGet(id);
        }
        return Optional.ofNullable(noteModel);
    }

    public NoteModel internalGet(Long id) {
        Optional<NoteModel> noteModel = repository.findById(id);
        return noteModel.orElse(null);
    }

    public void updateDataDB(@Valid NoteModel note, @Valid NoteModel noteUpdate) {
        if (Objects.nonNull(noteUpdate.getTitle())) {
            note.setTitle(noteUpdate.getTitle());
        }
        if (Objects.nonNull(noteUpdate.getContent())) {
            note.setContent(noteUpdate.getContent());
        }
    }
}
