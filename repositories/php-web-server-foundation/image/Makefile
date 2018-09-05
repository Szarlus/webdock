#!/usr/bin/env make

docker_image_name=damlys/webdock-php-web-server-foundation

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
	docker tag ${docker_image_name}:unreleased ${docker_image_name}:${version}
push:
	docker push ${docker_image_name}:${version}
release: tag push
