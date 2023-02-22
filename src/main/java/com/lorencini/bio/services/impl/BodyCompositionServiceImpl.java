package com.lorencini.bio.services.impl;

import com.lorencini.bio.domain.BodyComposition;
import com.lorencini.bio.domain.Client;
import com.lorencini.bio.dto.BodyCompositionDTO;
import com.lorencini.bio.repositories.ClientRepository;
import com.lorencini.bio.repositories.BodyCompositionRepository;
import com.lorencini.bio.services.BodyCompositionService;
import com.lorencini.bio.services.exceptions.DataIntegratyViolationException;
import com.lorencini.bio.services.exceptions.ObjectNotFoundException;
import lombok.Builder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Builder
public class BodyCompositionServiceImpl implements BodyCompositionService {

    private BodyCompositionRepository compositionRepo;

    private ClientRepository clientRepo;

    public BodyCompositionServiceImpl(BodyCompositionRepository bodyCompositionRepository, ClientRepository clientRepository) {
        super();
        this.compositionRepo = bodyCompositionRepository;
        this.clientRepo = clientRepository;
    }

    @Override
    public BodyComposition findById(Integer id) {
        Optional<BodyComposition> obj = compositionRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: "+id+" Tipo: "+ BodyComposition.class.getName()));
    }

    @Override
    public BodyComposition insert(Integer clientId, BodyComposition bodyCompositionRequest) {
        bodyCompositionRequest.setId(null);
        //nova data com o instate atual
        bodyCompositionRequest.setDataMedida(new Date());
        //usa o id para buscar o aluno inteiro
        BodyComposition bodyComposition = clientRepo.findById(clientId).map(client -> {
            bodyCompositionRequest.setClient(client);
            return compositionRepo.save(bodyCompositionRequest);
        }).orElseThrow(() -> new ObjectNotFoundException("Not found Client with id = " + clientId));
        return bodyComposition;
    }

    @Override
    public List<BodyComposition> findAll(Integer clientId) {
        if (!clientRepo.existsById(clientId)) {
            throw new ObjectNotFoundException("Not found Client with id = " + clientId);
        }
        return compositionRepo.findByClientId(clientId);
    }

    @Override
    public void update(Integer id, BodyComposition newBodyComposition) {
        compositionRepo.findById(id);
        BodyComposition update = BodyComposition.updateWith(newBodyComposition);
        compositionRepo.save(update);
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        try {
            compositionRepo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegratyViolationException("Objeto Id: " + id + " não pode ser deletado pois possui associações, TIPO: " + BodyComposition.class.getName());
        }
    }



}
