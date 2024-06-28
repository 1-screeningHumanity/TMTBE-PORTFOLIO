FROM openjdk:17
ENV TZ=Asia/Seoul
COPY build/libs/TMT-BE-PortFolio-0.0.1.jar PortfolioServer.jar
ENTRYPOINT ["java", "-jar", "PortfolioServer.jar"]