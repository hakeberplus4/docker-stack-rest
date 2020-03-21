pipeline {
  environment{
      DOCKER_HOST_NAME = "tcp://atheneucp-non-prod.onintranet.com:443"
      registryCredential = 'docker-credentials'
      CURRENT_BRANCH="${GIT_BRANCH.replaceFirst(/^origin\//, '')}"
      SLEEP_PRE_EXEC="10"
  }
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
      }
    }
    stage('proto') {
      script {
        docker.withRegistry( "https://$REGISTRY", registryCredential ) {
            imageExists = sh(returnStdout: true,
                script: """
                    docker image ls -a --no-trunc |
                    grep -i ${TAG} |
                    grep -i ${IMAGE} |
                    wc -l
                    """).trim()
            imageExists = (imageExists.toInteger() > 0)
        }
        if (imageExists) {
            echo "Image already exists, skipping build..."
        }
        else {
            docker.withRegistry( "https://$REGISTRY", registryCredential ) {
                dockerImage = docker.build( "$REGISTRY/$IMAGE:$TAG", " --build-arg GIT_COMMIT=" + GIT_COMMIT + " --build-arg BUILD_NUMBER=" + BUILD_NUMBER + " .")
            }
        }
      }
    }
    stage('kubectl version') {
      steps {
        container('kubectl') {
          sh """
             kubectl version
          """
        }
      }
    }
    stage('helm list') {
      steps {
        container('helm') {
          sh "helm list"
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
  }
  post{
    always{
        script{
            echo "Complete"
        }
    }
    success{
        script{
            echo "Success"
        }
    }
    failure{
        script{
            echo "Failure"
        }
    }
  }
}
