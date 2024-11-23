package br.aluno.ueg.memo_io_back.models.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class UserUpdateDTO {

    @Schema(description = "O nome do usuário", example = "Jeff Lynne")
    @Size(max = 50)
    private String name;

    @Schema(description = "O e-mail do usuário", example = "jefflynne@elo.com")
    @Email
    private String email;

    @Schema(description = "A senha do usuário", example = "yourstruly2095")
    @Size(min = 8)
    private String password;
}
