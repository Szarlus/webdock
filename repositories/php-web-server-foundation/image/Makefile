#!/usr/bin/env make

DOCKER_IMAGE_PREFIX=damlys/webdock-
draftVersion=unreleased
version=latest

define run
	docker run --rm ${DOCKER_IMAGE_PREFIX}php-web-server-foundation:${draftVersion} $(1)
	@echo "\e[42m---\e[0m"
endef

default: shell

build:
	docker build --tag ${DOCKER_IMAGE_PREFIX}php-web-server-foundation:${draftVersion} .
shell:
	docker run --interactive --tty --rm ${DOCKER_IMAGE_PREFIX}php-web-server-foundation:${draftVersion} bash
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
tag:
	docker tag ${DOCKER_IMAGE_PREFIX}php-web-server-foundation:${draftVersion} ${DOCKER_IMAGE_PREFIX}php-web-server-foundation:${version}
push:
	docker push ${DOCKER_IMAGE_PREFIX}php-web-server-foundation:${version}
release: tag push
