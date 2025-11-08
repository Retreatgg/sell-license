package com.axelor.apps.selllicenseplates2.controller;

import com.axelor.apps.selllicenseplates2.dto.CarNumberLotCreateAndRegisterRequest;
import com.axelor.apps.selllicenseplates2.dto.CarNumberLotCreateRequest;
import com.axelor.apps.selllicenseplates2.dto.CarNumberLotDto;
import com.axelor.apps.selllicenseplates2.dto.CarNumberLotUpdateRequest;
import com.axelor.apps.selllicenseplates2.service.CarNumberLotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car-number-lots")
@RequiredArgsConstructor
public class CarNumberLotController {

    private final CarNumberLotService carNumberLotService;

    @PostMapping
    public ResponseEntity<CarNumberLotDto> createCarNumberLots(@RequestBody CarNumberLotCreateRequest request) {
        return ResponseEntity.ok(carNumberLotService.createCarNumberLots(request));
    }

    @PostMapping("/create-and-register")
    public void createAndRegisterCarNumberLots(@RequestBody CarNumberLotCreateAndRegisterRequest request) {
        carNumberLotService.createCarNumberLotAndRegister(request);
    }

    @GetMapping
    public ResponseEntity<List<CarNumberLotDto>> getCarNumberLots(
            @RequestParam(required = false, defaultValue = "0", name = "regionId") Long regionId,
            @RequestParam(required = false, defaultValue = "false", name = "identicalNumber") Boolean identicalNumbers,
            @RequestParam(required = false, defaultValue = "false",  name = "identicalLetters") Boolean identicalLetters,
            @RequestParam(required = false, defaultValue = "date_desc", name = "sortByDate") String sort
    ) {
        return ResponseEntity.ok(carNumberLotService.getCarNumberLots(regionId, identicalNumbers, identicalLetters, sort));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarNumberLotDto> getCarNumberLotById(@PathVariable(name = "id") Long id) {
        CarNumberLotDto carNumberLot = carNumberLotService.getCarNumberLotById(id);
        return ResponseEntity.ok(carNumberLot);
    }

    @GetMapping("/my")
    public ResponseEntity<List<CarNumberLotDto>> getMyCarNumberLots() {
        return ResponseEntity.ok(carNumberLotService.getMyCarNumberLots());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarNumberLotDto> updateCarNumberLot(
            @PathVariable(name = "id") Long id,
            @RequestBody CarNumberLotUpdateRequest request) {
        CarNumberLotDto updatedCarNumberLot = carNumberLotService.updateCarNumberLot(id, request);
        return ResponseEntity.ok(updatedCarNumberLot);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarNumberLot(@PathVariable(name = "id") Long id) {
        carNumberLotService.deleteCarNumberLot(id);
        return ResponseEntity.noContent().build();
    }
}
