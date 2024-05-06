package ru.otus.homchenko.web.server;

public class Main {
    public static void main(String[] args) {
        new HttpServer(8189).start(); //http://localhost:8189/
    }
}