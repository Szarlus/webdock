#!/usr/bin/env make

DOCKER_IMAGE_PREFIX=damlys/webdock-

define run_application_builder
	docker run \
		--rm \
		--volume global_composer_cache:/var/cache/composer/ \
		--volume $(shell pwd)/app/:/opt/app/ \
		--workdir /opt/app/ \
		$(2) \
	${DOCKER_IMAGE_PREFIX}php-web-server-foundation:0.0.1-example \
	$(1)
	@echo "\e[42m---\e[0m"
endef

default: application-builder-bash

download-application-packages:
	$(call run_application_builder,composer install --no-interaction --no-autoloader --no-scripts)
build-appilcation:
	$(call run_application_builder,composer install --no-interaction)
test-application:
	$(call run_application_builder,composer test)
build: download-application-packages
	docker build --no-cache --tag ${DOCKER_IMAGE_PREFIX}php-web-server:unreleased .
tag:
	docker tag ${DOCKER_IMAGE_PREFIX}php-web-server:unreleased ${DOCKER_IMAGE_PREFIX}php-web-server:${version}
push:
	docker push ${DOCKER_IMAGE_PREFIX}php-web-server:${version}
release: tag push

application-builder-bash:
	$(call run_application_builder,bash,--interactive --tty)
bash:
	docker run --interactive --tty --rm ${DOCKER_IMAGE_PREFIX}php-web-server:unreleased bash
fix-mounted-files-permissions:
	$(call run_application_builder,chown --recursive $(shell id -u):$(shell id -g) .)
