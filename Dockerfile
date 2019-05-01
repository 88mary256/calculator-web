FROM frekele/ant
RUN echo "Java with ant image started"

RUN mkdir /data
COPY . /data/
RUN echo "copied all files"
RUN pwd
RUN ls
USER root
RUN apt-get install -y sudo
ADD . /usr/local/
RUN chmod 777 /usr/local/start-all.sh
CMD ["/usr/local/start-all.sh"]
