package ru.otus.homchenko.web.server.processors;

import ru.otus.homchenko.web.server.HttpRequest;

import java.io.IOException;
import java.io.OutputStream;

public interface RequestProcessor {
    void exequte(HttpRequest httpRequest, OutputStream output) throws IOException;
}
