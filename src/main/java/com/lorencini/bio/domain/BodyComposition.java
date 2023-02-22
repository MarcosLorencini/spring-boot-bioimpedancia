package com.lorencini.bio.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BodyComposition implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private double peso;
    private double imc;
    private double gordura;
    private double viceral;
    private double musculo;
    private double calorias;
    private double idade;

    @JsonFormat(pattern="dd/MM/yyyy hh:mm")
    private Date dataMedida;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="client_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Client client;

    public BodyComposition(Integer id, double peso, double imc, double gordura, double viceral, double musculo, double calorias, double idade, Date dataMedida) {
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

    public static BodyComposition updateWith(BodyComposition bodyComposition) {

        return BodyComposition.builder()
                .id(bodyComposition.getId())
                .calorias(bodyComposition.getCalorias())
                .dataMedida(bodyComposition.getDataMedida())
                .gordura(bodyComposition.getGordura())
                .idade(bodyComposition.getIdade())
                .peso(bodyComposition.getPeso())
                .viceral(bodyComposition.getViceral())
                .imc(bodyComposition.getImc())
                .build();
    }
}
