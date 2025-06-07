# --- Сборочный этап ---
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app

# Копируем pom и качаем зависимости до копирования исходников (кэширование)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Копируем исходники и собираем jar
COPY src ./src
RUN mvn clean package -DskipTests -B

# --- Режим выполнения ---
FROM eclipse-temurin:17-jdk-jre

WORKDIR /app

# Копируем готовый jar из сборочного этапа
COPY --from=build /app/target/bathbooking-*.jar bathbooking.jar

# Открываем порт приложения
EXPOSE 8080

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "bathbooking.jar"]
