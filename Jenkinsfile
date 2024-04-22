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
                    sh ''' $SCANNER_HOME/bin/sonar-scanner -Dsonar.projectName=Projet_Servlet \
                    -Dsonar.java.binaries=. \
                    -Dsonar.projectKey=Projet_Servlet '''
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
                    withDockerRegistry(credentialsId: '7bce7f99-9782-4f6e-b72f-e72ea703244a', toolName: 'docker') {
                        sh 'docker build -t servlet-app .'
                    }
                }
            }
        }
        stage('Clean up containers') {   //if container runs it will stop and remove this block
            steps {
                script {
                    try {
                        sh 'docker stop servlet-container'
                        sh 'docker rm servlet-container'
                } catch (Exception e) {
                        echo 'Container servlet-container not found, moving to next stage'
                    }
                }
            }
        }
        stage('Deploy to conatainer') {
            steps {
                sh 'docker run -d --name servlet-container -p 8082:8080 servlet-app'
            }
        }
        
    }
}
