package edu.oosd.restservices.RestApi;

public class Links {

    private String self;
    private String greeting;
    private String greetingWName;
    private String badReq;
    private String forbiddenReq;

    public Links(String self, String greeting, String greetingWName, String badReq, String forbiddenReq) {

        this.self = self;
        this.greeting = greeting;
        this.greetingWName = greetingWName;
        this.badReq = badReq;
        this.forbiddenReq = forbiddenReq;
    }

    public String getSelf() {
        return self;
    }

    public String getGreeting() {
        return greeting;
    }

    public String getGreetingWName() {
        return greetingWName;
    }

    public String getBadReq() {
        return badReq;
    }

    public String getForbiddenReq() {
        return forbiddenReq;
    }
}
