# Tal_Back
业务工具实现
技术栈：
- springboot 2.2.9
- mybatis 2.1.4

- 接口的聚合结果查询
- 打包命令
mvn clean package -Dmaven.test.skip=true
- 查看本机安装jdk位置：/usr/libexec/java_home -V
- java命令启动（后台启动）：
nohup java -jar TalToolsTest-1.0-SNAPSHOT.jar --spring.profiles.active=dev >>agile.log 2>&1 &

- dockerfiel 构建命令
docker build --no-cache=true -t test/demo:v1 . 
