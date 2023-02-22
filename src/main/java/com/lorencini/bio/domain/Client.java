package com.lorencini.bio.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToMany(mappedBy= "client", cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    private List<BodyComposition> bodyComposition = new ArrayList<>();

    public Client(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static void updateWith(Client newClient, Client clientRequest) {
        newClient.setName(clientRequest.getName());
    }
}
