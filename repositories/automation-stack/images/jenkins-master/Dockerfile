# https://github.com/jenkinsci/docker/blob/master/Dockerfile
FROM jenkins/jenkins:latest
# has user jenkins:1000
# has group jenkins:1000
# exposes port 8080 and 50000

USER root

# docker (https://download.docker.com/linux/static/stable/x86_64/)
RUN DOCKER_VERSION="18.06.1-ce" \
&& mkdir -p /var/tmp/docker-setup/ \
&& curl -fsSL https://download.docker.com/linux/static/stable/x86_64/docker-${DOCKER_VERSION}.tgz -o /var/tmp/docker-setup/archive.tgz \
&& tar -zxf /var/tmp/docker-setup/archive.tgz -C /var/tmp/docker-setup/ \
&& mv /var/tmp/docker-setup/docker/docker /usr/local/bin/docker \
&& rm -rf /var/tmp/docker-setup/ \
&& chmod a+rx /usr/local/bin/docker

USER jenkins

RUN /usr/local/bin/install-plugins.sh \
    docker-plugin \
    generic-webhook-trigger \
    git \
    git-parameter \
    matrix-auth \
    pipeline-stage-view \
    workflow-aggregator
