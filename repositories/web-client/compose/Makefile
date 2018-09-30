#!/usr/bin/env make

default: assets-builder-shell

setup:
	docker-compose up -d --remove-orphans
start:
	docker-compose start
stop:
	docker-compose stop
logs:
	docker-compose logs --timestamps --tail 25 --follow

shell:
	docker-compose exec http_server bash
assets-builder-shell:
	docker-compose exec assets_builder bash
fix-mounted-files-permissions:
	docker-compose exec assets_builder chown --recursive $(shell id -u):$(shell id -g) .
