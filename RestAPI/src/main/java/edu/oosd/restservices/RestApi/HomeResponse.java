package edu.oosd.restservices.RestApi;

public class HomeResponse {

    private String message;
    private Links links;

    public HomeResponse(String message, Links links) {
        this.message = message;
        this.links = links;
    }

    public String getMessage() {
        return message;
    }

    public Links getLinks() {
        return links;
    }
}
