def foo() {
    def get = new URL("https://api.npoint.io/94c7dfacc8e334149afb").openConnection();
    def getRC = get.getResponseCode();

    def slurper = new groovy.json.JsonSlurper()
    def finalResult = "AB,CD,EF"
    if(getRC.equals(200)) {
        def response = get.getInputStream().getText();
        def parsedResponse = slurper.parseText(response)
        println(parsedResponse.results);
        finalResult = parsedResponse.results
    }

    return finalResult
}

pipeline {
  agent any
  options {
    buildDiscarder(
      logRotator(daysToKeepStr: '5', numToKeepStr: '5')
    )
    disableConcurrentBuilds()
  }

parameters {
        extendedChoice(
            name: 'TagName',
            defaultValue: '',
            description: 'tag name',
            type: 'PT_SINGLE_SELECT',
            groovyScript: '''
                            return foo()
                          '''
        )
}

  environment {
      def GENERATED_BUILD_TAG = ''
  }

  stages {
    stage('parameterBuild') {
      when { expression { params.SkipRun } }
      steps {
        script {
          echo 'Setting job parameters'
        }
      }
    }
    stage('Tag Name ') {
      when { expression { !params.SkipRun } }
      stages {
        stage('test') {
          steps {
            script {
              echo "running on tag ${TagName}"

            }
          }
        }
      }//run stages
      post {
        success {
          script {
            env.GENERATED_BUILD_TAG = build_tag
          }
        }
      }
    }//stage run
  }//job stages
}//pipeline
