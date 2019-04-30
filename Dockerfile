FROM frekele/ant
RUN echo "Java with ant image started"
RUN apt-get update && apt-get install -y \
    git
RUN echo "git installed"

RUN mkdir /data
COPY . /data/
RUN echo "copied all files"

# RUN git clone https://github.com/88mary256/calculator-web.git
# RUN echo "repository downloaded"
# RUN cd calculator-web
# RUN ant clean dist