pipeline {
  agent {
    kubernetes {
      label 'k8s-agent-demo'
      defaultContainer 'k8s-agent'
      yaml """
apiVersion: v1
kind: Pod
metadata:
labels:
  component: ci
spec:
  # Use service account that can deploy to all namespaces
  serviceAccountName: k8s-agent
  containers:
  - name: docker
    image: docker:latest
    command:
    - cat
    tty: true
    volumeMounts:
    - name: docker-sock
      mountPath: /var/run/docker.sock
  volumes:
    - name: docker-sock
      hostPath:
        path: /var/run/docker.sock
"""
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
      container('kubectl') {
        sh """
           kubectl get pods
        """
      }
    }
    stage('Run helm') {
      container('helm') {
        sh "helm list"
      }
    }
  }
}
