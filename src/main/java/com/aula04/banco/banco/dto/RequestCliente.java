package com.aula04.banco.banco.dto;

import com.aula04.banco.banco.utils.Senha;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Getter @Setter
public class RequestCliente {
    @NotNull(message = "Não pode ser nulo")
    @NotEmpty
    @Length(min = 2)
    private String nome;
    private String email;
    @Senha
    private String senha;
}
