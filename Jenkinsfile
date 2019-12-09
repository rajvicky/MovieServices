node  {
  stage ('Build') {
 
    git url: 'https://github.com/rajvicky/MovieServices'
  }
   stage('Compile stage') {
            steps {
                bat "mvn clean compile" 
        }
    }
}
