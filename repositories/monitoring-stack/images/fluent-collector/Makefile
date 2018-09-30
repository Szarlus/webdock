#!/usr/bin/env make

DOCKER_IMAGE_PREFIX=damlys/webdock-
draftVersion=unreleased
version=latest

define run
	docker run --rm ${DOCKER_IMAGE_PREFIX}fluent-collector:${draftVersion} $(1)
	@echo "\e[42m---\e[0m"
endef

default: shell

build:
	docker build --tag ${DOCKER_IMAGE_PREFIX}fluent-collector:${draftVersion} .
shell:
	docker run --interactive --tty --rm ${DOCKER_IMAGE_PREFIX}fluent-collector:${draftVersion} sh
test:
	$(call run,fluentd --version)
tag:
	docker tag ${DOCKER_IMAGE_PREFIX}fluent-collector:${draftVersion} ${DOCKER_IMAGE_PREFIX}fluent-collector:${version}
push:
	docker push ${DOCKER_IMAGE_PREFIX}fluent-collector:${version}
release: tag push
