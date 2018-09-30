#!/usr/bin/env make

DOCKER_IMAGE_PREFIX=damlys/webdock-
draftVersion=unreleased
version=latest

define run_assets_builder
	docker run \
		--rm \
		--volume global_npm_cache:/var/cache/npm/ \
		--volume $(shell pwd)/app/:/opt/app/ \
		--workdir /opt/app/ \
		$(2) \
	${DOCKER_IMAGE_PREFIX}web-assets-builder:0.0.1-example \
	$(1)
	@echo "\e[42m---\e[0m"
endef

default: assets-builder-shell

download-application-packages:
	$(call run_assets_builder,npm install --ignore-scripts)
build-appilcation:
	$(call run_assets_builder,npm install --unsafe-perm)
test-application:
	$(call run_assets_builder,npm test)
build: download-application-packages
	docker build --no-cache --tag ${DOCKER_IMAGE_PREFIX}web-client:${draftVersion} .
tag:
	docker tag ${DOCKER_IMAGE_PREFIX}web-client:${draftVersion} ${DOCKER_IMAGE_PREFIX}web-client:${version}
push:
	docker push ${DOCKER_IMAGE_PREFIX}web-client:${version}
release: tag push

shell:
	docker run --interactive --tty --rm ${DOCKER_IMAGE_PREFIX}web-client:${draftVersion} bash
assets-builder-shell:
	$(call run_assets_builder,bash,--interactive --tty)
fix-mounted-files-permissions:
	$(call run_assets_builder,chown --recursive $(shell id -u):$(shell id -g) .)
