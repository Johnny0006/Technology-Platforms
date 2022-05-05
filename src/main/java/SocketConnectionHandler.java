import java.io.*;
import java.net.Socket;
import java.util.Date;

public class SocketConnectionHandler implements Runnable {

    private final Socket client;

    public SocketConnectionHandler(Socket socket) {
        this.client = socket;
    }

    @Override
    public void run() {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
             PrintWriter output = new PrintWriter(client.getOutputStream(), true)) {
            while (!Thread.interrupted()) {
                String request = input.readLine();
                if (request != null) {
                    ImportService importService = new ImportService();
                    importService.importMessage(request);
                    Message message = importService.getMessage();

                    System.out.print(message.getSendDate() + " | " + new Date() + " | " + message.getUserName() + ": " + message.getContent() + "\n");

                    output.println(message.getUserName() + " OK");
                } else {
                    break;
                }
            }
        } catch (IOException ex) {

        }
    }

}
