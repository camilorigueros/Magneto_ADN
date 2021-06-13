package com.meli.service;

import com.meli.dto.EstadisticasDnaDto;

import java.util.List;

public interface IMutanteService {
    boolean validarDna(List<String> dna);

    EstadisticasDnaDto estadisticasDna();
}
