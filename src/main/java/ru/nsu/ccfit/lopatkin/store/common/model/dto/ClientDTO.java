package ru.nsu.ccfit.lopatkin.store.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientDTO extends BaseDTO {

    public ClientDTO(Long id, String name, String email) {
        super(id);
        this.name = name;
        this.email = email;
    }

    @NotBlank(message = "Имя не может быть пустым")
    @Size(min = 2, max = 50, message = "Имя должно быть длиннее 1-го символа, но менее 50")
    private String name;

    @NotBlank(message = "email не может быть пустым")
    @Email(message = "Неверный формат email адреса")
    private String email;
}
