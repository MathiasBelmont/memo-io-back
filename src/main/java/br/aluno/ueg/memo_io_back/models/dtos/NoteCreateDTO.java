package br.aluno.ueg.memo_io_back.models.dtos;

import br.aluno.ueg.memo_io_back.models.UserModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class NoteCreateDTO {
    @Schema(description = "Título da nota")
    private String title;

    @Schema(description = "Teste da nota")
    private String content;

    @Schema(description = "Autor da nota")
    private UserModel author;
}
