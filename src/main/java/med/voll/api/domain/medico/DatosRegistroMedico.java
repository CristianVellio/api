package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import med.voll.api.domain.direccion.DatosDireccion;

public record DatosRegistroMedico(
                @NotBlank String nombre,
                @NotBlank @Email String email,
                @NotBlank String telefono,
                @NotBlank @Pattern(regexp = "\\d{4,8}") String documento,
                @NotNull Especialidad especialidad,
                @NotNull @Valid DatosDireccion direccion) {

}
