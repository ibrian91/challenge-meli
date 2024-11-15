# Mutant Detector

Este proyecto es una implementación de un detector de ADN mutante utilizando Spring Boot. La aplicación permite verificar si una secuencia de ADN proporcionada es de un mutante o un humano, y proporciona estadísticas sobre el análisis de los datos.

## Tecnologías Utilizadas

- **Spring Boot**: Para la creación de la API RESTful.
- **JPA (Java Persistence API)**: Para la persistencia de los datos (base de datos).
- **H2 Database**: Base de datos en memoria para pruebas.
- **JUnit**: Para pruebas unitarias.

## Endpoints

Link para la prueba del endpoint https://challenge-441520.rj.r.appspot.com/

### `GET /stats`
Devuelve las estadísticas de la cantidad de ADN humano y mutante procesados, así como el ratio entre ambos.

### 'POST /mutant'

Ingresando como Json:
{
  "dna": ["ATGCGA", "CAGTGC","TTATGT","AGAAGG","CCZCTA","TCACTG"]
}

devuelve ok y lo ingresa en la base de datos

No mutante:

{
  "dna": ["ATGCGA", "CAGTGC","TTATGT","ZZZZGG","CCZCTA","TCACTG"]
}

Lo ingresa como humano

## Base de datos:

Se crea base de datos en Google App Engine con Google Cloud:

<img width="489" alt="image" src="https://github.com/user-attachments/assets/76b9a627-03e2-46ab-8663-14ae3775cfb3">

## Test:
Se realizan pruebas de TEST con JUnit y JaCoCo para generar reporte obteniendo los siguientes datos:

<img width="1163" alt="image" src="https://github.com/user-attachments/assets/30dfd5d9-be45-49fe-962b-f610822d004a">



