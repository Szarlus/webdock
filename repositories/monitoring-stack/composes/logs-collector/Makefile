#!/usr/bin/env make

default: logs

setup:
	docker-compose up -d --remove-orphans
start:
	docker-compose start
stop:
	docker-compose stop
logs:
	docker-compose logs --timestamps --tail 25 --follow
