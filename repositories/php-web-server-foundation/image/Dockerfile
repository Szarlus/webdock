FROM php:fpm
# exposes port 9000

RUN groupadd --gid 1000 deploy \
&& useradd --uid 1000 --gid deploy --shell /bin/bash --create-home deploy

RUN apt-get update

RUN apt-get install --yes \
    git \
&& apt-get clean

ADD https://getcomposer.org/installer /usr/local/bin/composer-installer
RUN php /usr/local/bin/composer-installer --install-dir=/usr/local/bin/ --filename=composer \
&& COMPOSER_CACHE_DIR="/var/cache/composer/" \
&& mkdir --parents ${COMPOSER_CACHE_DIR} \
&& chmod --recursive 766 ${COMPOSER_CACHE_DIR} \
&& composer config --global cache-dir ${COMPOSER_CACHE_DIR}

RUN apt-get install --yes \
    bash-completion \
&& apt-get clean \
&& echo ". /etc/bash_completion" >> ~/.bashrc \
&& composer global require \
    bamarni/symfony-console-autocomplete \
&& composer clear-cache \
&& echo "$(php ~/.composer/vendor/bin/symfony-autocomplete --shell bash composer)" > /etc/bash_completion.d/composer

# `apache2-utils` package contains `htpasswd` tool
# `gettext` package contains `envsubst` tool
RUN apt-get install --yes \
    apache2-utils \
    gettext \
    nginx \
&& apt-get clean \
&& service nginx stop
EXPOSE 80

RUN apt-get install --yes \
    cron \
&& apt-get clean \
&& service cron stop

# `apt-get` is used here to install packages required by php libraries
RUN apt-get install --yes \
    zlib1g-dev \
&& apt-get clean \
&& pecl install \
    redis \
&& pecl clear-cache \
&& docker-php-ext-install \
    pdo_mysql \
    opcache \
    zip \
&& docker-php-ext-enable \
    redis
