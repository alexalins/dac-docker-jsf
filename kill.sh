docker stop jsf
docker kill jsf
docker rm jsf
docker rmi -f alexa/jsf

docker stop banco
docker kill banco
docker rm banco
docker rmi -f alexa/banco

mvn clean