# Challenge Backend Mercado Libre


<p align="center">
  <a>
    <img 
      src="https://static.wikia.nocookie.net/marvelall/images/d/d9/Magneto.jpg/revision/latest/scale-to-width-down/350?cb=20130828171011&path-prefix=es"
      width="400"
    />
  </a>
</p>



## CODIGO FUENTE
1. Este proyecto lo puede encontrar en git [Codigo de Magneto_ADN][]


## INTRUCCIONES DE EJECUCION

### Precondiciones 
    1. Tener instalado Docker y Docker compose en la maquina. 

### Pasos de Ejecución.

    1. Ejecucion del archivo que esta en las fuentes de datos, docket-compose.yml.
        
        docker-compose up -d

    Nota: Este archivo lo que realiza es subir una imagen de la base de datos de cassandra por el 
    puerto 9041 especificado en el archivo, el cual debe estar habilitado y disponible en la maquina 
    local, igualmente se puede cambiar, por cualquier otro. tambien sube la imagen del programa 
    desarrollado Magneto_ADN subido como imagen en mi DockerHub personal, se despliega en el puerto 
    especificado en el archivo 8080 el cual puede ser cambiado por cualquier otro.

## URL DE LA API EN LOCAL

### Url nivel 2
 * http://localhost:8080/mutant
    
    * Se debe pasar como parametro un json con la base nitrogenada del ADN por ejemplo:
      {"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]}
      

### Url nivel 3
 * http://localhost:8080/stats
    * Al consumir este servicio, nos retorna la cantidad de mutantes, la cantidad de humanos y el ratio entre cantidad de humanos y mutantes.

## PUBLICADO EN  AWS EC2
### Url nivel 2
 * http://3.135.192.241:8080/mutant
### Url nivel 3
 * http://3.135.192.241:8080/stats

## REPORTE COVERAGE

   [Click para ver Test-Automáticos, Code coverage]

## TECNOLOGIAS USADAS

 * Spring Boot
 * java 8
 * Lombook
 * Cassandra
 * Jacoco 

[Click para ver Test-Automáticos, Code coverage]: https://github.com/camilorigueros/Magneto_ADN/blob/main/docs/README_REPORTES.md
[Codigo de Magneto_ADN]: https://github.com/camilorigueros/Magneto_ADN.git
