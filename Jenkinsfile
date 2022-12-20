node {
    def app
           stage('Clone repository')
           {
              checkout scm
           }
       
         stage('Build image')
           {
               app = docker.build("teslaimage/demo")
           }

           stage(Test image'')
           {
               app.inside {
               sh 'echo "Test Passed"'
               }
           }
           stage('Push image')
           {
              docker.withRegistry('')
               {
                 app.push("$(env.BUILD_NUMBER)")
                   app.push("latest")
               }
           }

}
