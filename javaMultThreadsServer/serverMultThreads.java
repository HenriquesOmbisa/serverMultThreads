package serverHttp;

import java.net.*;
import java.io.*;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;


 class MessageHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        // Configurar o cabeçalho CORS
        /*exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
        exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type,Authorization");*/

        // Aqui você pode processar a mensagem recebida
        String response = "Mensagem recebida!";
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
public class serverMultThreads {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/message", new MessageHandler());
        server.setExecutor(Executors.newCachedThreadPool()); // Usando um pool de threads
        server.start();
    }
}
