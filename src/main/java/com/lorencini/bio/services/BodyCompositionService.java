package com.lorencini.bio.services;

import com.lorencini.bio.domain.BodyComposition;
import com.lorencini.bio.domain.Client;

import java.util.List;

public interface BodyCompositionService {

    BodyComposition findById(Integer id);

    BodyComposition insert(Integer alunoId, BodyComposition bodyCompositionRequest);

    List<BodyComposition> findAll(Integer clientId);

    void update(Integer id, BodyComposition bodyCompositionRequest);

    void delete(Integer id);

   /* Page<Aluno> findPage(Integer page, Integer linesPerPage, String direction, String orderBy);

    List<Aluno> findAll();

    void delete(Integer id);

    Aluno update(Integer id, Aluno obj);*/





}
