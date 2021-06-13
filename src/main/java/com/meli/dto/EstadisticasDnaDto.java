package com.meli.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadisticasDnaDto {
    @JsonProperty("count_mutant_dna")
    private long amountMutantDna;

    @JsonProperty("count_human_dna")
    private long amountHumanDna;

    private float ratio;
}
