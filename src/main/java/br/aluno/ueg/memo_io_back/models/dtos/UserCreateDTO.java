package br.aluno.ueg.memo_io_back.models.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class UserCreateDTO {

    @Schema(description = "O nome do usuário", example = "Jeff Lynne", required = true)
    @NotBlank(message = "O nome do usuário é obrigatório")
    @Size(max = 100, message = "O nome do usuário não pode ter mais que 100 caracteres")
    private String name;

    @Schema(description = "O e-mail do usuário", example = "jefflynne@elo.com", required = true)
    @NotBlank(message = "O e-mail do usuário é obrigatório")
    @Email(message = "O e-mail do usuário deve ser válido")
    private String email;

    @Schema(description = "A senha do usuário", example = "yourstruly2095", required = true)
    @NotBlank(message = "A senha do usuário é obrigatória")
    @Size(min = 8, message = "A senha do usuário deve ter no mínimo 8 caracteres")
    private String password;

}
