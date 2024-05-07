package ru.otus.homchenko.web.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {
    private int port;
    private Dispatcher dispatcher;
    private ExecutorService threadPool;

    public HttpServer(int port) {
        this.port = port;
        this.threadPool = Executors.newFixedThreadPool(10);
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен на порту: " + port);
            this.dispatcher = new Dispatcher();
            System.out.println("Диспетчер проинициализирован");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Получен запрос от клиента");

                threadPool.execute(() ->  {
                    try {
                        byte[] buffer = new byte[8192]; //8kb
                        int n = socket.getInputStream().read(buffer);
                        String rawRequest = new String(buffer, 0, n);
                        HttpRequest request = new HttpRequest(rawRequest);
                        request.info(true);

                        dispatcher.exequte(request, socket.getOutputStream());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
