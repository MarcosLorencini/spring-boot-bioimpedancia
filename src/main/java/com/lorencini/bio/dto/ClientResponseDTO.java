package com.lorencini.bio.dto;

import com.lorencini.bio.domain.Client;
import com.lorencini.bio.domain.BodyComposition;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ClientResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private List<BodyComposition> bodyComposition = new ArrayList<>();

    public static ClientResponseDTO transformaEmDTO(Client client) {
        return new ClientResponseDTO(client.getId(), client.getName(), client.getBodyComposition());
    }

    public ClientResponseDTO(Client client) {
        this.id = client.getId();
        this.name = client.getName();
    }
}
