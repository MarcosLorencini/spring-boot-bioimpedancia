package com.lorencini.bio.services;

import com.lorencini.bio.domain.Client;
import com.lorencini.bio.dto.ClientResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    Client insert(Client obj);

    Page<Client> findPage(Integer page, Integer linesPerPage, String direction, String orderBy);

    List<Client> findAll();

    void delete(Integer id);

    Client find(Integer id);

    void update(Client newRequest);





}
