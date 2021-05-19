def call() {
        def get = new URL("https://api.npoint.io/94c7dfacc8e334149afb").openConnection();
        def getRC = get.getResponseCode();
        print "line 4 executed"

        def slurper = new groovy.json.JsonSlurper()
        def finalResult = "AB,CD,EF"
        if(getRC.equals(200)) {
            print "received api response."
            def response = get.getInputStream().getText();
            def parsedResponse = slurper.parseText(response)
            println(parsedResponse.results);
            finalResult = parsedResponse.results
        }
        print "returning result"
        return finalResult
}
