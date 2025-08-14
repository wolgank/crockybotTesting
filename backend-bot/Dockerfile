# Etapa de compilación
FROM eclipse-temurin:11-jdk-alpine AS builder

WORKDIR /app

# Copiar solo lo necesario
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

# Dar permisos y compilar sin pruebas
RUN chmod +x mvnw && ./mvnw clean package -DskipTests

# Etapa de ejecución (optimizada para menor consumo de RAM)
FROM eclipse-temurin:11-jre-alpine
WORKDIR /app

# Copiar solo el JAR compilado
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

# Configuración optimizada para menor uso de RAM
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=50.0", "-XX:+TieredCompilation", "-XX:TieredStopAtLevel=1", "-jar", "app.jar"]
