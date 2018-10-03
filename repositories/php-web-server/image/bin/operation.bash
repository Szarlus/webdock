#!/usr/bin/env bash

set -e

case $1 in
    '--install-xdebug')
        pecl install xdebug
        docker-php-ext-enable xdebug
        chmod a+w /opt/app/xdebug/
    ;;

    *)
        echo 'Undefined operation.'
        exit 127
    ;;
esac

exit 0
