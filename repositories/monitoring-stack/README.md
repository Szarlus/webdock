Monitoring Stack
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

Create network for monitoring purposes:

```
$ docker network create monitoring_tier
```

## Development

1. Go to `images/` directory. For each image:
    1. Build image.
    1. Test image.
    1. Release image with version number.
1. Tag repository with version number.

## Deployment

1. Checkout repository to any released version number.
1. Go to `composes/` directory. For each compose:
    1. Set released version number in `DOCKER_IMAGE_VERSION` variable (in `.env` file).
    1. Setup infrastructure.

## Contributing

...

### Versioning

[Semantic Versioning](http://semver.org/)?

### Changelog

[Keep a Changelog](https://keepachangelog.com/)?
