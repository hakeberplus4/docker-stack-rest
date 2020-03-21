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
  - name: kubectl
    image: bitnami/kubectl:1.14
    command:
    - cat
    tty: true
  - name: helm
    image: alpine/helm:3.1.2
    command:
    - cat
    tty: true
  - name: docker
    image: docker:19.03
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
      steps {
        container('kubectl') {
          sh """
             kubectl version
          """
        }
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
