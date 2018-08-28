#!/usr/bin/env make

default:
	exit 0

build:
	docker build --tag damlys/webdock-web-assets-builder:unreleased ./web-assets-builder/
	docker tag damlys/webdock-web-assets-builder:unreleased damlys/webdock-web-assets-builder:0.0.1-example
	docker build --tag damlys/webdock-web-client-foundation:unreleased ./web-client-foundation/
	docker tag damlys/webdock-web-client-foundation:unreleased damlys/webdock-web-client-foundation:0.0.1-example
	docker build --tag damlys/webdock-php-web-server-foundation:unreleased ./php-web-server-foundation/
	docker tag damlys/webdock-php-web-server-foundation:unreleased damlys/webdock-php-web-server-foundation:0.0.1-example
	docker build --tag damlys/webdock-web-client:unreleased ./web-client/image/
	docker tag damlys/webdock-web-client:unreleased damlys/webdock-web-client:0.0.1-example
	docker build --tag damlys/webdock-php-web-server:unreleased ./php-web-server/image/
	docker tag damlys/webdock-php-web-server:unreleased damlys/webdock-php-web-server:0.0.1-example

push:
	docker push damlys/webdock-web-assets-builder:0.0.1-example
	docker push damlys/webdock-web-client-foundation:0.0.1-example
	docker push damlys/webdock-php-web-server-foundation:0.0.1-example
	docker push damlys/webdock-web-client:0.0.1-example
	docker push damlys/webdock-php-web-server:0.0.1-example
