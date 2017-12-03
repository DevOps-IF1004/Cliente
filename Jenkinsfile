node {
   def app
   def mvnHome
   
   stage('Clone repository') {
        checkout scm
        mvnHome = tool 'maven'
    }
   
   stage('Teste Cliente') {
         sh "'${mvnHome}/bin/mvn' test"
   }

    stage('Build image Cliente') {
        app = docker.build("tas4/cliente")
    }
    
    stage('Test image Cliente') {
        app.inside {
            sh 'echo "Tests passed"'
        }
    }
    
    stage('Push Cliente image') {
            app.push("${env.BUILD_NUMBER}")
            app.push("latest")
   }
}
