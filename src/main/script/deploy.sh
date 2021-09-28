#! /bin/bash

#$1 aws key
#$2 file to deploy
#$3 aws server
AWS_KEY=$1
FILE=/Users/hongseok/IdeaProjects/crawler/target/crawler-1.0-SNAPSHOT.jar
AWS_SERVER=$3
scp -i $AWS_KEY $FILE $AWS_SERVER
