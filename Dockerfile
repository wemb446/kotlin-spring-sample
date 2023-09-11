# Kotlinの環境もdockerで管理する時用
# 起動時間が長くなるので一旦無視してもろて
#
#FROM eclipse-temurin:17-alpine
#
#ENV APP_ROOT /usr/src/app
#
#WORKDIR $APP_ROOT
#
#USER root
#
#RUN apk add --no-cache curl \
#  zip \
#  libc6-compat \
#  bash
#
#RUN ln -s /lib/libc.musl-x86_64.so.1 /lib/ld-linux-x86-64.so.2
#
#RUN curl -s https://get.sdkman.io | bash
#
#RUN bash -c " \
#  source "$HOME/.sdkman/bin/sdkman-init.sh" && \
#  sdk install kotlin 1.3.72 \
#  "
