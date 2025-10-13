package com.axelor.apps.selllicenseplates2.controller;

import com.axelor.apps.selllicenseplates2.dto.CarNumberLotCreateAndRegisterRequest;
import com.axelor.apps.selllicenseplates2.dto.CarNumberLotCreateRequest;
import com.axelor.apps.selllicenseplates2.dto.CarNumberLotDto;
import com.axelor.apps.selllicenseplates2.service.CarNumberLotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car-number-lots")
@RequiredArgsConstructor
public class CarNumberLotController {

    private final CarNumberLotService carNumberLotService;

    @PostMapping
    public void createCarNumberLots(@RequestBody CarNumberLotCreateRequest request) {
        carNumberLotService.createCarNumberLots(request);
    }

    @PostMapping("/create-and-register")
    public void createAndRegisterCarNumberLots(@RequestBody CarNumberLotCreateAndRegisterRequest request) {
        carNumberLotService.createCarNumberLotAndRegister(request);
    }

    @GetMapping
    public ResponseEntity<List<CarNumberLotDto>> getCarNumberLots(
            @RequestParam(required = false, defaultValue = "0") Long regionId,
            @RequestParam(required = false, defaultValue = "false") Boolean identicalNumbers,
            @RequestParam(required = false, defaultValue = "false") Boolean identicalLetters,
            @RequestParam(required = false, defaultValue = "date_desc") String sort
    ) {
        return ResponseEntity.ok(carNumberLotService.getCarNumberLots(regionId, identicalNumbers, identicalLetters, sort));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarNumberLotDto> getCarNumberLotById(@PathVariable(name = "id") Long id) {
        CarNumberLotDto carNumberLot = carNumberLotService.getCarNumberLotById(id);
        return ResponseEntity.ok(carNumberLot);
    }
}
