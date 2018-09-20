#!/usr/bin/env make

DOCKER_IMAGE_PREFIX=damlys/webdock-

define run
	docker run --rm --entrypoint="" ${DOCKER_IMAGE_PREFIX}$(1):unreleased $(2)
	@echo "\e[42m---\e[0m"
endef

default: jenkins-master-bash

build:
	docker build --tag ${DOCKER_IMAGE_PREFIX}jenkins-master:unreleased ./jenkins-master/
	docker build --tag ${DOCKER_IMAGE_PREFIX}jenkins-slave:unreleased ./jenkins-slave/
jenkins-master-bash:
	docker run --interactive --tty --rm ${DOCKER_IMAGE_PREFIX}jenkins-master:unreleased bash
jenkins-slave-bash:
	docker run --interactive --tty --rm ${DOCKER_IMAGE_PREFIX}jenkins-slave:unreleased bash
test:
	$(call run,jenkins-master,curl --version)
	$(call run,jenkins-master,docker --version)
	$(call run,jenkins-master,git --version)
	$(call run,jenkins-slave,consul --version)
	$(call run,jenkins-slave,curl --version)
	$(call run,jenkins-slave,docker --version)
	$(call run,jenkins-slave,docker-compose --version)
	$(call run,jenkins-slave,git --version)
	$(call run,jenkins-slave,helm version --client --short)
	$(call run,jenkins-slave,kubectl version --client --short=true)
	$(call run,jenkins-slave,make --version)
	$(call run,jenkins-slave,terraform --version)
	$(call run,jenkins-slave,vault --version)
tag:
	docker tag ${DOCKER_IMAGE_PREFIX}jenkins-master:unreleased ${DOCKER_IMAGE_PREFIX}jenkins-master:${version}
	docker tag ${DOCKER_IMAGE_PREFIX}jenkins-slave:unreleased ${DOCKER_IMAGE_PREFIX}jenkins-slave:${version}
push:
	docker push ${DOCKER_IMAGE_PREFIX}jenkins-master:${version}
	docker push ${DOCKER_IMAGE_PREFIX}jenkins-slave:${version}
release: tag push
