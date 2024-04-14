FROM openjdk:17
WORKDIR /app
COPY target/gestionscolaire.war /app/servlet.war 
CMD [ "java", "-jar", "servlet.war" ]