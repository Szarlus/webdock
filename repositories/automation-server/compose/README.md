# Compose

...

## Deployment

...

### Setup

```
$ make prepare-env
...
Your Docker GID is: 999
```

```
$ cp .env.example .env
```

Change `HOST_DOCKER_GID` in `.env` file.

```
$ make setup
```

### Test

```
$ make test
```
