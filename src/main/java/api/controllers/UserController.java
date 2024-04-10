package api.controllers;

import api.BaseRequest;
import api.pojo.UserRequestData;
import com.microsoft.playwright.APIResponse;

public class UserController extends BaseRequest {

    private static final String GET_USERS_PATH = "api/users/";

    public static APIResponse getUsers(String token, String userId) {
        return sendGetRequest(token, GET_USERS_PATH + userId);
    }

    public static APIResponse createUser(String token) {
        UserRequestData userRequest = new UserRequestData();
        userRequest.setName("testName");
        userRequest.setJob("testJob");
        return sendPostRequest(token, GET_USERS_PATH, userRequest);
    }
}