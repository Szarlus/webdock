FROM nginx:latest
# exposes port 80

RUN groupadd --gid 1000 deploy \
&& useradd --uid 1000 --gid deploy --shell /bin/bash --create-home deploy

RUN apt-get update

RUN apt-get install --yes \
    curl \
&& apt-get clean
