package com.app.trinsi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PictureDTO {

    private Long id;

    @NotNull(message = "Path can not be null")
    @Size(min = 1, message = "Picture path must be at least 1 character")
    private String path;

    @NotNull(message = "Name can not be null")
    @Size(min = 1, message = "Picture name must be at least 1 character")
    private String name;

}
