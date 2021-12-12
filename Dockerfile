# 基础镜像是docker仓库的java:8（JDK8）
FROM java:8 
#  作者签名
MAINTAINER czy czy725@yeah.com
#  挂载宿主机jar包到镜像  /platform-pay-1.0.0.jar  和 下个指令对应即可，命名并非一定要和jar名一样，为了能够识别
COPY target/TalToolsTest-1.0-SNAPSHOT.jar /usr/local/TalToolsTest-1.0-SNAPSHOT.jar
#  设置对外端口为 9999
 EXPOSE 9999
 # 执行启动命令
 ENTRYPOINT ["java","-jar","/usr/local/TalToolsTest-1.0-SNAPSHOT.jar"]
