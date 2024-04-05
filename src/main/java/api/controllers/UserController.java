package api.controllers;

import api.BaseRequest;

public class UserController extends BaseRequest{

    private static final String GET_USERS_PATH = "api/users/2";

    public static String getUsers(String token) {
        return sendGetRequest(token, GET_USERS_PATH).text();
    }
}