#!/bin/bash


./gradlew build -x test 
java -jar ./build/libs/webapp-0.0.1-SNAPSHOT.jar
