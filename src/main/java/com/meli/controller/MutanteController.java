package com.meli.controller;


import com.meli.controller.dto.DnaDto;
import com.meli.dto.EstadisticasDnaDto;
import com.meli.service.IMutanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MutanteController {

    @Autowired
    private IMutanteService mutanteService;

    @PostMapping("/mutant")
    public ResponseEntity<Boolean> isMutant(@RequestBody DnaDto dna) {
        if(mutanteService.validarDna(dna.getDna()))
            return new ResponseEntity<>(true, HttpStatus.OK);
        return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
    }

    @GetMapping("/stats")
    public ResponseEntity<EstadisticasDnaDto> stats() {
        return new ResponseEntity<>(mutanteService.estadisticasDna(), HttpStatus.OK);

    }

}
