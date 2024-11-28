package br.aluno.ueg.memo_io_back.models.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NoteCreateDTO {

    @Schema(description = "Título da nota", example = "Aprendendo Spring Boot", required = true)
    @NotBlank(message = "O título da nota é obrigatório")
    @Size(max = 100, message = "O título da nota não pode ter mais que 100 caracteres")
    private String title;

    @Schema(description = "Conteúdo da nota", example = "Este é um exemplo de nota", required = true)
    @NotBlank(message = "O conteúdo da nota é obrigatório")
    @Size(max = 5000, message = "O conteúdo da nota não pode ter mais que 5000 carateres")
    private String content;

    @Schema(description = "Cor da nota", example = "yellow", required = true)
    @NotBlank(message = "A cor da nota é obrigatória")
    @Size(max = 20, message = "A cor da nota é obrigatória")
    private String color;

    @Schema(description = "ID do autor da nota", example = "1", required = true)
    @NotNull(message = "É obrigatório que a nota tenha o ID de um autor atribuído")
    private Long authorId;
}
