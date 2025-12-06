# Usamos imagen base con OpenJDK 17
FROM eclipse-temurin:17-jdk-alpine

# Directorio de la app dentro del contenedor
WORKDIR /app

# Copiamos los archivos de build
COPY build/libs/*.jar app.jar

# Exponemos el puerto
EXPOSE 8080

# Comando para ejecutar la app
ENTRYPOINT ["java","-jar","/app/app.jar"]
