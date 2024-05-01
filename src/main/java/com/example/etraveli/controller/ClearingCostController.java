package com.example.etraveli.controller;

import com.example.etraveli.domain.ClearingCost;
import com.example.etraveli.dto.ClearingCostDTO;
import com.example.etraveli.dto.ClearingCostTransformer;
import com.example.etraveli.service.ClearingCostService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ClearingCostController {

    private final ClearingCostService clearingCostService;

    @PostMapping("/clearing_costs")
    public ClearingCostDTO saveClearingCost(
            @Valid @RequestBody ClearingCostDTO clearingCostDTO) {
        return ClearingCostTransformer.objectToDTO(clearingCostService.saveClearingCost(clearingCostDTO));
    }

    @GetMapping("/clearing_costs")
    public List<ClearingCostDTO> fetchClearingCosts() {
        return clearingCostService.findAllClearingCosts()
                .stream()
                .map(ClearingCostTransformer::objectToDTO)
                .toList();
    }

    // Update operation
    @PutMapping("/clearing_costs/{id}")
    public ResponseEntity<ClearingCostDTO> updateClearingCost(@RequestBody ClearingCostDTO clearingCostDTO,
                                                              @PathVariable("id") Long clearingCostId) {
        ClearingCost updatedClearingCost = clearingCostService.updateClearingCost(
                clearingCostDTO, clearingCostId);
        if (updatedClearingCost != null) {
            return ResponseEntity.ok().body(ClearingCostTransformer.objectToDTO(updatedClearingCost));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ClearingCostDTO());
        }
    }

    // Delete operation
    @DeleteMapping("/clearing_costs/")
    public ResponseEntity deleteClearingCostById(@Valid @RequestBody ClearingCostDTO clearingCostDTO) {
        clearingCostService.deleteClearingCostById(ClearingCostTransformer.dtoToObject(clearingCostDTO));
        return ResponseEntity.status(HttpStatus.OK).body("");
    }
}
