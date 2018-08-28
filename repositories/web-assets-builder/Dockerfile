FROM node:latest
# has user node:1000
# has group node:1000

RUN apt-get update

RUN NPM_CACHE_DIR="/var/cache/npm/" \
&& mkdir --parents ${NPM_CACHE_DIR} \
&& chmod --recursive 766 ${NPM_CACHE_DIR} \
&& npm config set cache ${NPM_CACHE_DIR} --global

RUN apt-get install --yes \
    git \
&& apt-get clean

RUN npm install --global \
    sass \
&& npm cache clean --force

RUN npm install --global \
    typescript \
&& npm cache clean --force

RUN npm install --global \
    webpack \
    webpack-cli \
&& npm cache clean --force

RUN apt-get install --yes \
    bash-completion \
&& apt-get clean \
&& echo ". /etc/bash_completion" >> ~/.bashrc \
&& npm completion > /etc/bash_completion.d/npm
