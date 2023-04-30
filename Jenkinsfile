pipeline {
  agent any
  tools {
    jdk 'jdk-17'
  }
  options {
    buildDiscarder(logRotator(numToKeepStr: '5'))
  }
  environment {
    DOCKERHUB_CREDENTIALS = credentials('dockerhub')
  }
  stages {
    stage('Login') {
      steps {
        sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
      }
    }
    stage('Build') {
      steps {
        sh './gradlew jib'
      }
    }
    stage('Kubernetes deploy') {
        steps {
            script {
                withKubeConfig([credentialsId: 'kubeconfig', serverUrl: 'https://192.168.219.108:6443']) {
                  sh 'curl -LO "https://storage.googleapis.com/kubernetes-release/release/v1.20.5/bin/linux/amd64/kubectl"'
                  sh 'chmod u+x ./kubectl'
                  sh './kubectl apply -f k8s.yaml'
                  sh "./kubectl rollout restart deployment/tg-core-service -n backend-server"
                }
            }
        }
    }
  }
  post {
    always {
      sh 'docker logout'
    }
  }
}
