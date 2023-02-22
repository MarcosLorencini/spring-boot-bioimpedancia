package com.lorencini.bio.repositories;

import com.lorencini.bio.domain.BodyComposition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BodyCompositionRepository extends JpaRepository<BodyComposition, Integer> {

    List<BodyComposition> findByClientId(Integer clientId);

}
