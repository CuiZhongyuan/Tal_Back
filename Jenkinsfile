pipeline{
     agent {
        node {
            label '127.0.0.1'
            customWorkspace '/Users/cuizhongyuan/Desktop/work/010_project/jenkinsdemo/TalTools'
        }
    }
    environment {
        MVN_CMD_PATH='/Users/cuizhongyuan/Desktop/work/008_soft/apache-maven-3.8.1/bin/mvn'
        JAVA_CMD_PATH='/Library/Java/JavaVirtualMachines/jdk1.8.0_241.jdk/Contents/Home/bin/java'
        RUN_HOME='/Users/cuizhongyuan/Desktop/work/010_project/jenkinsdemo/TalTools'
    }
    stages {
        stage('拉取代码') {
            steps{
                sh 'cd /usr'
                echo '拉取代码'
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: 'gitee', url: 'https://gitee.com/testcui/TalTools.git']]])
                sh 'git rev-parse --short HEAD'
            }
        }
        stage('编译打包') {
            steps{
                sh 'pwd'
                sh 'ls'
                sh "${env.MVN_CMD_PATH} clean package -Dmaven.test.skip=true"
                echo '编译成功'

            }
        }
        stage('应用发布') {
            steps{
                sh '''
                    PID=$(ps aux|grep TalToolsTest-1.0-SNAPSHOT.jar | grep -v grep | awk \'{print $2}\')
                    echo TalToolsTest-1.0-SNAPSHOT pid:$PID
                    if [ ! $PID ];then
                        echo TalToolsTest-1.0-SNAPSHOT process not exist
                    else
                        kill -9 $PID
                    fi
                '''
                sh returnStdout: true, script: """
                    cd ${env.RUN_HOME}
                    JENKINS_NODE_COOKIE=dontkillme ${env.JAVA_CMD_PATH} -jar  ${env.RUN_HOME}/target/TalToolsTest-1.0-SNAPSHOT.jar > /dev/null 2>&1 &
                """


            }
        }
        stage('发布结果检测') {
            steps {
                sh 'ls'
                sh 'java -version'
                sh '''
                    PID=$(ps aux|grep TalToolsTest-1.0-SNAPSHOT | grep -v grep | awk \'{print $2}\')
                    echo coco-server pid:$PID
                    if [ ! $PID ];then
                        echo new TalToolsTest-1.0-SNAPSHOT pid not exist
                        exit 1
                    else
                        echo new TalToolsTest-1.0-SNAPSHOT pid: $PID
                        exit 0
                    fi
                '''
            }
        }
        stage('接口自动化测试') {
            steps{
                echo '5====This is a AutoTest step'
            }
        }
        stage('代码覆盖率统计') {
            steps{
                echo '6====This is a AutoTest step'
            }
        }
        stage('...') {
            steps{
                echo '其他步骤构建'
            }
        }
    }
}
