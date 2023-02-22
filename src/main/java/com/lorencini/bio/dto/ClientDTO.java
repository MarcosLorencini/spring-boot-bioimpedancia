package com.lorencini.bio.dto;

import com.lorencini.bio.domain.Client;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
public class ClientDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 5, max = 50, message = "O tamanho mínimo para o nome é 5 e o máximo é 50")
    private String name;

    public Client transformaParaObjeto() {
                return new Client(id, name);
    }
}
