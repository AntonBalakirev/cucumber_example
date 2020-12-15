pipeline{
    agent any
    stages{
        stage('Run Tests'){
            steps{
                withMaven(jdk: 'openjdk_1.8', maven: 'mvn_3.6.3') {
                    bat "mvn clean test -Dcucumber.filter.tags=\"${TAGS}\""
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