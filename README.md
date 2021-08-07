# CricketScoreBoard
Live Cricket Match Scoreboard


# MySql Docker Image
~~~
docker run -d --name mysql -e MYSQL_ROOT_PASSWORD=dummypassword -e MYSQL_DATABASE=hello -e MYSQL_USER=hello -e MYSQL_PASSWORD=dummytodos -p 3308:3306 mysql:5.7
# CricketScoreBoard
Live Cricket Match Scoreboard


# MySql Docker Image
~~~
docker run -d --rm --name mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=scoreboard -e MYSQL_USER=rahul -e MYSQL_PASSWORD=abc123 -p 3308:3306 mysql:5.7
~~~
# Admin user credential
~~~
userid : admin
password : rahul1234
~~~

# To create a PKCS12 keystore
~~~
keytool -genkeypair -alias scoreboard -keyalg RSA -keysize 4096 -storetype PKCS12 -keystore scoreboard.p12 -validity 3650 -storepass xyz123

view certificate

keytool -list -v -keystore scoreboard.p12

~~~
