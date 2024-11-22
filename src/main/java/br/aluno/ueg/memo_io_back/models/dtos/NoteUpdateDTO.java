package br.aluno.ueg.memo_io_back.models.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class NoteUpdateDTO {
    @Schema(description = "Título da nota")
    private String title;

    @Schema(description = "Conteúdo da nota")
    private String content;
}
