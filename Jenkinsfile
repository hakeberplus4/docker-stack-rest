pipeline {
  agent {
    kubernetes {
      label 'k8s-agent-demo'
      defaultContainer 'k8s-agent'
     }
   }

  stages {
    stage('Info') {
      steps {
        container('docker') {
          sh """
             docker info
          """
        }
        container('docker') {
          sh """
             docker version
          """
        }
      }
    }
    stage('Run kubectl') {
      steps {
        container('kubectl') {
          sh """
             kubectl get pods
          """
        }
      }
    }
    stage('Run helm') {
      steps {
        container('helm') {
          sh "helm list"
        }
      }
    }
  }
}
