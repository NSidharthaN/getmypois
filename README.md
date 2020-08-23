Get the POIs (restaurant,parking-station,ev-chargin-station) given a cityname.

*Steps to run the project* :

docker build -f DockerFile -t nsidhartha/getmypois . # replace with any tag,if need be
docker run -p 8085:8080 nsidhartha/getmypois

This image is also published and hence is available here Dockerhub(https://hub.docker.com/r/nsidhartha/getmypois)
docker pull nsidhartha/getmypois

