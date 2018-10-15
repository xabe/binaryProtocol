[![Build Status](https://travis-ci.org/xabe/binaryProtocol.svg?branch=master)](https://travis-ci.org/xabe/binaryProtocol)
[![Coverage Status](https://coveralls.io/repos/xabe/binaryProtocol/badge.svg?branch=master&service=github)](https://coveralls.io/github/xabe/binaryProtocol?branch=master)

# Protocolos binarios

Este es un ejemplo que se muestra como usar con Jersey con protocolos binarios:

- [x] Jackson
- [x] Google Protobuf
- [x] Kryo

## ¿Porque protocolos binarios?

Actualmente todo el mundo esta migrando de monolítico a microservicios

![](images/monolithic_vs_microservices.jpg)

Cuando llevas tiempo trabajando con los microservicos ves que no todo son ventajas, como todo tiene cosas "no tan buenas " en este caso es la comunicación entre los microservicos por defecto es json, pero en ocasion necesitamos un plus, para conseguir eso necesitamos buscar un protocolo que sea:

* Rapido en serializar y desarializar
* Tamaño reducido

Con esto lo conseguimos con kryo y protobuf
  

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