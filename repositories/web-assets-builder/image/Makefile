#!/usr/bin/env make

docker_image_name=damlys/webdock-web-assets-builder

define run
	docker run --rm ${docker_image_name}:unreleased $(1)
	@echo "\e[42m---\e[0m"
endef

default: bash

build:
	docker build --tag ${docker_image_name}:unreleased .
bash:
	docker run --interactive --tty --rm ${docker_image_name}:unreleased bash
test:
	$(call run,git --version)
	$(call run,node --version)
	$(call run,npm --version)
	$(call run,sass --version)
	$(call run,tsc --version)
	$(call run,webpack --version)
	$(call run,webpack-cli --version)
tag:
	docker tag ${docker_image_name}:unreleased ${docker_image_name}:${version}
push:
	docker push ${docker_image_name}:${version}
release: tag push
