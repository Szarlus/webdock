#!/usr/bin/env make

default:
	exit 0

build:
	docker build --tag damlys/webdock-web-assets-builder:unreleased ./web-assets-builder/image/
	docker tag damlys/webdock-web-assets-builder:unreleased damlys/webdock-web-assets-builder:0.0.1-example
	@echo "\e[42m---\e[0m"
	docker build --tag damlys/webdock-web-client-foundation:unreleased ./web-client-foundation/image/
	docker tag damlys/webdock-web-client-foundation:unreleased damlys/webdock-web-client-foundation:0.0.1-example
	@echo "\e[42m---\e[0m"
	docker build --tag damlys/webdock-php-web-server-foundation:unreleased ./php-web-server-foundation/image/
	docker tag damlys/webdock-php-web-server-foundation:unreleased damlys/webdock-php-web-server-foundation:0.0.1-example
	@echo "\e[42m---\e[0m"
	docker build --tag damlys/webdock-web-client:unreleased ./web-client/image/
	docker tag damlys/webdock-web-client:unreleased damlys/webdock-web-client:0.0.1-example
	@echo "\e[42m---\e[0m"
	docker build --tag damlys/webdock-php-web-server:unreleased ./php-web-server/image/
	docker tag damlys/webdock-php-web-server:unreleased damlys/webdock-php-web-server:0.0.1-example
	@echo "\e[42m---\e[0m"
	docker build --tag damlys/webdock-automation-jenkins-master:unreleased ./automation-server/images/jenkins-master/
	docker tag damlys/webdock-automation-jenkins-master:unreleased damlys/webdock-automation-jenkins-master:0.0.1-example
	@echo "\e[42m---\e[0m"
	docker build --tag damlys/webdock-automation-jenkins-slave:unreleased ./automation-server/images/jenkins-slave/
	docker tag damlys/webdock-automation-jenkins-slave:unreleased damlys/webdock-automation-jenkins-slave:0.0.1-example

push:
	docker push damlys/webdock-web-assets-builder:0.0.1-example
	@echo "\e[42m---\e[0m"
	docker push damlys/webdock-web-client-foundation:0.0.1-example
	@echo "\e[42m---\e[0m"
	docker push damlys/webdock-php-web-server-foundation:0.0.1-example
	@echo "\e[42m---\e[0m"
	docker push damlys/webdock-web-client:0.0.1-example
	@echo "\e[42m---\e[0m"
	docker push damlys/webdock-php-web-server:0.0.1-example
	@echo "\e[42m---\e[0m"
	docker push damlys/webdock-automation-jenkins-master:0.0.1-example
	@echo "\e[42m---\e[0m"
	docker push damlys/webdock-automation-jenkins-slave:0.0.1-example
