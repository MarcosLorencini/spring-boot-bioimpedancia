package com.lorencini.bio.dto;

import com.lorencini.bio.domain.BodyComposition;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

@Data
public class BodyCompositionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    @NotEmpty(message = "Preenchimento obrigatório")
    private double peso;

    @NotEmpty(message = "Preenchimento obrigatório")
    private double imc;

    @NotEmpty(message = "Preenchimento obrigatório")
    private double gordura;

    @NotEmpty(message = "Preenchimento obrigatório")
    private double viceral;

    @NotEmpty(message = "Preenchimento obrigatório")
    private double musculo;

    @NotEmpty(message = "Preenchimento obrigatório")
    private double calorias;

    @NotEmpty(message = "Preenchimento obrigatório")
    private double idade;

    @NotEmpty(message = "Preenchimento obrigatório")
    private Date dataMedida;

    public BodyCompositionDTO(Integer id, double peso, double imc, double gordura, double viceral, double musculo, double calorias, double idade, Date dataMedida) {
        this.id = id;
        this.peso = peso;
        this.imc = imc;
        this.gordura = gordura;
        this.viceral = viceral;
        this.musculo = musculo;
        this.calorias = calorias;
        this.idade = idade;
        this.dataMedida = dataMedida;
    }

    public static BodyComposition transformaParaObjeto(BodyCompositionDTO dto) {
        return BodyComposition.builder()
                .calorias(dto.getCalorias())
                .dataMedida(dto.getDataMedida())
                .gordura(dto.getGordura())
                .idade(dto.getIdade())
                .peso(dto.getPeso())
                .viceral(dto.getViceral())
                .imc(dto.getImc())
                .build();
    }
}
