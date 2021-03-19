FROM adoptopenjdk
COPY target/exchangerates-0.0.1-SNAPSHOT.jar /opt/app/
WORKDIR /opt/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/app/exchangerates-0.0.1-SNAPSHOT.jar", "com.atilla.exchangerates.ExchangeratesApplication"]
