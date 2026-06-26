# ArquitecturaWebTpEntregable

Proyecto Java: entrega de arquitectura web (TP). Este repositorio contiene un sistema compuesto por múltiples módulos y microservicios orientados a una arquitectura distribuida para un caso de uso de monopatines compartidos (name: entregableMonopatin). Está pensado como trabajo práctico/entregable para una materia de arquitectura de software.

## Resumen

- Lenguaje principal: Java
- Estructura: multi-módulo Maven con microservicios (varios módulos bajo `entregableMonopatin/`)
- Objetivo: demostrar diseño arquitectónico (servicios, gateway, comunicación entre microservicios), persistencia, y funcionalidades típicas de una plataforma de movilidad.

## Tecnologías (ejemplos detectados)

- Java (JDK 17+ / 24 según configuración de IDE)
- Maven para build y gestión de módulos
- Posible uso de frameworks como Spring Boot (revisar pom.xml de cada módulo)

> Nota: revisa los `pom.xml` dentro de los submódulos para confirmar versiones y dependencias exactas.

## Estructura del repositorio (resumen)

El proyecto tiene múltiples submódulos identificados en la configuración del IDE. Algunos nombres relevantes:

- entregableMonopatin/
  - gateway/
  - msvc-monopatin/
  - msvc-gateway/
  - msvc-usuario/
  - msvc-cuenta/
  - msvc-parada/
  - msvc-administracion/
  - msvc-viaje/
  - msvc-facturacion/
  - msvc-reporte/
  - gateway-msvc/
  - msvc-usuario-cuenta-gateway/
  - msvc-mockMercadoPago/
  - msvc-chat/
  - chat-service/

Cada uno probablemente contiene su propio `pom.xml` y código fuente Java.

## Requisitos previos

- Java JDK instalado (recomendado: 17 o la versión que indique cada `pom.xml` / archivo de configuración — en el proyecto se ve referencia a JDK 24 en la configuración de IDE)
- Maven instalado
- Docker (opcional, si los microservicios o la base de datos se levantan en contenedores)

## Compilar y ejecutar (guía rápida)

1. Clonar el repositorio:

   git clone https://github.com/GGonza12/ArquitecturaWebTpEntregable.git

2. Compilar todo el proyecto desde la raíz con Maven:

   mvn clean install

   - Si prefieres compilar módulos específicos, usa `-pl <module>` o entra al directorio del módulo y ejecuta `mvn package`.

3. Ejecutar un microservicio (ejemplo):

   - Entrar al directorio del módulo: `cd entregableMonopatin/msvc-usuario`
   - Ejecutar: `mvn spring-boot:run` (si usa Spring Boot) o ejecutar la clase principal con `java -jar target/*.jar`.

4. Si el proyecto usa servicios externos (base de datos, Kafka, Redis), levántalos primero o revisa la configuración `application.properties` / `application.yml` de cada módulo.

## Tests

- Ejecutar pruebas con Maven:

  mvn test

- Revisa los `pom.xml` de cada módulo para ver dependencias de testing (JUnit, Mockito, etc.).



