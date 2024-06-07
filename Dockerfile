FROM ibm-semeru-runtimes:open-17-jre-focal

ADD /out/ARMStorage.jar ARMStorage.jar

ENV PORT 8080
EXPOSE 8080

CMD java -XX:MaxRAM=400m -jar ARMStorage.jar