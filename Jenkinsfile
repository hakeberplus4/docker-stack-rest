pipeline {
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
