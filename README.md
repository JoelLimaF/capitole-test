# Prueba técnica Java con SpringBoot ofrecida por Capitole

## Descripción
Este repositorio contiene la aplicación Java desarrollada como parte de un test técnico para evaluar las habilidades de programación. La aplicación está diseñada para proporcionar un servicio REST en Spring Boot que gestiona precios de productos para una cadena de tiendas. 

## Tecnologías utilizadas

### Lenguaje, Framework, arquitectura
    Java: 17  
    Spring Boot: 3.2.4  
    Arquitectura hexagonal   
  
### Base de datos:
    H2 Database  
    JPA: Para la persistencia de datos  
    Liquibase: Gestión de BBDD (creación de las entidades e inserción de datos)  
    Jakarta Validation API: Validaciones de datos  

### Tests unitarios e integración
    JUnit  
    Mockito  

### Herramientas
    Lombok: Simplificación de código  

## Observaciones

El enunciado no específica si es posible encontrarse con un caso en el que existan 2 precios con el mismo rango de fecha y misma prioridad. En ese caso se ha optado por seleccionar el precio que tenga la fecha "endDate" más longeva.   

Teniendo en cuenta que la entidad del enunciado de la prueba técnica está en inglés, se ha optado por realizar todo el código, entidades y documentación en inglés.  

### Tests unitarios e integración
Para los tests del PriceService, se ha optado por dividirlo en dos clases distintas:  

    PriceServiceIntegrationTest.java --> Contiene los tests de integración utilizando como fuente de datos la BBDD en memoria.  
    PriceServiceUnitTest.java --> Contiene los tests unitarios. Se mockea la respuesta del repositorio para tratar casuísticas que no están actualmente incluidas en la BBDD (como por ejemplo, 2 precios con el mismo rango de fechas y misma prioridad).  
  
