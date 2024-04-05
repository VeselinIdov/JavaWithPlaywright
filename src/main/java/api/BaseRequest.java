package api;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.RequestOptions;
import utils.PlaywrightDriver;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


import java.util.HashMap;
import java.util.Map;

public class BaseRequest {
    private static APIRequestContext request;

    private static void createAPIRequestContext(String token) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Authorization", token);

        request = PlaywrightDriver.getPlaywright().request().newContext(new APIRequest.NewContextOptions()
                .setBaseURL("https://reqres.in/")
                .setExtraHTTPHeaders(headers));
    }

    public static APIResponse sendDeleteRequest(String path) {
        APIResponse deletedRepo = null;
        if (request != null) {
            deletedRepo = request.delete(path);
        }
        return deletedRepo;
    }

    public static APIResponse sendGetRequest(String token, String path) {
        createAPIRequestContext(token);
        return request.get(path);
    }

    public static APIResponse sendPostRequest(String token, String path, String data) {
        createAPIRequestContext(token);
        APIResponse apiResponse = null;
        if (request != null) {
            apiResponse = request.post(path, RequestOptions.create().setData(data));
        }
        return apiResponse;
    }

    void disposeAPIRequestContext() {
        if (request != null) {
            request.dispose();
            request = null;
        }
    }
}