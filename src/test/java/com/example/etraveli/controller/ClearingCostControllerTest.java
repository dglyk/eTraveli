package com.example.etraveli.controller;

import com.example.etraveli.domain.ClearingCost;
import com.example.etraveli.dto.ClearingCostDTO;
import com.example.etraveli.dto.ClearingCostTransformer;
import com.example.etraveli.service.ClearingCostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ClearingCostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ClearingCostService clearingCostService;

    @Test
    public void saveClearingCost_ValidInput_ShouldReturnCreated() throws Exception {
        ClearingCostDTO clearingCostDTO = new ClearingCostDTO();
        clearingCostDTO.setCost(10);
        clearingCostDTO.setIssuingCountry("US");
        clearingCostDTO.setCurrency("USD");

        ClearingCost savedClearingCost = ClearingCostTransformer.dtoToObject(clearingCostDTO);
        savedClearingCost.setId(1L);

        when(clearingCostService.saveClearingCost(any())).thenReturn(savedClearingCost);

        mockMvc.perform(MockMvcRequestBuilders.post("/clearing_costs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clearingCostDTO)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    public void fetchClearingCosts_ShouldReturnListOfClearingCosts() throws Exception {
        ClearingCostDTO clearingCostDTO1 = new ClearingCostDTO();
        clearingCostDTO1.setId(1L);
        clearingCostDTO1.setCost(10);
        clearingCostDTO1.setIssuingCountry("US");
        clearingCostDTO1.setCurrency("USD");

        ClearingCostDTO clearingCostDTO2 = new ClearingCostDTO();
        clearingCostDTO2.setId(2L);
        clearingCostDTO2.setCost(15);
        clearingCostDTO2.setIssuingCountry("UK");
        clearingCostDTO2.setCurrency("GBP");

        List<ClearingCost> clearingCosts = Arrays.asList(
                ClearingCostTransformer.dtoToObject(clearingCostDTO1),
                ClearingCostTransformer.dtoToObject(clearingCostDTO2)
        );

        when(clearingCostService.findAllClearingCosts()).thenReturn(clearingCosts);

        mockMvc.perform(MockMvcRequestBuilders.get("/clearing_costs")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].cost").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].issuingCountry").value("US"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].currency").value("USD"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].cost").value(15))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].issuingCountry").value("UK"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].currency").value("GBP"));
    }

    @Test
    public void updateClearingCost_ValidInput_ShouldReturnUpdatedClearingCost() throws Exception {
        ClearingCostDTO clearingCostDTO = new ClearingCostDTO();
        clearingCostDTO.setCost(20);
        clearingCostDTO.setIssuingCountry("US");
        clearingCostDTO.setCurrency("USD");

        ClearingCost updatedClearingCost = ClearingCostTransformer.dtoToObject(clearingCostDTO);
        updatedClearingCost.setId(1L);

        when(clearingCostService.updateClearingCost(eq(clearingCostDTO), any())).thenReturn(updatedClearingCost);

        mockMvc.perform(MockMvcRequestBuilders.put("/clearing_costs/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clearingCostDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cost").value(20))
                .andExpect(MockMvcResultMatchers.jsonPath("$.issuingCountry").value("US"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currency").value("USD"));
    }

    @Test
    public void deleteClearingCostById_ValidInput_ShouldReturnNoContent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/clearing_costs/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new ClearingCostDTO())))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }
}
