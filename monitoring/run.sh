#!/bin/bash
startBanner(){
echo "  _____ _             _   "
echo " / ____| |           | |  "
echo "| (___ | |_ __ _ _ __| |_ "
echo " \___ \| __/ _  | '__| __|"
echo " ____) | || (_| | |  | |_ "
echo "|_____/ \__\__,_|_|   \__|"
}
endBanner(){
echo " ______           _ "
echo "|  ____|         | |"
echo "| |__   _ __   __| |"
echo "|  __| | '_ \ / _  |"
echo "| |____| | | | (_| |"
echo "|______|_| |_|\__,_|"
}

findIpAddress(){
  if [[ "$OSTYPE" == "linux-gnu"* ]]; then
          IP_ADDRESS=$(hostname -I)
  elif [[ "$OSTYPE" == "darwin"* ]]; then
          IP_ADDRESS=$(ifconfig | grep "inet " | grep -Fv 127.0.0.1 | awk '{print $2}')
  elif [[ "$OSTYPE" == "msys"* ]]; then
          IP_ADDRESS=$(ipconfig | grep "IPv4" | grep -Fv 127.0.0.1 | awk '{print $14}')
          IFS=''
          read -ra arr <<< "$IP_ADDRESS"
          for val in "${arr[@]}";
          do
            IP_ADDRESS=$val
          done
  else
           echo "Unknown"
  fi
  echo "step 1: Find ip address of system : $IP_ADDRESS"
}

addLocalSystemIPAddressInPrometheusYml(){
  LOCATION="${PWD%/*}"
  mkdir -p "$LOCATION"/build/monitoring
  sed "s/LOCAL_MACHINE_IP_ADDRESS/$IP_ADDRESS/g" "$LOCATION"/monitoring/prometheus.yml > "$LOCATION"/build/monitoring/prometheus.yml
  echo "step 2: Created Prometheus.yml file : $LOCATION/build/monitoring/prometheus.yml"
}

runPrometheus(){
  echo "step 3: stop prometheus container if exist"
  docker stop prometheus
  echo "step 4: start building new prometheus container"
  docker run -d --rm --name=prometheus -p 9090:9090 -v "$LOCATION"/build/monitoring/prometheus.yml:/etc/prometheus/prometheus.yml prom/prometheus
  docker ps -f name=prometheus
}

runGrafana(){
  echo "step 5: stop grafana container if exist"
  docker stop grafana
  echo "step 6: start building new grafana container"
  docker run -d --rm --name=grafana -p  3000:3000 grafana/grafana:5.4.3
  docker ps -f name=grafana
}

addDataSource() {
  curl 'http://admin:admin@127.0.0.1:3000/api/datasources' \
        -X POST \
        -H 'Content-Type: application/json;charset=UTF-8' \
        --data-binary \
        '{"name":"Prometheus","type":"prometheus","url":"http://'"$PROMETHEUS_IP_ADDRESS"':9090","access":"proxy", "isDefault":true,"database":"asd"}'
}

runMysqlInDoker(){
  echo "step 9: stop mysql container if exist"
  docker stop mysql
  echo "step 10: start building new mysql container"
  docker run -d --rm --name mysql -e MYSQL_ROOT_PASSWORD=dummypassword -e MYSQL_DATABASE=scoreboard -e MYSQL_USER=rahul -e MYSQL_PASSWORD=abc123 -p 3308:3306 mysql:8.0
  docker ps -f name=mysql
}

setupGrafana(){
  echo "step 8: Add data source in grafana to connect prometheus"
  export PROMETHEUS_IP_ADDRESS=$(findRunningDockerContainerIpAddress prometheus)
  echo "Prometheus container ip address $PROMETHEUS_IP_ADDRESS"
    until addDataSource; do
      echo 'Configuring Grafana...'
      sleep 1
    done
    echo 'Done!'
    wait
}

runRabbitMq(){
  echo "step 11: stop rabbit container if exist"
    docker stop rabbitmq
    echo "step 12: start building new mysql container"
    docker run -d --rm --name rabbitmq -p 15672:15672 -p 5672:5672 rabbitmq:3-management
    docker ps -f name=rabbitmq
}

findRunningDockerContainerIpAddress() {
  docker inspect --format '{{ .NetworkSettings.IPAddress }}' "$@"
}

startBanner
findIpAddress
addLocalSystemIPAddressInPrometheusYml
runPrometheus
runGrafana
setupGrafana
runMysqlInDoker
runRabbitMq
endBanner

#docker inspect -f '{{range.NetworkSettings.Networks}}{{.IPAddress}}{{end}}' prometheus
#docker inspect -f '{{range.NetworkSettings.Networks}}{{.IPAddress}}{{end}}' grafana




