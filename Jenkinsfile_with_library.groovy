@Library('example_shared_library') _

def mvnPath = "/var/jenkins_home/tools/hudson.tasks.Maven_MavenInstallation/3.6.3/bin/mvn"

node {
    stage('Checkout SCM') {
        checkOut(params.BRANCH)
    }
    stage('Build') {
        build(mvnPath)
    }
    stage('Run Tests') {
        try {
            runTestsByTag(mvnPath, params.TAG)
        }
        catch (Exception e) {
            echo "Test run was broken"
            throw e
        }
        finally {
            stage('Allure Report Generation') {
                generateAllureReport()
            }
            cleanWs notFailBuild: true
        }
    }
}