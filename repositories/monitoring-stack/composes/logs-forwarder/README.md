# Compose

...

## Deployment

...

### Setup

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

```
$ cp .env.example .env
$ make setup
```
