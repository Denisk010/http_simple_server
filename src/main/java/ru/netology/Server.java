package ru.netology;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {

    final int NUMBER_OF_THREADS = 64;
    ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public void startHTTPServer(int port) {
        try (final var serverSocket = new ServerSocket(port)) {
            while (true) {
                final var socket = serverSocket.accept();
                System.out.println(socket);
                Handler handler = new Handler(socket);
                executorService.submit(handler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}