pipeline{
    agent { label 'mbaykova' }
    stages{
        stage('Run Tests'){
            steps{
                sh label: '', script: 'chmod +x src/main/resources/drivers/chromedriver_linux'
                withMaven(jdk: 'Java 1.8', maven: 'Maven3') {
                    sh "mvn clean test -Dcucumber.filter.tags=\"${TAGS}\""
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