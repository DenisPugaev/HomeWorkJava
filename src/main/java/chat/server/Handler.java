package chat.server;


import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Handler implements Runnable {

    private boolean running;
    private final InputStream is;
    private final OutputStream os;
    private final Socket socket;
    private final byte[] buf;

    public Handler(Socket socket) throws IOException {
        running = true;
        buf = new byte[8192];
        is = socket.getInputStream();
        os = socket.getOutputStream();
        this.socket = socket;
    }

    public void stop(){
        running = false;
    }

    @Override
    public void run() {
        try {

            while (running) {

                int read  = is.read(buf);

                String message = new String(buf,0, read).trim();
                if(message.equals("quit")){
                    os.write("Клиент отключился.\n".getBytes(StandardCharsets.UTF_8));
                    close();
                    break;
                }
                System.out.println("Получено: " + message);
                os.write((message + "\n").getBytes(StandardCharsets.UTF_8));


            }
        }catch (Exception e){

            e.printStackTrace();
        }

    }

    private void close() throws IOException {
        os.close();
        is.close();
        socket.close();
    }
}
