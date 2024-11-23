package br.aluno.ueg.memo_io_back.models.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NoteUpdateDTO {

    @Schema(description = "Título da nota", example = "Aprendendo Spring Boot")
    @Size(max = 100, message = "O título da nota não pode ter mais que 100 caracteres")
    private String title;

    @Schema(description = "Conteúdo da nota", example = "Este é um exemplo de nota")
    @Size(max = 5000, message = "O conteúdo da nota não pode ter mais que 5000 caracteres")
    private String content;
}
