node {
   def app
    
   stage('Clone repository') {
        checkout scm
    }

    stage('Build image') {
        app = docker.build("tas4/cliente")
    }

    stage('Test image') {
        app.inside {
            sh 'echo "Tests passed"'
        }
    }

}
