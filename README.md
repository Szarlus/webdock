WebDock
===

WebDock is a minimalistic Docker starter for web applications.
Project is designed to work with PHP micro services and SPA web clients,
but it is also great to work with PHP MPA.

## Table of contents

1. [Getting started](#getting-started)
1. [Useful links](./Documentation/Useful-links.md)
1. [Useful Docker images](./Documentation/Useful-Docker-images.md)
1. [Repository structure concepts](./Documentation/Repository-structure-concepts.md)
1. [PHP Debugging](./Documentation/PHP-Debugging.md)

## Getting started

These instructions will help you use any repository from this project.

### Prerequisites

To start working with WebDock's repositories you have to install
[Docker Engine](https://docs.docker.com/engine/) 
(`dockerd`; which may be [virtualized](#virtualization)), 
Docker Client (`docker`) and 
[Docker Compose](https://docs.docker.com/compose/) (`docker-compose`)
software.

This is how you can check if it is properly installed:

```
$ docker --version
$ docker run --rm hello-world
$ docker-compose --version
```

#### Virtualization

If you are MacOS or Windows user then you have no possibility to run
Docker Engine daemon on your host machine. Instead of that you have to
install [Virtualbox](https://www.virtualbox.org/) and
[Docker Machine](https://docs.docker.com/machine/) that will run
virtual Linux system with Docker Engine.

```
$ docker-machine --version
```

[Docker Machine NFS](https://github.com/adlogix/docker-machine-nfs)
is highly recommended to speed up transferring files between
virtual machine and MacOS.

```
$ docker-machine-nfs
```

#### Hosts

Check Docker IP with `ifconfig docker0` command  
(`docker-machine ip` if you are working with Docker Machine).

```
$ ifconfig docker0
docker0   Link encap:Ethernet  HWaddr 02:42:be:e6:83:00  
          inet addr:172.17.0.1  Bcast:0.0.0.0  Mask:255.255.0.0
          UP BROADCAST MULTICAST  MTU:1500  Metric:1
          RX packets:0 errors:0 dropped:0 overruns:0 frame:0
          TX packets:0 errors:0 dropped:0 overruns:0 carrier:0
          collisions:0 txqueuelen:0 
          RX bytes:0 (0.0 B)  TX bytes:0 (0.0 B)
```

Add `172.17.0.1 docker.localhost www.docker.localhost` line to
the `/etc/hosts` file  
(`C:\Windows\System32\Drivers\etc\hosts` file for Windows).

### Copy files

Copy any [repository](./repositories/) from this project to your
own Git repository and follow it's readme file.

#### Docker images registry

[Docker Hub](https://hub.docker.com) is an official registry for Docker images.
You may use it or setup own private Docker registry.

Note that following names are used only to visualise the relationships
between repositories.

```
damlys/webdock-web-assets-builder
damlys/webdock-web-client
damlys/webdock-web-client-foundation
damlys/webdock-php-web-server
damlys/webdock-php-web-server-foundation
```

You should change them immediately after repository clone.
