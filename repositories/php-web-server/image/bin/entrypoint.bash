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
    '--start-migrator')
        su --command "echo 'migrating...'" deploy
    ;;

    '--start-cgi-server')
        php-fpm --nodaemonize
    ;;

    '--start-http-server')
        substitute_environment_variables < /etc/nginx/nginx.conf.template > /etc/nginx/nginx.conf
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
