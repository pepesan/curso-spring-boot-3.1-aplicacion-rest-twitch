pipeline {
  agent any
  stages {
    stage('Setup Env') {
      steps {
        git(url: 'https://github.com/pepesan/pokedex-api-res-spring-boot-jpa.git', branch: 'main')
      }
    }

    stage('Build') {
      steps {
        sh 'mvn clean compile'
      }
    }

  }
}
