package com.lorencini.bio.services.impl;

import com.lorencini.bio.domain.Client;
import com.lorencini.bio.dto.ClientResponseDTO;
import com.lorencini.bio.repositories.ClientRepository;
import com.lorencini.bio.services.ClientService;
import com.lorencini.bio.services.exceptions.DataIntegratyViolationException;
import com.lorencini.bio.services.exceptions.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ClientServiceImpl implements ClientService {

    private ClientRepository repo;

    public ClientServiceImpl(ClientRepository clientRepository) {
        super();
        this.repo = clientRepository;
    }

    @Override
    public Client insert(Client obj) {
        obj.setId(null);
        return repo.save(obj);
    }

    @Override
    public Page<Client> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
        PageRequest pageRequest = PageRequest.of(
                page,
                linesPerPage,
                org.springframework.data.domain.Sort.Direction.valueOf(direction),
                orderBy);
        return repo.findAll(pageRequest);
    }

    @Override
    public List<Client> findAll() {
        return repo.findAll();
    }

    @Override
    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegratyViolationException("Objeto Id: " + id + " não pode ser deletado pois possui associações, TIPO: " + Client.class.getName());
        }
    }

    @Override
    public Client find(Integer id) {
        Optional<Client> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Client.class.getName()));
    }

    @Override
    public void update(Client clientRequest) {
        Client newClient = find(clientRequest.getId());
        Client.updateWith(newClient, clientRequest);
        repo.save(newClient);
    }


}
