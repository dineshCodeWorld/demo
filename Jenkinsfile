pipeline{
    agent any
       stages{
           stage('Git-checkout')
           {
               steps{
                   echo "Checking Out From git repo";
                   git credentialsId: 'e2da0aed-d56a-4437-a415-d9cad8a1a5ef', url: 'https://github.com/dineshCodeWorld/demo.git'
               }
           }
         stage('Deploy')
           {
               steps{
                   echo "Deploying to stage Environment for more tests";
                   powershell 'mvn clean install'
               }
           }

           stage('Build')
           {
               steps{
                   echo "Building the checkout project";
                  powershell 'java -jar  tesla-0.0.1.jar'
               }
           }
           stage('Unit-Test'){
               steps{
                   echo "Running Junit Test";
               }
           }
           stage('Quality-Gate')
           {
               steps{
                   echo "Verifying Quality Gates";
               }
           }
 
       }
       post {
           always {
               echo "This will always run";
           }
           success {
               echo "This will run only if success";
           }
           failure {
               echo "this will run only if failure";
           }
           unstable {
               echo "thhis will run onlly if run was marked as unstable";
           }
           changed {
               echo "this will run if state of pipeline has changed";
               echo "For example, if the pipeline was previously failed but is now successfull";
           }
       }
}
