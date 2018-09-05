#!/usr/bin/env make

default: assets-builder-bash

setup:
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
fix-mounted-files-permissions:
	docker-compose exec assets_builder chown --recursive $(shell id -u):$(shell id -g) .
