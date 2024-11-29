package br.aluno.ueg.memo_io_back.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Data
@Entity
@Table(name = "NOTE")
public class NoteModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @NotBlank
    @Size(max = 100)
    @Column(name = "title", nullable = false)
    private String title;

    @NotBlank
    @Size(max = 5000)
    @Column(length = 5000, name = "content", nullable = false)
    private String content;

    @NotBlank
    @Size(max = 20)
    @Column(name = "color", nullable = false)
    private String color;

    @JsonBackReference
    @NotNull
    @ManyToOne
    @JoinColumn(name = "author_id", updatable = false, nullable = false)
    private UserModel author;
}
