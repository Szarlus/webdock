#!/usr/bin/env make

docker_image_name=damlys/webdock-web-assets-builder

define run_test
	docker run --rm ${docker_image_name}:unreleased $(1)
	@echo ""
endef

default: bash

build:
	docker build --tag ${docker_image_name}:unreleased .
bash:
	docker run --interactive --tty --rm ${docker_image_name}:unreleased bash
test:
	$(call run_test,git --version)
	$(call run_test,node --version)
	$(call run_test,npm --version)
	$(call run_test,sass --version)
	$(call run_test,tsc --version)
	$(call run_test,webpack --version)
	$(call run_test,webpack-cli --version)
push:
	docker tag ${docker_image_name}:unreleased ${docker_image_name}:${version}
	docker push ${docker_image_name}:${version}
tag:
	git fetch origin --tags
	git tag ${version}
	git push origin ${version}
release: tag push
