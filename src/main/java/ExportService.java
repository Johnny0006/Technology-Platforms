import java.io.PrintWriter;

public class ExportService {

    private final Message message;

    public ExportService(Message message) {
        this.message = message;
    }

    public void export(PrintWriter output) throws IllegalStateException {
        String string = message.getUserName() + ";" + message.getSendDate().toString() + ";" + message.getContent();
        output.println(string);
    }

}
