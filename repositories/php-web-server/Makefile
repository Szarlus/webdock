#!/usr/bin/env make

default: bash

setup: create-volumes
	docker-compose up -d --remove-orphans
start:
	docker-compose start
stop:
	docker-compose stop
logs:
	docker-compose logs --timestamps --tail 25 --follow

application-builder-bash:
	docker-compose exec application_builder bash
bash:
	docker-compose exec cgi_server bash
build-application:
	docker-compose exec application_builder composer install
build-image:
	docker build --no-cache --tag damlys/webdock-php-web-server:unreleased ./image/
create-volumes:
	docker volume create --name=global_composer_cache
download-packages: create-volumes
	docker-compose run --rm --no-deps application_builder composer install --no-autoloader --no-scripts
fix-file-system-permissions:
	docker-compose exec application_builder chown --recursive $(shell id -u):$(shell id -g) .
install-xdebug:
	docker-compose exec cgi_server operation --install-xdebug
	docker-compose restart cgi_server
	docker-compose restart http_server
migrate:
	docker-compose exec cgi_server operation --migrate
