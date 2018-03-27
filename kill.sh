docker stop jsf
docker kill jsf
docker rm jsf
docker rmi -f alexa/jsf

docker stop dac-jsf
docker kill dac-jsf
docker rm dac-jsf
docker rmi -f alexa/dac-jsf

mvn clean