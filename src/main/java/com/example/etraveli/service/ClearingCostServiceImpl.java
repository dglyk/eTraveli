package com.example.etraveli.service;

import com.example.etraveli.domain.ClearingCost;
import com.example.etraveli.dto.ClearingCostDTO;
import com.example.etraveli.dto.ClearingCostTransformer;
import com.example.etraveli.repositories.ClearingCostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClearingCostServiceImpl implements ClearingCostService {

    private final ClearingCostRepository clearingCostRepository;

    @Override
    public List<ClearingCost> findAllClearingCosts() {
        return (List<ClearingCost>) clearingCostRepository.findAll();
    }

    @Override
    public ClearingCost findClearCostById(Long id) {
        return clearingCostRepository.findById(id).orElse(null);
    }

    @Override
    public ClearingCost addClearingCost(ClearingCost clearingCost) {
        return clearingCostRepository.save(clearingCost);
    }

    @Override
    public void deleteClearingCostById(ClearingCost clearingCost) {
        clearingCostRepository.delete(clearingCost);
    }

    @Override
    public ClearingCost updateClearingCost(ClearingCost clearingCost) {
        return clearingCostRepository.save(clearingCost);
    }

    @Override
    public ClearingCost updateClearingCost(ClearingCostDTO clearingCost, Long id) {
        Optional<ClearingCost> clearingCostToBeUpdated = clearingCostRepository.findById(id);
        if(clearingCostToBeUpdated.isPresent()) {
            ClearingCost cl = clearingCostToBeUpdated.get();
            cl.setCost(clearingCost.getCost());
            cl.setId(id);
            cl.setCurrency(clearingCost.getCurrency());
            cl.setIssuingCountry(clearingCost.getIssuingCountry());
            return clearingCostRepository.save(cl);
        } else {
            return null;
        }
    }

    @Override
    @Cacheable(value="ClearingCost", key="#countryCode")
    public ClearingCost findClearingCostByCountryCode(String countryCode) {
         if(clearingCostRepository.findClearingCostByIssuingCountry(countryCode).isPresent()) {
             return clearingCostRepository.findClearingCostByIssuingCountry(countryCode).get();
         } else {
             return clearingCostRepository.findClearingCostByIssuingCountry("OT").get();
         }
    }

    @Override
    public ClearingCost saveClearingCost(ClearingCostDTO clearingCostDTO) {
        return clearingCostRepository.save(ClearingCostTransformer.dtoToObject(clearingCostDTO));
    }
}
