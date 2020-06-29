FROM openjdk:8-jre
MAINTAINER Emmanuel Kiametis <kiametis91@gmail.com>

ARG JAR_FILE

WORKDIR /usr/share/app

COPY target/${JAR_FILE} ./
RUN echo "#!/bin/bash\njava -jar ${JAR_FILE}" > ./entrypoint.sh
RUN chmod +x ./entrypoint.sh
RUN cat ./entrypoint.sh

ENTRYPOINT ["./entrypoint.sh"]