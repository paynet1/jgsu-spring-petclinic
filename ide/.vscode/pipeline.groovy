pipeline {
    agent any
    triggers { pollSCM('H */4 * * 1-5') }    
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/paynet1/jgsu-spring-petclinic.git'
            }       
        }
        stage('Build') {
            steps {
                sh './mvnw clean package'
            }
            
            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
                changed {
                    emailext attachLog: true, body: 'Please go to ${BUILD_URL} and verify the build', 
                    compressLog: true, subject: 'job \'${JOB_NAME}\' {${BUILD_NUMBER}} is waiting for input', 
                    to: 'paynetpayne2gmail.com'
                }
            }
        }
    }
}
