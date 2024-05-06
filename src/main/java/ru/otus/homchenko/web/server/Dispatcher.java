package ru.otus.homchenko.web.server;

import ru.otus.homchenko.web.server.processors.CalculatorRequestProcessor;
import ru.otus.homchenko.web.server.processors.HelloWorldRequestProcessor;
import ru.otus.homchenko.web.server.processors.RequestProcessor;
import ru.otus.homchenko.web.server.processors.UnknownOperationRequestProcessor;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class Dispatcher {
    private Map<String, RequestProcessor> router;
    private RequestProcessor unknownOperationsRequestProcessor;

    public Dispatcher() {
        this.router = new HashMap<>();
        this.router.put("/calc", new CalculatorRequestProcessor());
        this.router.put("/hello", new HelloWorldRequestProcessor());
        this.unknownOperationsRequestProcessor = new UnknownOperationRequestProcessor();
    }
    public void exequte(HttpRequest httpRequest, OutputStream outputStream) throws IOException {
        if (!router.containsKey(httpRequest.getUri())) {
            unknownOperationsRequestProcessor.exequte(httpRequest, outputStream);
            return;
        }
        router.get(httpRequest.getUri()).exequte(httpRequest, outputStream);
    }
}
