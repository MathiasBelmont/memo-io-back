package br.aluno.ueg.memo_io_back.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "NOTE")
public class NoteModel {
    @Id
    @SequenceGenerator(
            name = "note_sequence",
            sequenceName = "note_sequence_bd",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "note_sequence"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @NotNull
    @Column(name = "creation_date", updatable = false, nullable = false)
    private LocalDate creationDate;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @Column(name = "content", nullable = false)
    private String content;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "author", updatable = false, nullable = false)
    private UserModel author;
}
