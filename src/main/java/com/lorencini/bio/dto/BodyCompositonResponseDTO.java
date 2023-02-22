
package com.lorencini.bio.dto;

import com.lorencini.bio.domain.BodyComposition;
import com.lorencini.bio.domain.Client;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Builder
public class BodyCompositonResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private double peso;
    private double imc;
    private double gordura;
    private double viceral;
    private double musculo;
    private double calorias;
    private double idade;
    private Date dataMedida;



    public static BodyCompositonResponseDTO transformaEmDTO(BodyComposition composition) {
        return BodyCompositonResponseDTO.builder()
                .id(composition.getId())
                .calorias(composition.getCalorias())
                .dataMedida(composition.getDataMedida())
                .gordura(composition.getGordura())
                .idade(composition.getIdade())
                .peso(composition.getPeso())
                .viceral(composition.getViceral())
                .imc(composition.getImc())
                .build();
    }


}
