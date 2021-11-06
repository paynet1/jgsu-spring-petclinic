pipeline {
    agent any
<<<<<<< HEAD
    
    stages {
        stage('Checkout') {
            steps {
                // Get some code from a GitHub repository
                git branch: 'main', url: 'https://github.com/paynet1/jgsu-spring-petclinic.git'
            }
=======
    triggers { pollSCM('H */4 * * 1-5') }    
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/paynet1/jgsu-spring-petclinic.git'
            }       
>>>>>>> 8949bb28d8ab40bb73af1a5259d7dbc5c99cdaa4
        }
        stage('Build') {
            steps {
                sh './mvnw clean package'
            }
<<<<<<< HEAD
            post {
=======
            
            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
>>>>>>> 8949bb28d8ab40bb73af1a5259d7dbc5c99cdaa4
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
<<<<<<< HEAD
=======
                changed {
                    emailext attachLog: true, body: 'Please go to ${BUILD_URL} and verify the build', 
                    compressLog: true, subject: 'job \'${JOB_NAME}\' {${BUILD_NUMBER}} is waiting for input', 
                    to: 'paynetpayne2gmail.com'
                }
>>>>>>> 8949bb28d8ab40bb73af1a5259d7dbc5c99cdaa4
            }
        }
    }
}
