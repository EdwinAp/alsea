# Alsea Backend Exam

## Tecnologias

- Docker
- Java 11
- SpringBoot 2.6.2
- MySQL Server 9.0.0-1.el9

## Tecnologias o librerias para mejorar examen

+ Map Struct
+ Junit y Mockito
+ Lombok

### Nota de esta seccion

> Para realiar estas mejoras, no me fue suficiente el tiempo para realizar estas mejoras

## Instrucciones para ejecutar la aplicacion

Voy amostrar los passos de como se puede ejecutar y visualizar logs de cada contenedor

### Archivos con los que se cuenta para la ejecucion del examen

- Dockerfile: este archivo nos sirve para crear la imagen de la aplicacion, basada en ubuntu
- docker-compose: este archivo se esta utilizando para definir un entorno, en donde se crear una instancia de base de datos mysql y una red especificando igualmente la creacion de contenedor apartir de la imagen de la aplicacion
- runningApplication.sh: este archivo coordina los anteriores archivos mencionado, ademas de generar el ejecutable jar, ejecutar posteriomrnete Dockerfile y finalmente docker-compose

#### Ejemplo de ejecutar script runningApplication.sh, nota: ABRIR TERMINAL BASH (GITBASH)

> bash ./runningApplication.sh
> bash runningApplication.sh


#### Para visualiar logs de base de datos:
> docker-compose logs -f mysqlalsea

#### Para visualiar logs de la aplicacion backend:
> docker-compose logs -f alseabackend

#### Tambien en la raiz del proyecto agrege la coleccion importable en insomnia o postman

El archivo se llama: alsea-endpoints.json





 
