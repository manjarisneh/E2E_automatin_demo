#!groovy
node {
    stage('Git checkout') { // for display purposes
        git 'https://github.com/manjarisneh/E2E_automation_demo.git'
    }
//    tools {
//        maven 'Maven 3.6.3'
//    }
    stage('Smoke') {
        try {
            sh "mvn clean verify -Dtags='type:Smoke'"
        } catch (err) {

        } finally {
            publishHTML (target: [
                    reportDir: '/Users/vishwajeetrana/Downloads/e2e-automation-pipeline-master/jenkins-pipelines/E2E-automation-test.groovy',
                    reportFiles: 'index.html',
                    reportName: "Smoke tests report"
            ])
        }
    }
    stage('API') {
        try {
            sh "mvn clean verify -Dtags='type:API'"
        } catch (err) {

        } finally {
            publishHTML (target: [
                    reportDir: '/Users/vishwajeetrana/Downloads/e2e-automation-pipeline-master/jenkins-pipelines/E2E-automation-test.groovy',
                    reportFiles: 'index.html',
                    reportName: "API tests report"
            ])
        }
    }
    stage('UI') {
        try {
            sh "mvn clean verify -Dtags='type:UI'"
        } catch (err) {

        } finally {
            publishHTML (target: [
                    reportDir: '/Users/vishwajeetrana/Downloads/e2e-automation-pipeline-master/jenkins-pipelines/E2E-automation-test.groovy',
                    reportFiles: 'index.html',
                    reportName: "UI tests report"
            ])
        }
    }
    stage('Results') {
        junit '**/target/failsafe-reports/*.xml'
    }
}