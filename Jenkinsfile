pipeline {
  agent {
    kubernetes {
      yaml """
apiVersion: v1
kind: Pod
metadata:
  labels:
    some-label: some-label-value
spec:
  containers:
    - name: jnlp
      image: markmiller/jenkins-slave-k8s:1.3
      tty: true
      securityContext:
        privileged: true
        runAsUser: 0
      volumeMounts:
        - name: dockersock
          mountPath: "/var/run/docker.sock"
    - name: maven
      image: markmiller/maven:alpine
      command:
      - cat
      tty: true
    - name: busybox
      image: busybox
      command:
      - cat
      tty: true
  volumes:
  - name: dockersock
    hostPath:
      path: /var/run/docker.sock

"""
    }
  }
  stages {
    stage('docker info') {
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
        container('docker') {
          sh """
             nslookup git.onintranet.com || nslookup jira.onintranet.com || nslookup stt.onintranet.com
          """
        }
      }
    }
  stages {
    stage('Compile') {
      steps {
        container('maven') {
          sh 'mvn -version'
        }
      }
    }
    stage('Unit Test') {
      steps {
        container('busybox') {
          sh 'echo "Unit Testing!"'
        }
      }
    }
    stage('Build Image') {
      steps {
        container('busybox') {
          sh 'echo "Build the Docker Image!"'
        }
        script {
          sh 'echo "skip build image"'
          // docker.build("markmiller/python-image")
        }
      }
    }
    stage('Kubernetes Deploy to Dev') {
      steps {
        script {
          kubernetesDeploy(configs: "kubernetes/deployment.yml", kubeconfigId: "markskubeconfig")
        }
      }
    }
    stage('Automated Functional Test') {
      steps {
        container('busybox') {
          sh 'echo "Automated Functional Testing!"'
        }
      }
    }
  }
}
