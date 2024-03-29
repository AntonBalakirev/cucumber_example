def mvn = "/var/lib/jenkins/tools/hudson.tasks.Maven_MavenInstallation/mvn/bin/mvn"

pipeline {
    agent any
    parameters {
        string(name: 'TAG', defaultValue: '@smoke', description: 'тег для запуска')
    }
    stages {
        stage('Build') {
            steps {
                sh "${mvn} clean compile"
            }
        }
        stage('Run Tests') {
            steps {
                sh "${mvn} test -Dcucumber.filter.tags=\"${params.TAG}\""
            }
        }
        stage('Allure Report Generation') {
            steps {
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
