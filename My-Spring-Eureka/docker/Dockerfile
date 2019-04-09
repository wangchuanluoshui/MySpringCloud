FROM  ubuntu
MAINTAINER    linx

#把java添加到容器中
ADD jdk-8u31-linux-x64.tar.gz /usr/local/

#配置java环境变量
ENV JAVA_HOME /usr/local/jdk1.8.0_31
ENV CLASSPATH $JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar

ENV PATH $PATH:$JAVA_HOME/bin:$CATALINA_HOME/lib:$CATALINA_HOME/bin