package com.example;

import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Hello {
    public static void main(String[] args) {
        // Start a simple HTTP server
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

	server.createContext("/", new EchoHandler());

        server.setExecutor(null); // Use default executor
        server.start();
        System.out.println("Server started on port 8080");
    }

    static class EchoHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {

            exchange.sendResponseHeaders(200, 0);
            OutputStream os = exchange.getResponseBody();

	    os.write(exchange.getRequestURI().getPath().getBytes());

            os.close();
        }
    }
}
