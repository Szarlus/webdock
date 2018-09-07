[<-- Back to main section](../README.md)

# PHP Debugging

[Xdebug](https://xdebug.org/) is not installed in the Docker image by default.
To do it run `make install-xdebug`.
This is required after each container recreation.

## PHPStorm configuration

![PHPStorm Xdebug port config](./assets/phpstorm-xdebug-port-config.png)

![PHPStorm Xdebug run config](./assets/phpstorm-xdebug-server-config.png)

## Chrome plugin

https://chrome.google.com/webstore/detail/xdebug-helper/eadndfjplgieldjbigjakmdgkmoaaaoc

![Chrome Xdebug helper](./assets/chrome-xdebug-helper.png)

## Callgrind application

https://kcachegrind.github.io/html/Home.html

##### Linux/Ubuntu

```
$ sudo apt install kcachegrind
```
 
##### MacOS

```
$ brew install qcachegrind
```

##### Windows

https://sourceforge.net/projects/qcachegrindwin/
