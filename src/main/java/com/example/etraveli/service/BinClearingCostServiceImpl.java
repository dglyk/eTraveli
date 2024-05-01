package com.example.etraveli.service;

import com.example.etraveli.domain.CardInfo;
import com.example.etraveli.dto.CardInfoDTO;
import com.example.etraveli.repositories.CardInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BinClearingCostServiceImpl implements BinClearingCostService {

    private final CardInfoRepository cardInfoRepository;
    private final BinListApiClient binListApiClient;

    //The bin number is coming with the request. First we try to fetch it
    //from cache with @Cacheable
    @Override
    @Cacheable(value = "CardInfo", key = "#binNumber")
    public CardInfoDTO findClearingCostByCountryCode(String binNumber) {
        //If not found in cache, we try and get it from DB.
        Optional<CardInfo> cardInfoOptional = cardInfoRepository.findByBin(binNumber);
        String countryCode;
        if (cardInfoOptional.isEmpty()) {
            //If not found in DB we make the actual call.
            countryCode = binListApiClient.makeCall(binNumber);
            CardInfo cardInfo = new CardInfo();
            cardInfo.setBin(binNumber);
            cardInfo.setCountry(countryCode);
            //If we had to do the call, we also persist the value for future use.
            cardInfoRepository.save(cardInfo);
        } else {
            //If found in DB it will be returned and also
            countryCode = cardInfoOptional.get().getCountry();
        }
        return CardInfoDTO.builder().countryCode(countryCode).binNumber(binNumber).build();
    }
}
