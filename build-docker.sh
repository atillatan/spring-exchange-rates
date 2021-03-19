sudo docker build -t springexchangerates:latest . 
docker rm springexchangerates1
sudo docker run --rm -d --name springexchangerates1 -h springexchangerates1 -p 8081:8081 -t springexchangerates:latest