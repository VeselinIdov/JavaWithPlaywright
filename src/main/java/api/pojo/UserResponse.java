package api.pojo;

public class UserResponse {
    private Data data;
    private Support support;

    private static class Data {
        private int id;
        private String email;
        private String first_name;
        private String last_name;
        private String avatar;
    }

    private static class Support {
        private String url;
        private String text;
    }
}