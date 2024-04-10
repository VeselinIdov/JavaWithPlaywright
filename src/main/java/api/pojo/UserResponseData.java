package api.pojo;

import lombok.Getter;

@Getter
public class UserResponseData {
    private Data data;
    private Support support;

    @Getter
    public static class Data {
        private int id;
        private String email;
        private String first_name;
        private String last_name;
        private String avatar;
    }

    @Getter
    public static class Support {
        private String url;
        private String text;
    }
}