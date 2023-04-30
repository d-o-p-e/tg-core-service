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
      kubernetesDeploy configs: "k8s.yaml", kubeconfigId: 'kubeconfig'
      sh "/usr/local/bin/kubectl --kubeconfig=/u01/kube-config.yaml rollout restart deployment/tg-core-service -n backend-server"
    }
  }
  post {
    always {
      sh 'docker logout'
    }
  }
}
