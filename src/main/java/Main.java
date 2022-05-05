import java.io.*;
import java.net.Socket;

public class Main{

    public static void main(String[] args) {

        try (Socket client = new Socket("localhost", 9797)) {
            try (BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
                 PrintWriter output = new PrintWriter(client.getOutputStream(), true)) {
                 Message[] message = new Message[2];
                 ExportService[] exportService = new ExportService[2];
                 message[0] = new Message(1,"Hello!");
                 message[1] = new Message(1,"How are you?");
                 for(int i=0; i<2; i++) {
                     exportService[i] = new ExportService(message[i]);
                     exportService[i].export(output);
                     System.out.print(input.readLine());
                 }

            }
        } catch (IOException ex) {
            System.err.println(ex);
        }


    }

}

