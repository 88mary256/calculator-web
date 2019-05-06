FROM frekele/ant
RUN echo "Java with ant image started"

RUN mkdir /data
COPY . /data/
RUN echo "copied all files"

USER root
RUN whoami
RUN ls -la /data/**

CMD [ "sh", "-c", "service ssh start; bash"]

