#!/usr/bin/env bash

set -e

case $(cat /run/docker-entrypoint-command.txt) in
    '--start-http-server')
        </dev/tcp/$WEBSERVER_CGI_SERVER_HOST/$WEBSERVER_CGI_SERVER_PORT || exit 1
    ;;

    'undefined')
        exit 1
    ;;
esac

exit 0
