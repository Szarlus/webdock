ARG FROM
FROM ${FROM}

USER root

ARG DOCKER_GID
RUN groupadd --gid ${DOCKER_GID} docker
RUN usermod --append --groups docker jenkins

USER jenkins
