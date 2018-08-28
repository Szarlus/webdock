FROM damlys/webdock-php-web-server-foundation:0.0.1-example
# has user deploy:1000
# has group deploy:1000
# exposes port 80 and 9000

ENV APP_VERSION="undefined" \
    APP_CGI_SERVER_HOST="127.0.0.1" \
    APP_CGI_SERVER_PORT="9000" \
    APP_CGI_WORKERS_COUNT="1" \
    APP_HTTP_WORKERS_COUNT="1" \
    APP_HTTP_BASIC_AUTH="off" \
    APP_HTTP_BASIC_AUTH_USER="demo" \
    APP_HTTP_BASIC_AUTH_PASSWORD="Secret123" \
    APP_DEFAULT_CHARSET="UTF-8" \
    APP_DEFAULT_LOCALE="en-US" \
    APP_DEFAULT_TIMEZONE="UTC" \
    APP_ENVIRONMENT="prod" \
    APP_DEBUG="off" \
    APP_CACHE_STORAGE_DSN="redis://127.0.0.1:6379?auth=password&database=1" \
    APP_DATA_STORAGE_DSN="mysql://username:password@127.0.0.1:3306/dbname" \
    APP_FILE_STORAGE_DSN="http://accesskey:secretkey@127.0.0.1" \
    APP_MAIL_SENDER_URL="smtp://username:password@127.0.0.1:587" \
    APP_MESSAGE_QUEUE_DSN="amqp://username:password@127.0.0.1:5672" \
    APP_SESSION_STORAGE_DSN="redis://127.0.0.1:6379?auth=password&database=0" \
    APP_XDEBUG_REMOTE_AUTOSTART="off" \
    APP_XDEBUG_REMOTE_CONNECT_BACK="off" \
    APP_XDEBUG_REMOTE_HOST="127.0.0.1" \
    APP_XDEBUG_REMOTE_PORT="9000" \
    APP_XDEBUG_IDE_KEY="DOCKER"

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

RUN su --command "composer install" deploy
