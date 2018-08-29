#!/usr/bin/env bash

substitute_environment_variables()
{
    envsubst $(printenv | cut -f1 -d'=' | sed 's/.*/\\\${&}/' | tr '\n' ',')
}

set -e

echo "$1" > /run/docker-entrypoint-command.txt
case "$1" in
    '--start-http-server')
        substitute_environment_variables < /etc/opt/app/env.js.template > /opt/app/public/env.js
        substitute_environment_variables < /etc/opt/app/index.html.template > /opt/app/public/index.html

        substitute_environment_variables < /etc/nginx/nginx.conf.template > /etc/nginx/nginx.conf
        htpasswd -b -c /etc/nginx/htpasswd "$WEBCLIENT_HTTP_BASIC_AUTH_USER" "$WEBCLIENT_HTTP_BASIC_AUTH_PASSWORD"
        nginx -g 'daemon off;'
    ;;

    *)
        echo 'undefined' > /run/docker-entrypoint-command.txt
        exec "$@"
    ;;
esac

exit 0
