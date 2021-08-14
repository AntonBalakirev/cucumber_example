def mvn = "/var/jenkins_home/tools/hudson.tasks.Maven_MavenInstallation/3.6.3/bin/mvn"

pipeline{
    agent any
    stages{
        stage ('Build') {
            steps{
                sh "${mnv} clean build"
            }
        }
        stage ('Run Tests'){
            steps{
                sh "${mnv} test -Dcucumber.filter.tags=\"${TAG}\""
            }
        }
        stage('Allure Report Generation'){
            steps{
                allure includeProperties: false,
                        jdk: '',
                        results: [[path: 'target/reports/allure-results']]
            }
        }
    }
    post {
        always {
            cleanWs notFailBuild: true
        }
    }
}