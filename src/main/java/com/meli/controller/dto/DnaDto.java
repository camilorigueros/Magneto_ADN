package com.meli.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter

@Setter

public class DnaDto implements Serializable {
    private static final long serialVersionUID = 1L;

    List<String> dna;

}
