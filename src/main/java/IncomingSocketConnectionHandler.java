import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class IncomingSocketConnectionHandler implements Runnable {

    private final ServerSocket server;


    public IncomingSocketConnectionHandler(int port) {
        try {
            server = new ServerSocket(port);
            server.setSoTimeout(1000);
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Socket socket = server.accept();
                new Thread(new SocketConnectionHandler(socket)).start();
            } catch (SocketTimeoutException ex) {
            } catch (IOException ex) {
            }
        }

    }
}
