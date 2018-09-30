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
	docker build --tag damlys/webdock-jenkins-master:unreleased ./automation-stack/images/jenkins-master/
	docker tag damlys/webdock-jenkins-master:unreleased damlys/webdock-jenkins-master:0.0.1-example
	@echo "\e[42m---\e[0m"
	docker build --tag damlys/webdock-jenkins-slave:unreleased ./automation-stack/images/jenkins-slave/
	docker tag damlys/webdock-jenkins-slave:unreleased damlys/webdock-jenkins-slave:0.0.1-example
	@echo "\e[42m---\e[0m"
	docker build --tag damlys/webdock-fluent-collector:unreleased ./monitoring-stack/images/fluent-collector/
	docker tag damlys/webdock-fluent-collector:unreleased damlys/webdock-fluent-collector:0.0.1-example
	@echo "\e[42m---\e[0m"
	docker build --tag damlys/webdock-fluent-forwarder:unreleased ./monitoring-stack/images/fluent-forwarder/
	docker tag damlys/webdock-fluent-forwarder:unreleased damlys/webdock-fluent-forwarder:0.0.1-example

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
	docker push damlys/webdock-jenkins-master:0.0.1-example
	@echo "\e[42m---\e[0m"
	docker push damlys/webdock-jenkins-slave:0.0.1-example
	@echo "\e[42m---\e[0m"
	docker push damlys/webdock-fluent-collector:0.0.1-example
	@echo "\e[42m---\e[0m"
	docker push damlys/webdock-fluent-forwarder:0.0.1-example
