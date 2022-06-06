FROM openjdk:17
ADD build/libs/exchangeRate-2.jar exchange-rate.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "exchange-rate.jar"]