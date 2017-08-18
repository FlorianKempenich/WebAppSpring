FROM java

RUN mkdir /build
COPY . /build

WORKDIR /build
RUN ./gradlew clean
RUN ./gradlew build

RUN mkdir /app
WORKDIR /app

RUN cp /build/build/libs/webapp* /app/
RUN rm -rf /build

CMD ["java", "-jar", "webapp-0.0.1-SNAPSHOT.jar"]
