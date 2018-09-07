#!/usr/bin/env make

DOCKER_IMAGE_PREFIX=damlys/webdock-

define run
	docker run --rm ${DOCKER_IMAGE_PREFIX}php-web-server-foundation:unreleased $(1)
	@echo "\e[42m---\e[0m"
endef

default: bash

build:
	docker build --tag ${DOCKER_IMAGE_PREFIX}php-web-server-foundation:unreleased .
bash:
	docker run --interactive --tty --rm ${DOCKER_IMAGE_PREFIX}php-web-server-foundation:unreleased bash
test:
	$(call run,bash -c "dpkg --status cron | grep 'Version'")
	$(call run,composer --version)
	$(call run,curl --version)
	$(call run,envsubst --version)
	$(call run,git --version)
	$(call run,nginx -v)
	$(call run,pecl version)
	$(call run,php --version)
	$(call run,php-fpm --version)
	$(call run,which cron)
	$(call run,which crontab)
	$(call run,which htpasswd)
tag:
	docker tag ${DOCKER_IMAGE_PREFIX}php-web-server-foundation:unreleased ${DOCKER_IMAGE_PREFIX}php-web-server-foundation:${version}
push:
	docker push ${DOCKER_IMAGE_PREFIX}php-web-server-foundation:${version}
release: tag push
