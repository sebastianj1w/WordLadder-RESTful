############################################################
# Dockerfile to build a wordladder container images
# Based on Ubuntu
############################################################
# Set the base image to Ubuntu
FROM ubuntu

# File Author / Maintainer
MAINTAINER Sebastianj1w

# Add jdk and application to the image
ADD WordLadder.jar /Application/WordLadder.jar
ADD jdk-12.0.1 /jdk

# Set environment variables
ENV JAVA_HOME /jdk
ENV PATH $JAVA_HOME/bin:$PATH
ENV CLASSPATH .:$JAVA_HOME/lib/

# Expose 8081 port for application
EXPOSE 8081

# Run the app
CMD "java" "-jar" "/Application/WordLadder.jar"

# A prompt
# CMD "echo" "Hi there! WordLadder Application will start soon"