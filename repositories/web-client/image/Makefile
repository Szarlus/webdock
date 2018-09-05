#!/usr/bin/env make

docker_image_name=damlys/webdock-web-client

define run_assets_builder
	docker run \
		--rm \
		--volume global_npm_cache:/var/cache/npm/ \
		--volume $(shell pwd)/app/:/opt/app/ \
		--workdir /opt/app/ \
		$(2) \
	damlys/webdock-web-assets-builder:0.0.1-example \
	$(1)
	@echo "\e[42m---\e[0m"
endef

default: assets-builder-bash

download-application-packages:
	$(call run_assets_builder,npm install --ignore-scripts)
build-appilcation:
	$(call run_assets_builder,npm install --unsafe-perm)
test-application:
	$(call run_assets_builder,npm test)
build: download-application-packages
	docker build --no-cache --tag ${docker_image_name}:unreleased .
tag:
	docker tag ${docker_image_name}:unreleased ${docker_image_name}:${version}
push:
	docker push ${docker_image_name}:${version}
release: tag push

assets-builder-bash:
	$(call run_assets_builder,bash,--interactive --tty)
bash:
	docker run --interactive --tty --rm ${docker_image_name}:unreleased bash
fix-mounted-files-permissions:
	$(call run_assets_builder,chown --recursive $(shell id -u):$(shell id -g) .)
