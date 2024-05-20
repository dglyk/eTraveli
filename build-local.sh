#!/bin/sh

# Define variables
H2_DIRECTORY=~/.m2/repository/com/h2database/h2/2.2.224
H2_JAR=h2-2.2.224.jar
PROJECT_DIR=/c/Projects_new/Mine/eTraveli/eTraveli

# Change directory and start H2 database
cd "$H2_DIRECTORY"
java -jar "$H2_JAR" -webAllowOthers -tcpAllowOthers &

cd "$PROJECT_DIR"
mvn clean install -DskipTests

java -jar -Dserver.port=7013 ./target/eTraveli-0.0.1-SNAPSHOT.jar &
java -jar -Dserver.port=7012 ./target/eTraveli-0.0.1-SNAPSHOT.jar &
