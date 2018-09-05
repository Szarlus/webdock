#!/usr/bin/env make

docker_image_name=damlys/webdock-php-web-server

define run_application_builder
	docker run \
		--rm \
		--volume global_composer_cache:/var/cache/composer/ \
		--volume $(shell pwd)/app/:/opt/app/ \
		--workdir /opt/app/ \
		$(2) \
	damlys/webdock-php-web-server-foundation:0.0.1-example \
	$(1)
	@echo "\e[42m---\e[0m"
endef

default: application-builder-bash

download-application-packages:
	$(call run_application_builder,composer install --no-autoloader --no-scripts)
build-appilcation:
	$(call run_application_builder,composer install)
test-application:
	$(call run_application_builder,composer test)
build: download-application-packages
	docker build --no-cache --tag ${docker_image_name}:unreleased .
tag:
	docker tag ${docker_image_name}:unreleased ${docker_image_name}:${version}
push:
	docker push ${docker_image_name}:${version}
release: tag push

application-builder-bash:
	$(call run_application_builder,bash,--interactive --tty)
bash:
	docker run --interactive --tty --rm ${docker_image_name}:unreleased bash
fix-mounted-files-permissions:
	$(call run_application_builder,chown --recursive $(shell id -u):$(shell id -g) .)
