package chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerIo {


    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(8189);
        System.out.println("Сервер запущен...");

        while (true) {
            try {


                Socket socket = server.accept();
                System.out.println("Клиент подключился");

                new Thread(new Handler(socket)).start();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
