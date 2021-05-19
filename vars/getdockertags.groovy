import groovy.json.JsonSlurper
def call() {
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
