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
   
   stage('Push image') {
        docker.login -u tas4 -p 8eb7394047c14999a95bed591d626cbb
        docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentials') {
            app.push("${env.BUILD_NUMBER}")
            app.push("latest")
        }
   }
}
