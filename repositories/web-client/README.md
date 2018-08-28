Web Client
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

Build and release required version of
[Web Client Foundation](../web-client-foundation/README.md)
and [Web Assets Builder](../web-assets-builder/README.md) image.

## Contributing

...

### Versioning

[Semantic Versioning](http://semver.org/)?

### Changelog

[Keep a Changelog](https://keepachangelog.com/)?

## Runtime configuration

...

### Environment variables

...

### Certificate files

...

## Development

...

### Setup local environment

```
$ make download-packages
$ make build-image
$ cp .env.example .env
$ make setup
$ make build-application
```

Open `HTTP_SERVER_PORT` port in a web browser.

### Build

...

### Test

...

### Release

...

## Deployment

...
