#!/usr/bin/env make

default: logs

prepare-env:
	sudo mkdir --parents /var/jenkins/master/workspace/
	sudo mkdir --parents /var/jenkins/slave/workspace/
	sudo chown --recursive 1000:1000 /var/jenkins/master/
	sudo chown --recursive 10000:10000 /var/jenkins/slave/
	@echo "Your Docker GID is: \e[42m$(shell getent group docker | cut -d':' -f3)\e[0m"
setup:
	docker-compose up -d --build --remove-orphans
start:
	docker-compose start
stop:
	docker-compose stop
logs:
	docker-compose logs --timestamps --tail 25 --follow

jenkins-master-bash:
	docker-compose exec --user root jenkins_master bash
jenkins-slave-bash:
	docker-compose exec --user root jenkins_slave bash
test:
	docker-compose exec jenkins_master bash -c "docker run --rm hello-world | grep 'Hello'"
	docker-compose exec jenkins_slave bash -c "docker run --rm hello-world | grep 'Hello'"
