# Compose

...

## Deployment

...

### Requirements

```
$ cat /etc/docker/daemon.json
{
    "log-driver": "fluentd",
    "log-opts": {
        "fluentd-address": "127.0.0.1:24224",
        "tag": "docker",
        "labels": "version"
    }
}
```

```
$ sudo service docker restart
```

### Prerequisites

```
$ cp .env.example .env
```

### Setup

```
$ make setup
```
