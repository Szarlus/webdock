#!/usr/bin/env make

DOCKER_IMAGE_PREFIX=damlys/webdock-
draftVersion=unreleased
version=latest

define run
	docker run --rm --entrypoint="" ${DOCKER_IMAGE_PREFIX}jenkins-master:${draftVersion} $(1)
	@echo "\e[42m---\e[0m"
endef

default: shell

build:
	docker build --tag ${DOCKER_IMAGE_PREFIX}jenkins-master:${draftVersion} .
shell:
	docker run --interactive --tty --rm ${DOCKER_IMAGE_PREFIX}jenkins-master:${draftVersion} bash
test:
	$(call run,curl --version)
	$(call run,docker --version)
	$(call run,git --version)
tag:
	docker tag ${DOCKER_IMAGE_PREFIX}jenkins-master:${draftVersion} ${DOCKER_IMAGE_PREFIX}jenkins-master:${version}
push:
	docker push ${DOCKER_IMAGE_PREFIX}jenkins-master:${version}
release: tag push
