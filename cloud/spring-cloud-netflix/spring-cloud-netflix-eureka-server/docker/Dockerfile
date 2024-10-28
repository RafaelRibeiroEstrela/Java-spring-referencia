# Utilize uma imagem base com o JDK 17
FROM openjdk:17-jdk

# Adicione um ponto de montagem para volumes opcionais
VOLUME /tmp

# Copie o arquivo jar da sua aplicação para a imagem
COPY spring-cloud-netflix-eureka-server-0.0.1-SNAPSHOT.jar app.jar

# Exponha a porta em que sua aplicação executa
EXPOSE 8761

# Execute a aplicação
ENTRYPOINT ["java","-jar","/app.jar"]
