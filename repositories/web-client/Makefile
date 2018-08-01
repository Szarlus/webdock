#!/usr/bin/env make

default: assets-builder-bash

setup: create-volumes
	docker-compose up -d --remove-orphans
start:
	docker-compose start
stop:
	docker-compose stop
logs:
	docker-compose logs --timestamps --tail 25 --follow

assets-builder-bash:
	docker-compose exec assets_builder bash
bash:
	docker-compose exec http_server bash
build-application:
	docker-compose exec assets_builder npm install --unsafe-perm
build-image:
	docker build --no-cache --tag damlys/webdock-web-client:unreleased ./image/
create-volumes:
	docker volume create --name=global_npm_cache
download-packages: create-volumes
	docker-compose run --rm --no-deps assets_builder npm install --ignore-scripts
fix-file-system-permissions:
	docker-compose exec assets_builder chown --recursive $(shell id -u):$(shell id -g) .
