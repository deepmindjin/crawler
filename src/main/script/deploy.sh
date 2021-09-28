#! /bin/bash

#$1 aws key
#$2 file to deploy
#$3 aws server
scp -i $1 $2 $3
