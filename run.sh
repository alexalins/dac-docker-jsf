docker build -t alexa/dac-jsf ./postgres
docker run -p 5433:5432 -d --name dac-jsf alexa/dac-jsf

mvn clean package
docker build -t alexa/jsf .
docker run -p 8081:8080 -d --name jsf --link dac-jsf:host-banco alexa/jsf	