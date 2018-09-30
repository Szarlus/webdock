PHP Web Server
===

...

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

Build and release required version of
[PHP Web Server Foundation](../php-web-server-foundation/README.md) image.

Create volume for Composer cache:

```
$ docker volume create --name=global_composer_cache
```

## Development

1. Go to `image/` directory.
    1. Build application.
    1. Test application.
    1. Build image.
1. Go to `compose/` directory.
    1. Setup infrastructure.
    1. Run end-to-end tests (there is no such tests in WebDock starter).
1. Go back to `image/` directory.
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
