package br.aluno.ueg.memo_io_back.repositories;

import br.aluno.ueg.memo_io_back.models.NoteModel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<NoteModel, Long> {
    List<NoteModel> findByAuthorId(Long id);
    List<NoteModel> findByAuthorIdAndContentContainingIgnoreCase(Long authorId, String content);
}
