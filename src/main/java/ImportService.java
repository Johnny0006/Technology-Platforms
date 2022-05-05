import lombok.Getter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
public class ImportService {

    private Message message;

    public ImportService() {
    }

    public void importMessage(String request) {
        try {
            String[] string = request.split("\\;");
            this.message = new Message(Integer.parseInt(string[0]), string[2]);
            Date date = new SimpleDateFormat("MMM dd HH:mm:ss z yyyy").parse(string[1].substring(4, 28));
            this.message.setSendDate(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
