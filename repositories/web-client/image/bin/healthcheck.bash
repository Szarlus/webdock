#!/usr/bin/env bash

set -e

case "$(cat /run/docker-entrypoint-command.txt)" in
    '--start-http-server')
        if [ $(curl --output /dev/null --write-out '%{http_code}' --silent --fail "$APP_API_GATEWAY_DSN") != '200' ]; then exit 1; fi
    ;;

    'undefined')
        exit 1
    ;;
esac

exit 0
