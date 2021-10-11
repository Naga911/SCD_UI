pipeline {
  triggers {
        cron('H H(0-7) * * *')
  agent any
  stages {
    stage('Build') {
      steps {
        echo 'building the code'
      }
    }

    stage('Deploy') {
      steps {
        echo 'deploying the code'
      }
    }

  }
}
