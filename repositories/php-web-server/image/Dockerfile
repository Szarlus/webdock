ARG DOCKER_IMAGE_PREFIX="damlys/webdock-"

FROM ${DOCKER_IMAGE_PREFIX}php-web-server-foundation:0.0.1-example
# has user deploy:1000
# has group deploy:1000
# exposes port 80 and 9000

ENV WEBSERVER_VERSION="undefined" \
    WEBSERVER_CGI_SERVER_HOST="127.0.0.1" \
    WEBSERVER_CGI_SERVER_PORT="9000" \
    WEBSERVER_CGI_WORKERS_COUNT="1" \
    WEBSERVER_HTTP_WORKERS_COUNT="1" \
    WEBSERVER_HTTP_BASIC_AUTH="off" \
    WEBSERVER_HTTP_BASIC_AUTH_USER="demo" \
    WEBSERVER_HTTP_BASIC_AUTH_PASSWORD="Secret123" \
    WEBSERVER_DEFAULT_CHARSET="UTF-8" \
    WEBSERVER_DEFAULT_LOCALE="en-US" \
    WEBSERVER_DEFAULT_TIMEZONE="UTC" \
    WEBSERVER_ENVIRONMENT="prod" \
    WEBSERVER_DEBUG="off" \
    WEBSERVER_CACHE_STORAGE_DSN="redis://127.0.0.1:6379?auth=password&database=1" \
    WEBSERVER_DATA_STORAGE_DSN="mysql://username:password@127.0.0.1:3306/dbname" \
    WEBSERVER_FILE_STORAGE_DSN="http://accesskey:secretkey@127.0.0.1" \
    WEBSERVER_MAIL_SENDER_URL="smtp://username:password@127.0.0.1:587" \
    WEBSERVER_MESSAGE_QUEUE_DSN="amqp://username:password@127.0.0.1:5672" \
    WEBSERVER_SESSION_STORAGE_DSN="redis://127.0.0.1:6379?auth=password&database=0" \
    WEBSERVER_XDEBUG_REMOTE_AUTOSTART="off" \
    WEBSERVER_XDEBUG_REMOTE_CONNECT_BACK="off" \
    WEBSERVER_XDEBUG_REMOTE_HOST="127.0.0.1" \
    WEBSERVER_XDEBUG_REMOTE_PORT="9000" \
    WEBSERVER_XDEBUG_IDE_KEY="DOCKER"

COPY --chown=deploy:deploy app/ /opt/app/
COPY bin/entrypoint.bash /usr/local/bin/entrypoint
COPY bin/healthcheck.bash /usr/local/bin/healthcheck
COPY bin/operation.bash /usr/local/bin/operation
COPY etc/cron/crontab /etc/crontab
COPY etc/nginx/nginx.conf.template /etc/nginx/nginx.conf.template
COPY etc/php/php.ini /usr/local/etc/php/conf.d/zzz-php.ini
COPY etc/php/php-fpm.conf /usr/local/etc/php-fpm.d/zzz-php-fpm.conf
COPY etc/php/xdebug.ini /usr/local/etc/php/conf.d/zzz-xdebug.ini

RUN chmod a+x /opt/app/bin/* \
&& chmod a+x /usr/local/bin/entrypoint \
&& chmod a+x /usr/local/bin/healthcheck \
&& chmod a+x /usr/local/bin/operation

WORKDIR /opt/app/
ENTRYPOINT ["entrypoint"]
CMD ["--start-http-server"]
HEALTHCHECK --timeout=3s CMD ["healthcheck"]

RUN su --command "composer install --no-interaction" deploy
