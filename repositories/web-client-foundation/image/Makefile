#!/usr/bin/env make

docker_image_name=damlys/webdock-web-client-foundation

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
	$(call run,curl --version)
	$(call run,envsubst --version)
	$(call run,nginx -v)
	$(call run,which htpasswd)
tag:
	docker tag ${docker_image_name}:unreleased ${docker_image_name}:${version}
push:
	docker push ${docker_image_name}:${version}
release: tag push
