pipeline{
    agent any
    stages{
        stage('Run Tests'){
            steps{
                withMaven(maven: 'maven_3.6.3') {
                    bat "mvn clean test -Dcucumber.filter.tags='${TAGS}' -ntp"
                }
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