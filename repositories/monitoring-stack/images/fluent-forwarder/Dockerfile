FROM fluent/fluent-bit:latest

ENV FF_LOG_LEVEL="error" \
    FF_SHARED_SECRET="" \
    FF_FLUENT_COLLECTOR_HOST="127.0.0.1" \
    FF_FLUENT_COLLECTOR_PORT="24284"

COPY etc/ /fluent-bit/etc/

EXPOSE 24224
