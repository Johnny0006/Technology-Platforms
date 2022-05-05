import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Message implements Serializable {

    static final long serialVersionUID = 1;
    private int userName;
    private Date sendDate;
    private String content;

    public Message(int userName, String content) {
        this.userName = userName;
        this.content = content;
        this.sendDate = new Date();
    }

}
