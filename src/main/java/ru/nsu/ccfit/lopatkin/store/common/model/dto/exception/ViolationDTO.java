package ru.nsu.ccfit.lopatkin.store.common.model.dto.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ViolationDTO {

    private String name;
    private String message;

}
