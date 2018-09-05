#!/usr/bin/env make

default: application-builder-bash

setup:
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
fix-mounted-files-permissions:
	docker-compose exec application_builder chown --recursive $(shell id -u):$(shell id -g) .
install-xdebug:
	docker-compose exec cgi_server operation --install-xdebug
	docker-compose restart cgi_server
	docker-compose restart http_server
