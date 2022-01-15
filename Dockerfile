FROM openjdk:11
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
RUN javac /src/main/java/cl/mingeso/evaluacion2/Evaluacion2Application.java
CMD ["java", "Main"]
