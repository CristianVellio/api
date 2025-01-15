package med.voll.api.domain.direccion;

import jakarta.validation.constraints.*;

public record DatosDireccion(
        @NotBlank String calle,
        @NotBlank String distrito,
        @NotBlank String ciudad,
        @NotBlank String numero,
        @NotBlank String complemento) {

}
