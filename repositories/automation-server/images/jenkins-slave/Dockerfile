FROM jenkins/jnlp-slave:3.23-1
# has user jenkins:10000
# has group jenkins:10000

USER root

RUN apt-get update \
&& apt-get install --yes \
    make \
&& apt-get clean

# docker (https://download.docker.com/linux/static/stable/x86_64/)
RUN DOCKER_VERSION="18.06.1-ce" \
&& mkdir -p /var/tmp/docker-setup/ \
&& curl -fsSL https://download.docker.com/linux/static/stable/x86_64/docker-${DOCKER_VERSION}.tgz -o /var/tmp/docker-setup/archive.tgz \
&& tar -zxf /var/tmp/docker-setup/archive.tgz -C /var/tmp/docker-setup/ \
&& mv /var/tmp/docker-setup/docker/docker /usr/local/bin/docker \
&& rm -rf /var/tmp/docker-setup/ \
&& chmod a+rx /usr/local/bin/docker

# docker-compose (https://github.com/docker/compose/releases)
RUN COMPOSE_VERSION="1.22.0" \
&& curl -fsSL https://github.com/docker/compose/releases/download/${COMPOSE_VERSION}/docker-compose-Linux-x86_64 -o /usr/local/bin/docker-compose \
&& chmod a+rx /usr/local/bin/docker-compose

# kubectl (https://storage.googleapis.com/kubernetes-release/release/stable.txt)
RUN KUBERNETES_VERSION="1.11.2" \
&& curl -fsSL https://storage.googleapis.com/kubernetes-release/release/v${KUBERNETES_VERSION}/bin/linux/amd64/kubectl -o /usr/local/bin/kubectl \
&& chmod a+rx /usr/local/bin/kubectl

# helm (https://github.com/kubernetes/helm/releases)
RUN HELM_VERSION="2.10.0" \
&& mkdir -p /var/tmp/helm-setup/ \
&& curl -fsSL https://storage.googleapis.com/kubernetes-helm/helm-v${HELM_VERSION}-linux-amd64.tar.gz -o /var/tmp/helm-setup/archive.tar.gz \
&& tar -zxf /var/tmp/helm-setup/archive.tar.gz -C /var/tmp/helm-setup/ \
&& mv /var/tmp/helm-setup/linux-amd64/helm /usr/local/bin/helm \
&& rm -rf /var/tmp/helm-setup/ \
&& chmod a+rx /usr/local/bin/helm

# consul (https://releases.hashicorp.com/consul/)
RUN CONSUL_VERSION="1.2.2" \
&& mkdir -p /var/tmp/consul-setup/ \
&& curl -fsSL https://releases.hashicorp.com/consul/${CONSUL_VERSION}/consul_${CONSUL_VERSION}_linux_amd64.zip -o /var/tmp/consul-setup/archive.zip \
&& unzip /var/tmp/consul-setup/archive.zip -d /var/tmp/consul-setup/ \
&& mv /var/tmp/consul-setup/consul /usr/local/bin/consul \
&& rm -rf /var/tmp/consul-setup/ \
&& chmod a+rx /usr/local/bin/consul

# terraform (https://releases.hashicorp.com/terraform/)
RUN TERRAFORM_VERSION="0.11.8" \
&& mkdir -p /var/tmp/terraform-setup/ \
&& curl -fsSL https://releases.hashicorp.com/terraform/${TERRAFORM_VERSION}/terraform_${TERRAFORM_VERSION}_linux_amd64.zip -o /var/tmp/terraform-setup/archive.zip \
&& unzip /var/tmp/terraform-setup/archive.zip -d /var/tmp/terraform-setup/ \
&& mv /var/tmp/terraform-setup/terraform /usr/local/bin/terraform \
&& rm -rf /var/tmp/terraform-setup/ \
&& chmod a+rx /usr/local/bin/terraform

# vault (https://releases.hashicorp.com/vault/)
RUN VAULT_VERSION="0.11.0" \
&& mkdir -p /var/tmp/vault-setup/ \
&& curl -fsSL https://releases.hashicorp.com/vault/${VAULT_VERSION}/vault_${VAULT_VERSION}_linux_amd64.zip -o /var/tmp/vault-setup/archive.zip \
&& unzip /var/tmp/vault-setup/archive.zip -d /var/tmp/vault-setup/ \
&& mv /var/tmp/vault-setup/vault /usr/local/bin/vault \
&& rm -rf /var/tmp/vault-setup/ \
&& chmod a+rx /usr/local/bin/vault

USER jenkins
