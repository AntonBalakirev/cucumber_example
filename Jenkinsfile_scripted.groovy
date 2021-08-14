def mvn = "/var/jenkins_home/tools/hudson.tasks.Maven_MavenInstallation/3.6.3/bin/mvn"

node {
    stage('Checkout SCM') {
        checkout(
                [$class: 'GitSCM',
                 branches: [[name: "refs/heads/${BRANCH}"]],
                 userRemoteConfigs: [[url: 'https://github.com/AntonBalakirev/cucumber_example.git']]]
        )
    }
    stage('Build') {
        sh "${mvn} clean compile"
    }
    stage('Run Tests') {
        try {
            sh "${mvn} test -Dcucumber.filter.tags=\"${TAG}\""
        }
        catch (Exception e) {
            echo "Test run was broken"
            throw e
        }
        finally {
            stage('Allure Report Generation') {
                allure includeProperties: false,
                        jdk: '',
                        results: [[path: 'target/reports/allure-results']]
            }
            cleanWs notFailBuild: true
        }
    }
}