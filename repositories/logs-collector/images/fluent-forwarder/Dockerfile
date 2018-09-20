FROM fluent/fluent-bit:latest

ENV FF_SHARED_SECRET="" \
    FF_FLUENT_COLLECTOR_HOST="127.0.0.1" \
    FF_FLUENT_COLLECTOR_PORT="24284" \
    FF_METADATA_VERSION="undefined"

COPY etc/ /fluent-bit/etc/

EXPOSE 24224
