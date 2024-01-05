#!/usr/bin/env zsh

NS=cnj
APP_NAME=my_rh_api
IMAGE_NAME=ma.yc.api/my_rh_api:latest
#
mvn -DsikpTests=true clean package spring-boot:build-image \
 -Dspring-boot.build-image.imageName=${IMAGE_NAME}



# docker run -p 8080:8080 ma.yc.api/myrh-api/recrutement:0.0.1-SNAPSHOT
#docker push ${IMAGE_NAME}

