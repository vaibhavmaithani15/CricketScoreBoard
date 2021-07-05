# CricketScoreBoard
Live Cricket Match Scoreboard


# MySql Docker Image
~~~
docker run -d --name mysql -e MYSQL_ROOT_PASSWORD=dummypassword -e MYSQL_DATABASE=hello -e MYSQL_USER=hello -e MYSQL_PASSWORD=dummytodos -p 3308:3306 mysql:5.7