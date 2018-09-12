#!/usr/bin/env make

DOCKER_IMAGE_PREFIX=damlys/webdock-

define run
	docker run --rm --entrypoint="" ${DOCKER_IMAGE_PREFIX}$(1):unreleased $(2)
	@echo "\e[42m---\e[0m"
endef

default: jenkins-master-bash

build:
	docker build --tag ${DOCKER_IMAGE_PREFIX}automation-jenkins-master:unreleased ./jenkins-master/
	docker build --tag ${DOCKER_IMAGE_PREFIX}automation-jenkins-slave:unreleased ./jenkins-slave/
jenkins-master-bash:
	docker run --interactive --tty --rm ${DOCKER_IMAGE_PREFIX}automation-jenkins-master:unreleased bash
jenkins-slave-bash:
	docker run --interactive --tty --rm ${DOCKER_IMAGE_PREFIX}automation-jenkins-slave:unreleased bash
test:
	$(call run,automation-jenkins-master,curl --version)
	$(call run,automation-jenkins-master,docker --version)
	$(call run,automation-jenkins-master,git --version)
	$(call run,automation-jenkins-slave,consul --version)
	$(call run,automation-jenkins-slave,curl --version)
	$(call run,automation-jenkins-slave,docker --version)
	$(call run,automation-jenkins-slave,docker-compose --version)
	$(call run,automation-jenkins-slave,git --version)
	$(call run,automation-jenkins-slave,helm version --client --short)
	$(call run,automation-jenkins-slave,kubectl version --client --short=true)
	$(call run,automation-jenkins-slave,make --version)
	$(call run,automation-jenkins-slave,terraform --version)
	$(call run,automation-jenkins-slave,vault --version)
tag:
	docker tag ${DOCKER_IMAGE_PREFIX}automation-jenkins-master:unreleased ${DOCKER_IMAGE_PREFIX}automation-jenkins-master:${version}
	docker tag ${DOCKER_IMAGE_PREFIX}automation-jenkins-slave:unreleased ${DOCKER_IMAGE_PREFIX}automation-jenkins-slave:${version}
push:
	docker push ${DOCKER_IMAGE_PREFIX}automation-jenkins-master:${version}
	docker push ${DOCKER_IMAGE_PREFIX}automation-jenkins-slave:${version}
release: tag push
