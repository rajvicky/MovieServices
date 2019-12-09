node  {
  stage ('Build') {
 
    git url: 'https://github.com/rajvicky/MovieServices'
 
    withMaven(globalMavenSettingsConfig: 'globalSettings', mavenSettingsConfig: 'settings') {
 
      bat "mvn clean install"
 
    } // withMaven will discover the generated Maven artifacts, JUnit Surefire & FailSafe reports and FindBugs reports
  }
}
