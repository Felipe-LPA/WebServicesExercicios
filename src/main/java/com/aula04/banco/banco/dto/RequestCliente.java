package com.aula04.banco.banco.dto;

import com.aula04.banco.banco.utils.senha.Senha;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Getter @Setter
@AllArgsConstructor
public class RequestCliente {
    @NotNull(message = "Não pode ser nulo")
    @NotEmpty(message = "Não pode ser Vazio")
    @Length(min = 2)
    private String nome;
    private String email;
    @Senha()
    private String senha;
}
