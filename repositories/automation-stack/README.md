Automation Stack
===

This repository contains files required to build and run
[Jenkins](https://jenkins.io/) master and slave images with few pre-installed plugins:

- [Docker](https://plugins.jenkins.io/docker-plugin) (`docker-plugin`),
- [Generic Webhook Trigger](https://plugins.jenkins.io/generic-webhook-trigger) (`generic-webhook-trigger`),
- [Git](https://plugins.jenkins.io/git) (`git`),
- [Git Parameter](https://plugins.jenkins.io/git-parameter) (`git-parameter`),
- [Matrix Authorization Strategy](https://plugins.jenkins.io/matrix-auth) (`matrix-auth`),
- [Pipeline](https://plugins.jenkins.io/workflow-aggregator) (`workflow-aggregator`),
- [Pipeline: Stage View](https://plugins.jenkins.io/pipeline-stage-view) (`pipeline-stage-view`)

and few useful CLI scripts inside:

- `make`,
- `docker`,
- `docker-compose`,
- `kubectl`,
- `helm`,
- `consul`,
- `terraform`,
- `vault`.

## Getting started

...

### Requirements

```
$ make --version; \
  docker --version; \
  docker-compose --version; \
  docker run --rm hello-world
```

### Prerequisites

Login to Docker registry:

```
$ docker login --username damlys
```

## Development

1. Go to `images/` directory. For each image:
    1. Build image.
    1. Test image.
1. Go to `compose/` directory.
    1. Setup infrastructure.
    1. Test infrastructure.
1. Go back to `images/` directory. For each image:
    1. Release image with version number.
1. Tag repository with version number.

## Deployment

1. Checkout repository to any released version number.
1. Go to `compose/` directory.
    1. Set released version number in `DOCKER_IMAGE_VERSION` variable (in `.env` file).
    1. Setup infrastructure.

## Contributing

...

### Versioning

[Semantic Versioning](http://semver.org/)?

### Changelog

[Keep a Changelog](https://keepachangelog.com/)?
