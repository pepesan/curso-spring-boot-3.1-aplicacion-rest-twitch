pipeline {
  agent {
        docker {
            // Especifica la imagen de Docker que se utilizará en el agente
            image 'maven:3.9.6-amazoncorretto-21'
        }
    }
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
