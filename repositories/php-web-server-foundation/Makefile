#!/usr/bin/env make

docker_image_name=damlys/webdock-php-web-server-foundation

define run_test
	docker run --rm ${docker_image_name}:unreleased $(1)
	@echo ""
endef

default: bash

build:
	docker build --tag ${docker_image_name}:unreleased .
bash:
	docker run --interactive --tty --rm ${docker_image_name}:unreleased bash
test:
	$(call run_test,bash -c "dpkg --status cron | grep 'Version'")
	$(call run_test,composer --version)
	$(call run_test,curl --version)
	$(call run_test,envsubst --version)
	$(call run_test,git --version)
	$(call run_test,nginx -v)
	$(call run_test,pecl version)
	$(call run_test,php --version)
	$(call run_test,php-fpm --version)
	$(call run_test,which cron)
	$(call run_test,which crontab)
	$(call run_test,which htpasswd)
push:
	docker tag ${docker_image_name}:unreleased ${docker_image_name}:${version}
	docker push ${docker_image_name}:${version}
tag:
	git fetch origin --tags
	git tag ${version}
	git push origin ${version}
release: tag push
