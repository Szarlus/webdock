#!/usr/bin/env make

DOCKER_IMAGE_PREFIX=damlys/webdock-

define run
	docker run --rm ${DOCKER_IMAGE_PREFIX}web-assets-builder:unreleased $(1)
	@echo "\e[42m---\e[0m"
endef

default: bash

build:
	docker build --tag ${DOCKER_IMAGE_PREFIX}web-assets-builder:unreleased .
bash:
	docker run --interactive --tty --rm ${DOCKER_IMAGE_PREFIX}web-assets-builder:unreleased bash
test:
	$(call run,git --version)
	$(call run,node --version)
	$(call run,npm --version)
	$(call run,sass --version)
	$(call run,tsc --version)
	$(call run,webpack --version)
	$(call run,webpack-cli --version)
tag:
	docker tag ${DOCKER_IMAGE_PREFIX}web-assets-builder:unreleased ${DOCKER_IMAGE_PREFIX}web-assets-builder:${version}
push:
	docker push ${DOCKER_IMAGE_PREFIX}web-assets-builder:${version}
release: tag push
