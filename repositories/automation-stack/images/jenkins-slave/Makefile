#!/usr/bin/env make

DOCKER_IMAGE_PREFIX=damlys/webdock-
draftVersion=unreleased
version=latest

define run
	docker run --rm --entrypoint="" ${DOCKER_IMAGE_PREFIX}jenkins-slave:${draftVersion} $(1)
	@echo "\e[42m---\e[0m"
endef

default: shell

build:
	docker build --tag ${DOCKER_IMAGE_PREFIX}jenkins-slave:${draftVersion} .
shell:
	docker run --interactive --tty --rm ${DOCKER_IMAGE_PREFIX}jenkins-slave:${draftVersion} bash
test:
	$(call run,curl --version)
	$(call run,docker --version)
	$(call run,docker-compose --version)
	$(call run,git --version)
	$(call run,helm version --client --short)
	$(call run,kubectl version --client --short=true)
	$(call run,make --version)
	$(call run,terraform --version)
	$(call run,vault --version)
tag:
	docker tag ${DOCKER_IMAGE_PREFIX}jenkins-slave:${draftVersion} ${DOCKER_IMAGE_PREFIX}jenkins-slave:${version}
push:
	docker push ${DOCKER_IMAGE_PREFIX}jenkins-slave:${version}
release: tag push
