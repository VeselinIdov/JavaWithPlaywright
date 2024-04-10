package api.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter @Setter
public class UserRequestData {
    private String name;
    private String job;
    private String id;
    private Date createdAt;

}
