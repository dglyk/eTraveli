package com.example.etraveli;

import com.example.etraveli.domain.ClearingCost;
import com.example.etraveli.dto.ClearingCostDTO;
import com.example.etraveli.dto.ClearingCostTransformer;
import com.example.etraveli.dto.RequestQueue;
import com.example.etraveli.repositories.ClearingCostRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.time.Duration;

@SpringBootApplication
@EnableCaching
@EnableAsync
public class ETraveliApplication {

    private static final Logger log = LoggerFactory.getLogger(
            ETraveliApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ETraveliApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public RequestQueue requestQueue() {
        return new RequestQueue(5, Duration.ofHours(1));
    }


    @Bean
    public CommandLineRunner demo(ClearingCostRepository repository) {
        return args -> {
            //Initiating the local database.

            if (!repository.findAll().iterator().hasNext()) {
                ClearingCostDTO usaDTO = ClearingCostDTO.builder().cost(5).issuingCountry("US").currency("USD").build();
                ClearingCostDTO grDTO = ClearingCostDTO.builder().cost(15).issuingCountry("GR").currency("USD").build();
                ClearingCostDTO otherDTO = ClearingCostDTO.builder().cost(10).issuingCountry("OT").currency("USD").build();


                ClearingCost usClearingCost = repository.save(ClearingCostTransformer.dtoToObject(usaDTO));
                ClearingCost grClearingCost = repository.save(ClearingCostTransformer.dtoToObject(grDTO));
                ClearingCost otherClearingCost = repository.save(ClearingCostTransformer.dtoToObject(otherDTO));

            }

            log.info("ClearingCost found with findAll():");
            log.info("-------------------------------");
            repository.findAll().forEach(cost -> {
                log.info(cost.toString());
            });
            log.info("");

        };
    }
}
