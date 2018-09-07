#!/usr/bin/env make

DOCKER_IMAGE_PREFIX=damlys/webdock-

define run
	docker run --rm ${DOCKER_IMAGE_PREFIX}web-client-foundation:unreleased $(1)
	@echo "\e[42m---\e[0m"
endef

default: bash

build:
	docker build --tag ${DOCKER_IMAGE_PREFIX}web-client-foundation:unreleased .
bash:
	docker run --interactive --tty --rm ${DOCKER_IMAGE_PREFIX}web-client-foundation:unreleased bash
test:
	$(call run,curl --version)
	$(call run,envsubst --version)
	$(call run,nginx -v)
	$(call run,which htpasswd)
tag:
	docker tag ${DOCKER_IMAGE_PREFIX}web-client-foundation:unreleased ${DOCKER_IMAGE_PREFIX}web-client-foundation:${version}
push:
	docker push ${DOCKER_IMAGE_PREFIX}web-client-foundation:${version}
release: tag push
