FROM openjdk:8-jre-alpine

ADD "target/BSF_UV-jar-with-dependencies.jar" "app/BSF_UV-jar-with-dependencies.jar"

ENTRYPOINT ["java", "-jar", "app/BSF_UV-jar-with-dependencies.jar"]