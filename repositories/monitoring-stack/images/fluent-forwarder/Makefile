#!/usr/bin/env make

DOCKER_IMAGE_PREFIX=damlys/webdock-
draftVersion=unreleased
version=latest

define run
	docker run --rm ${DOCKER_IMAGE_PREFIX}fluent-forwarder:${draftVersion} $(1)
	@echo "\e[42m---\e[0m"
endef

default: shell

build:
	docker build --tag ${DOCKER_IMAGE_PREFIX}fluent-forwarder:${draftVersion} .
shell:
	docker run --interactive --tty --rm ${DOCKER_IMAGE_PREFIX}fluent-forwarder:${draftVersion} bash
test:
	$(call run,/fluent-bit/bin/fluent-bit --version)
tag:
	docker tag ${DOCKER_IMAGE_PREFIX}fluent-forwarder:${draftVersion} ${DOCKER_IMAGE_PREFIX}fluent-forwarder:${version}
push:
	docker push ${DOCKER_IMAGE_PREFIX}fluent-forwarder:${version}
release: tag push
