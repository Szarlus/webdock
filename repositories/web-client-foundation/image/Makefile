#!/usr/bin/env make

DOCKER_IMAGE_PREFIX=damlys/webdock-
draftVersion=unreleased
version=latest

define run
	docker run --rm ${DOCKER_IMAGE_PREFIX}web-client-foundation:${draftVersion} $(1)
	@echo "\e[42m---\e[0m"
endef

default: shell

build:
	docker build --tag ${DOCKER_IMAGE_PREFIX}web-client-foundation:${draftVersion} .
shell:
	docker run --interactive --tty --rm ${DOCKER_IMAGE_PREFIX}web-client-foundation:${draftVersion} bash
test:
	$(call run,curl --version)
	$(call run,envsubst --version)
	$(call run,nginx -v)
	$(call run,which htpasswd)
tag:
	docker tag ${DOCKER_IMAGE_PREFIX}web-client-foundation:${draftVersion} ${DOCKER_IMAGE_PREFIX}web-client-foundation:${version}
push:
	docker push ${DOCKER_IMAGE_PREFIX}web-client-foundation:${version}
release: tag push
