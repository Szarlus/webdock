FROM fluent/fluentd:latest
# exposes port 5140 and 24224

# https://github.com/fluent/fluentd-docker-image#3-customize-dockerfile-to-install-plugins-optional
RUN apk add --update --virtual .build-deps sudo build-base ruby-dev \
&& sudo gem install \
    fluent-plugin-influxdb \
    fluent-plugin-secure-forward \
&& sudo gem sources --clear-all \
&& apk del .build-deps \
&& rm -rf /var/cache/apk/* /home/fluent/.gem/ruby/2.4.0/cache/*.gem

ENV FC_LOG_LEVEL="error" \
    FC_SHARED_SECRET="" \
    FC_INFLUXDB_HOST="127.0.0.1" \
    FC_INFLUXDB_PORT="8086" \
    FC_INFLUXDB_USER="" \
    FC_INFLUXDB_PASSWORD="" \
    FC_INFLUXDB_DATABASE="db0"

COPY etc/ /fluentd/etc/

EXPOSE 24284
