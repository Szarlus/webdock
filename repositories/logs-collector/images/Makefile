#!/usr/bin/env make

DOCKER_IMAGE_PREFIX=damlys/webdock-

default: fluent-collector-bash

build:
	docker build --tag ${DOCKER_IMAGE_PREFIX}fluent-collector:unreleased ./fluent-collector/
	docker build --tag ${DOCKER_IMAGE_PREFIX}fluent-forwarder:unreleased ./fluent-forwarder/
fluent-collector-bash:
	docker run --interactive --tty --rm ${DOCKER_IMAGE_PREFIX}fluent-collector:unreleased sh
fluent-forwarder-bash:
	docker run --interactive --tty --rm ${DOCKER_IMAGE_PREFIX}fluent-forwarder:unreleased bash
tag:
	docker tag ${DOCKER_IMAGE_PREFIX}fluent-collector:unreleased ${DOCKER_IMAGE_PREFIX}fluent-collector:${version}
	docker tag ${DOCKER_IMAGE_PREFIX}fluent-forwarder:unreleased ${DOCKER_IMAGE_PREFIX}fluent-forwarder:${version}
push:
	docker push ${DOCKER_IMAGE_PREFIX}fluent-collector:${version}
	docker push ${DOCKER_IMAGE_PREFIX}fluent-forwarder:${version}
release: tag push
