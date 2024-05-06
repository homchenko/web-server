package ru.otus.homchenko.web.server;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    private String rawRequest;
    private String uri;
    private HttpMethod method;
    private Map<String, String> parameters;

    public String getUri() {
        return uri;
    }

    public String getParameter(String key) {
        return parameters.get(key);
    }

    public HttpRequest(String rawRequest) {
        this.rawRequest = rawRequest;
        this.parseRequestLine();
    }

    public void parseRequestLine() {
        int startIdx = rawRequest.indexOf(' ');
        int endIdx = rawRequest.indexOf(' ', startIdx + 1);
        this.uri = rawRequest.substring(startIdx + 1, endIdx);
        this.method = HttpMethod.valueOf(rawRequest.substring(0, startIdx));
        this.parameters = new HashMap<>();
        if (this.uri.contains("?")) {
            String[] elements = this.uri.split("[?]");
            this.uri = elements[0];
            String[] keysValues = elements[1].split("&");
            for (String kv : keysValues) {
                String[] keyValue = kv.split("=");
                this.parameters.put(keyValue[0], keyValue[1]);
            }
        }
    }

    public void info(boolean showRawRequest) {
        if (showRawRequest) {
            System.out.println(rawRequest);
        }
        System.out.println("URI: " + this.uri);
        System.out.println("HTTP-method: " + this.method);
        System.out.println("Parameters: " + this.parameters);
    }
}
