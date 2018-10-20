[![Build Status](https://travis-ci.org/xabe/binaryProtocol.svg?branch=master)](https://travis-ci.org/xabe/binaryProtocol)
[![Sonar](https://sonarcloud.io/api/project_badges/measure?project=xabe%3Abinary-protocol&metric=alert_status)](https://sonarcloud.io/api/project_badges/measure?project=xabe%3Abinary-protocol&metric=alert_status)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=xabe%3Abinary-protocol&metric=coverage)](https://sonarcloud.io/api/project_badges/measure?project=xabe%3Abinary-protocol&metric=coverage)
[![Maintainability](https://sonarcloud.io/api/project_badges/measure?project=xabe%3Abinary-protocol&metric=sqale_rating)](https://sonarcloud.io/api/project_badges/measure?project=xabe%3Abinary-protocol&metric=sqale_rating)
[![Technical](https://sonarcloud.io/api/project_badges/measure?project=xabe%3Abinary-protocol&metric=sqale_index)](https://sonarcloud.io/api/project_badges/measure?project=xabe%3Abinary-protocol&metric=sqale_index)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=xabe%3Abinary-protocol&metric=vulnerabilities)](https://sonarcloud.io/api/project_badges/measure?project=xabe%3Abinary-protocol&metric=vulnerabilities)
[![Duplicate](https://sonarcloud.io/api/project_badges/measure?project=xabe%3Abinary-protocol&metric=duplicated_lines_density)](https://sonarcloud.io/api/project_badges/measure?project=xabe%3Abinary-protocol&metric=duplicated_lines_density)
[![Reliability](https://sonarcloud.io/api/project_badges/measure?project=xabe%3Abinary-protocol&metric=reliability_rating)](https://sonarcloud.io/api/project_badges/measure?project=xabe%3Abinary-protocol&metric=reliability_rating)
[![Security](https://sonarcloud.io/api/project_badges/measure?project=xabe%3Abinary-protocol&metric=security_rating)](https://sonarcloud.io/api/project_badges/measure?project=xabe%3Abinary-protocol&metric=security_rating)
# Protocolos binarios

Actualmente todo el mundo esta migrando de monolítico a microservicios

![](images/monolithic_vs_microservices.jpg)

Cuando llevas tiempo trabajando con los microservicos ves que no todo son ventajas, como todo tiene cosas "buenas" y "no tan buenas" en este caso es la comunicación entre los microservicos por defecto es json, pero en ocasiones necesitamos que la comunicación entre los microservicios sea muy rapido, para conseguir eso necesitamos buscar un protocolo que sea:

* Rapido en serializar y desarializar
* Tamaño reducido

Con esto lo conseguimos con protocolos binarios, si buscamos en google encontramos un moton de frameworks:

* Protocol Buffer
* Kryo
* Avro
* Thrift

En este ejemplo vamos a comparar json contra protocol buffer y kryo, como he dicho antes para probar los protocolos binarios este ejemplo esta formado por las siguientes tecnologias:

- [x] Grizzly como servidor NIO
- [x] Jersey como JAX-RS
- [x] Jackson
- [x] Google Protobuf
- [x] Kryo

## Requisitos

* Maven 3 o superior
* Java 8 o superior

## Inicio rápido

```
git clone git@github.com:xabe/binaryProtocol.git
```

## Ejecución

```
mvn clean install -Pbenchmark
```