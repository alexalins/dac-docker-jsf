docker build -t alexa/banco ./postgres
docker run -p 5433:5432 -d --name banco alexa/banco

mvn clean package
docker build -t alexa/jsf .
docker run -p 8081:8080 -d --name jsf --link banco:host-banco alexa/jsf	