FROM damlys/webdock-web-assets-builder:0.0.1-example AS assets_builder
COPY app/ /opt/app/
WORKDIR /opt/app/
RUN npm install --unsafe-perm


FROM damlys/webdock-web-client-foundation:0.0.1-example
# has user deploy:1000
# has group deploy:1000
# exposes port 80

ENV APP_VERSION="undefined" \
    APP_HTTP_WORKERS_COUNT="1" \
    APP_HTTP_BASIC_AUTH="off" \
    APP_HTTP_BASIC_AUTH_USER="demo" \
    APP_HTTP_BASIC_AUTH_PASSWORD="Secret123" \
    APP_DEFAULT_CHARSET="UTF-8" \
    APP_DEFAULT_LOCALE="en-US" \
    APP_DEFAULT_TIMEZONE="UTC" \
    APP_ENVIRONMENT="prod" \
    APP_DEBUG="off" \
    APP_API_GATEWAY_DSN="http://username:password@127.0.0.1:80"

COPY --from=assets_builder --chown=deploy:deploy /opt/app/public/ /opt/app/public/
COPY bin/entrypoint.bash /usr/local/bin/entrypoint
COPY bin/healthcheck.bash /usr/local/bin/healthcheck
COPY etc/app/env.js.template /etc/opt/app/env.js.template
COPY etc/app/index.html.template /etc/opt/app/index.html.template
COPY etc/nginx/nginx.conf.template /etc/nginx/nginx.conf.template

RUN chmod a+x /usr/local/bin/entrypoint \
&& chmod a+x /usr/local/bin/healthcheck

WORKDIR /opt/app/
ENTRYPOINT ["entrypoint"]
CMD ["--start-http-server"]
HEALTHCHECK --timeout=3s CMD ["healthcheck"]
