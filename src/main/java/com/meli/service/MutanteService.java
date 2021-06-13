package com.meli.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.meli.dto.EstadisticasDnaDto;
import com.meli.entity.Adn;
import com.meli.repository.DataBaseRepository;
import com.meli.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MutanteService implements IMutanteService {

    char[][] matriz;
    List<Character> dnaMutante;
    int secuencia = 4;

    @Autowired
    private DataBaseRepository databaseRepository;


    @Override
    public EstadisticasDnaDto estadisticasDna() {
        Long totalH= databaseRepository.findByResponse(false);
        Long totalM = databaseRepository.findByResponse(true);
        EstadisticasDnaDto estadisticasDnaDto = new EstadisticasDnaDto();
        estadisticasDnaDto.setAmountHumanDna(totalH);
        estadisticasDnaDto.setAmountMutantDna(totalM);
        estadisticasDnaDto.setRatio((float) totalM / (float) totalH);

        return estadisticasDnaDto;
    }

    /**
     * Funcion que valida si un dna es de un mutante
     *
     * @param dna
     * @return
     */
    @Override
    public boolean validarDna(List<String> dna) {
        boolean result = isMutate(dna);

        /**
         * Guarda en Cassanda la respuesta y la trama de dna
         */
        saveRequest(dna, result);
        return result;
    }

    private void saveRequest(List<String> dna, boolean result){
        Adn request = new Adn();
        request.setId(UUID.randomUUID());
        request.setDna(dna);
        request.setResponse(result);
        request.setDate(new Date());
        databaseRepository.save(request);
    }

    /**
     * Inicializacion de variables y normalizacion
     *
     * @param dna
     */
    private void inicializar(List<String> dna) {
        normalizar(dna);
        this.matriz = Util.crearMatriz(dna);
        dnaMutante = new ArrayList<>();
    }

    private boolean baseNitrogenada(char valor) {
        return "ATCG".indexOf(valor) != -1 ? true : false;
    }

    /**
     * Normalizacion de variables
     *
     * @param dna
     */
    public void normalizar(List<String> dna) {
        dna.replaceAll(String::toUpperCase);
    }


    /**
     * Metodo que valida si el dna es de un mutante;
     * la variable dnaMutante si es mayor a 1, quiere decir que el dna
     * pertenece a un mutante.
     *
     * @param dna
     * @return
     */
    protected boolean isMutate(List<String> dna) {
        inicializar(dna);
        dna.replaceAll(String::toUpperCase);

        calcularVertical(dna);
        if (dnaMutante.size() > 1) return true;

        calcularHorizontal(dna);
        if (dnaMutante.size() > 1) return true;

        calcularOblicua(dna);
        if (dnaMutante.size() > 1) return true;

        return false;
    }

    /**
     * Metodo que verifica si el dna validandolo horizontalmente tiene 4 bases nitrogenadas iguales
     *
     * @param dna
     */
    private void calcularHorizontal(List<String> dna) {
        for (int i = 0; i < dna.size(); i++) {
            int count = 0;
            for (int j = 0; j < dna.size(); j++) {
                if (baseNitrogenada(matriz[i][j])) {
                    if (matriz[i][j] == matriz[i][j == dna.size() - 1 ? j : (j + 1)]) {
                        count++;
                        if (count == 3) {
                            dnaMutante.add(matriz[i][j]);
                            break;
                        }
                    } else {
                        count = 0;
                    }
                } else {
                    continue;
                }
            }
        }
    }

    /**
     * Metodo que verifica si el dna validandolo verticalmente tiene 4 bases nitrogenadas iguales
     *
     * @param dna
     */
    private void calcularVertical(List<String> dna) {
        for (int i = 0; i < dna.size(); i++) {
            int count = 0;
            for (int j = 0; j < dna.size(); j++) {
                if (baseNitrogenada(matriz[j][i])) {
                    if (matriz[j][i] == matriz[j == dna.size() - 1 ? j : (j + 1)][i]) {
                        count++;
                        if (count == 3) {
                            dnaMutante.add(matriz[j][i]);
                            break;
                        }
                    } else {
                        count = 0;
                    }
                } else {
                    continue;
                }
            }
        }
    }

    /**
     * Metodo que verifica si el dna validandolo oblicuamente de derecha a izquierda
     * y de izquierda a derecha tiene 4 bases nitrogenadas iguales
     *
     * @param dna
     */
    private boolean calcularOblicua(List<String> dna) {
        calcularDiagonalDerechaSuperior(dna);
        if (dnaMutante.size() > 1) return true;

        calcularDiagonalIzquierdaInferior(dna);
        if (dnaMutante.size() > 1) return true;

        calcularDiagonalIzquierdaSuperior(dna);
        if (dnaMutante.size() > 1) return true;

        calcularDiagonalDerechaInferior(dna);
        if (dnaMutante.size() > 1) return true;

        return false;

    }

    private void calcularDiagonalDerechaInferior(List<String> dna) {
        int retroseso = 0;
        for (int i = 1; i <= dna.size() - secuencia; i++) {
            int count = 0;
            char valor = matriz[i][(dna.size()) - 1];
            for (int j = 0; j < dna.size() - i; j++) {
                if (valor == matriz[i + j][(dna.size() - 1) - j]) {
                    count++;
                    if (count == secuencia) {
                        dnaMutante.add(valor);
                        break;
                    }
                } else {
                    valor = matriz[i + j][(dna.size() - 1) - j];
                    count = 0;
                }
            }
        }

    }

    private void calcularDiagonalIzquierdaSuperior(List<String> dna) {
        int retroceso = -1;
        for (int j = dna.size() - 1; j > dna.size() - secuencia; j--) {
            int count = 0;
            retroceso++;
            char valor = matriz[0][j];
            for (int k = 0; k < dna.size() - retroceso; k++) {
                if (valor == matriz[k][j - k]) {
                    count++;
                    if (count == secuencia) {
                        dnaMutante.add(valor);
                        break;
                    }
                } else {
                    valor = matriz[k][j - k];
                    count = 0;
                }
            }
        }
    }

    private void calcularDiagonalIzquierdaInferior(List<String> dna) {
        for (int i = 1; i <= dna.size() - secuencia; i++) {
            int count = 0;
            char valor = matriz[i][0];
            for (int k = 0; k < dna.size() - i; k++) {
                if (valor == matriz[k + i][k]) {
                    count++;
                    if (count == secuencia) {
                        dnaMutante.add(valor);
                        break;
                    }
                } else {
                    valor = matriz[k + i][k];
                    count = 0;
                }
            }
        }
    }

    private void calcularDiagonalDerechaSuperior(List<String> dna) {
        for (int j = 0; j < dna.size() - secuencia; j++) {
            int count = 0;
            char valor = matriz[0][j];
            for (int k = 0; k < dna.size() - j; k++) {
                if (valor == matriz[k][j + k]) {
                    count++;
                    if (count == secuencia) {
                        dnaMutante.add(valor);
                        break;
                    }
                } else {
                    valor = matriz[k][j + k];
                    count = 0;
                }
            }
        }
    }
}
