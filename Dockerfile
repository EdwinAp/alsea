    # Base image
FROM ubuntu:latest

# Instalar Java 11 y otras dependencias necesarias
RUN apt-get update && \
    apt-get install -y openjdk-11-jdk && \
    apt-get clean

# Establecer variables de entorno para Java
ENV JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64
ENV PATH="$JAVA_HOME/bin:${PATH}"

# Copiar la aplicación Java a la imagen
COPY target/alsea-0.0.1-SNAPSHOT.jar /app/app.jar

# Establecer el directorio de trabajo
WORKDIR /app

# Comando para ejecutar la aplicación Java
CMD ["java", "-jar", "app.jar"]