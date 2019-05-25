#!/bin/bash

echo "Update blog posts location at 'src/main/java/com/shockn745/DataConfiguration.java'"
echo "Update blog posts location at 'src/main/java/com/shockn745/DataConfiguration.java'"
echo "Update blog posts location at 'src/main/java/com/shockn745/DataConfiguration.java'"
echo "Update blog posts location at 'src/main/java/com/shockn745/DataConfiguration.java'"
echo "Update blog posts location at 'src/main/java/com/shockn745/DataConfiguration.java'"
echo ""

./gradlew build -x test 
java -jar ./build/libs/webapp-0.0.1-SNAPSHOT.jar
