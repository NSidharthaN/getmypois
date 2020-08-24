Get the POIs (restaurant,parking-station,ev-chargin-station) given a cityname.

*Steps to run the project* :

# replace with any tag,if need be

docker build -f DockerFile -t nsidhartha/getmypois .

docker run -p 8085:8080 nsidhartha/getmypois

This image is also published and hence is available here Dockerhub ( https://hub.docker.com/r/nsidhartha/getmypois/tags)

docker pull nsidhartha/getmypois

