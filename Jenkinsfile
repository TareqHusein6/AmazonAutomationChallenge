pipeline {

  agent any 

    stages {
      stage("build") {
        steps {
          echo 'building the application...'
        }
      stage("test"){
        steps{
          echo 'testing the application...'
        }
      }
      stages("deploy"){
          steps {
            echo 'deploying the application...'
          }
        }
    }
  }
}

node {
  // groovy script
}

    
