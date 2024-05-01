package com.example.etraveli.repositories;

import com.example.etraveli.domain.CardInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardInfoRepository extends CrudRepository<CardInfo, Long> {

    @Override
    Optional<CardInfo> findById(Long id);

    Optional<CardInfo> findByBin(String bin);
}
