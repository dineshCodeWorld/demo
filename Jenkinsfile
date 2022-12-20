node {
    def app
           stage('Clone repository')
           {
              checkout scm
           }
       
         stage('Build image')
           {
               app = docker.build("teslaimage")
           }

           stage('Test image')
           {
               app.inside {
               sh 'echo "Tests Passed"'
               }
           }
           stage('Push image')
           {
              docker.withRegistry('https://hub.docker.com','docker-cred')
               {
                   app.push("${env.BUILD_NUMBER}")
                   app.push("latest")
               }
           }

}
