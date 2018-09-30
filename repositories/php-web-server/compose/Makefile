#!/usr/bin/env make

default: application-builder-shell

setup:
	docker-compose up -d --remove-orphans
start:
	docker-compose start
stop:
	docker-compose stop
logs:
	docker-compose logs --timestamps --tail 25 --follow

shell:
	docker-compose exec cgi_server bash
application-builder-shell:
	docker-compose exec application_builder bash
fix-mounted-files-permissions:
	docker-compose exec application_builder chown --recursive $(shell id -u):$(shell id -g) .
install-xdebug:
	docker-compose exec cgi_server operation --install-xdebug
	docker-compose restart cgi_server
	docker-compose restart http_server
