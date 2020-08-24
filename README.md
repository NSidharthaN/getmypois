Get the POIs (restaurant,parking-station,ev-chargin-station) given a cityname.

*Steps to run the project* :


*Running Locally with dockerFile* :
docker build -f DockerFile -t nsidhartha/getmypois:mytag .
docker run -p 8085:8080 nsidhartha/getmypois:mytag


*Pulling from Docker-Hub and Running* :

docker pull nsidhartha/getmypois:v2.1.0
docker run -p 8085:8080 nsidhartha/getmypois:v2.1.0

*Verifying the Service Response with a Curl* 
curl --location --request GET 'localhost:8080/api/v1/poi-place-management/place/pois?location=London'


This image is also published and so is available here Dockerhub (https://hub.docker.com/repository/docker/nsidhartha/getmypois/tags?page=1)

