package com.example.etraveli.repositories;

import com.example.etraveli.domain.ClearingCost;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClearingCostRepository extends CrudRepository<ClearingCost, Long> {
    @Override
    Optional<ClearingCost> findById(Long id);

    Optional<ClearingCost> findClearingCostByIssuingCountry(String issuingCountry);
}
