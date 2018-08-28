#!/usr/bin/env bash

serialize_environment_variables()
{
    export -p | sed 's/declare -x/export/g'
}
substitute_environment_variables()
{
    envsubst $(printenv | cut -f1 -d'=' | sed 's/.*/\\\${&}/' | tr '\n' ',')
}

set -e

echo "$1" > /run/docker-entrypoint-command.txt
case "$1" in
    '--start-cgi-server')
        php-fpm --nodaemonize
    ;;

    '--start-http-server')
        substitute_environment_variables < /etc/nginx/nginx.conf.template > /etc/nginx/nginx.conf
        htpasswd -b -c /etc/nginx/htpasswd "$APP_HTTP_BASIC_AUTH_USER" "$APP_HTTP_BASIC_AUTH_PASSWORD"
        nginx -g 'daemon off;'
    ;;

    '--start-task-scheduler')
        serialize_environment_variables > /etc/cronenvs
        crontab /etc/crontab
        cron -f
    ;;

    '--start-example-worker')
        su --command "bin/console.php" deploy
    ;;

    *)
        echo 'undefined' > /run/docker-entrypoint-command.txt
        exec "$@"
    ;;
esac

exit 0
