package ru.otus.homchenko.web.server.processors;

import ru.otus.homchenko.web.server.HttpRequest;
import ru.otus.homchenko.web.server.processors.RequestProcessor;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class CalculatorRequestProcessor implements RequestProcessor {
    @Override
    public void exequte(HttpRequest httpRequest, OutputStream output) throws IOException {
        int a = Integer.parseInt(httpRequest.getParameter("a"));
        int b = Integer.parseInt(httpRequest.getParameter("b"));
        int result = a + b;
        String outMessage = a + " + " + b + " = " + result;

        String response = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\n<html><body><h1>" + outMessage + "</h1></body></html>"; //HTTP/1.1 - версия протокола / 200 - код ответа
        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}
