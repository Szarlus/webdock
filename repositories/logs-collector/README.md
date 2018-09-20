Logs Collector
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

Create network for Fluent logging:

```
$ docker network create logging_tier
```

## Development

1. Go to `image/` directory.
1. Build images.
1. Release images with version number.
1. Tag repository with version number.

## Deployment

1. Checkout repository to any released version number.
1. Go to `compose/` directory.
1. Set released version number in `DOCKER_IMAGE_PREFIX` variable (in `.env` file).
1. Setup infrastructure.

## Contributing

...

### Versioning

[Semantic Versioning](http://semver.org/)?

### Changelog

[Keep a Changelog](https://keepachangelog.com/)?
