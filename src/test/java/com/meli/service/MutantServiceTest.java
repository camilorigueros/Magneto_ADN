package com.meli.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@RunWith(SpringRunner.class)
class MutantServiceTest {

    MutanteService mutantService = new MutanteService();

    @Test
    @DisplayName("Prueba inicial")
    void start() {
        List<String> dnaData = Arrays.asList("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG");
        assertTrue(mutantService.isMutate(dnaData));
    }

    @Test
    @DisplayName("Prueba de diagonales derecha izquierda")
    void returntruediagonalderechasuperior() {
        List<String> dnaData = Arrays.asList("ATGCGA","CAGTAC","TTAAGT","AGAAGG","CCCCTA","TCACTG");
        assertTrue(mutantService.isMutate(dnaData));
    }

    @Test
    @DisplayName("Prueba de diagonales izquierda derecha")
    void returntruediagonalderechainferior() {
        List<String> dnaData = Arrays.asList("ATGCGA","ATGCGA","TCAGAT","TCAATC","GGAGTC","GGTTAA");
        assertFalse(mutantService.isMutate(dnaData));
    }

    @Test
    @DisplayName("Prueba de diagonales izquierda superior")
    void returntruediagonalizquierdasuperior() {
        List<String> dnaData = Arrays.asList("ATGCTA","ATGTGT","TCTGAT","TTAATC","GGAGTC","GGTTAA");
        assertFalse(mutantService.isMutate(dnaData));
    }

    @Test
    @DisplayName("Prueba de diagonales izquierda inferior")
    void returntruediagonalizquierdainferior() {
        List<String> dnaData = Arrays.asList("ATGCCA","ATGTGT","TCTGAT","TTAATC","GGTGTC","GGTTAA");
        assertFalse(mutantService.isMutate(dnaData));
    }


}
