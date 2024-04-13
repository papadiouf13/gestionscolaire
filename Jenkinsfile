pipeline {
    agent any
    tools {
        jdk 'jdk17'
        maven 'maven3'
    }
    environment {
        SCANNER_HOME=tool 'sonar-scanner'
    }
    stages {
        stage('Checkout From Git') {
            steps {
                git branch: 'main', url: 'https://github.com/papadiouf13/gestionscolaire.git'
            }
        }
        stage('Clean') {
            steps {
                sh 'mvn clean'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Sonarqube Analysis ') {
            steps {
                withSonarQubeEnv('sonar-server') {
                    sh ''' $SCANNER_HOME/bin/sonar-scanner -Dsonar.projectName=demo221 \
                    -Dsonar.java.binaries=. \
                    -Dsonar.projectKey=demo221 '''
                }
            }
        }
        stage('quality gate') {
            steps {
                script {
                    waitForQualityGate abortPipeline: false, credentialsId: 'sonar-token'
                }
            }
        }
        stage('mvn build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('OWASP Dependency Check') {
            steps {
                dependencyCheck additionalArguments: '--scan ./ --format HTML ', odcInstallation: 'DP-Check'
                dependencyCheckPublisher pattern: '**/dependency-check-report.html'
            }
        }
        stage('Docker Build & Push') {
            steps {
                script {
                    withDockerRegistry(credentialsId: 'docker', toolName: 'docker') {
                        sh 'docker build -t spring-app .'
                    }
                }
            }
        }
        stage('Clean up containers') {   //if container runs it will stop and remove this block
            steps {
                script {
                    try {
                        sh 'docker stop spring-container'
                        sh 'docker rm spring-container'
                } catch (Exception e) {
                        echo 'Container spring-container not found, moving to next stage'
                    }
                }
            }
        }
        stage('Deploy to conatainer') {
            steps {
                sh 'docker run -d --name spring-container -p 8082:8080 spring-app'
            }
        }
        
    }
}
