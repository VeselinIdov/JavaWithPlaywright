package api;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import utils.LogUtils;
import utils.PlaywrightDriver;

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

    public static APIResponse sendDeleteRequest(String token, String path) {
        createAPIRequestContext(token);
        APIResponse apiResponse = null;
        if (request != null) {
            apiResponse = request.delete(path);
        }

        LogUtils.logInfo("Delete request sent to: " + path);
        return apiResponse;
    }

    public static APIResponse sendGetRequest(String token, String path) {
        createAPIRequestContext(token);
        APIResponse apiResponse = null;
        if (request != null) {
            apiResponse = request.get(path);
        }

        LogUtils.logInfo("Get request sent to: " + path);
        return apiResponse;
    }

    public static APIResponse sendPostRequest(String token, String path, Object data) {
        createAPIRequestContext(token);
        APIResponse apiResponse = null;
        if (request != null) {
            apiResponse = request.post(path, RequestOptions.create().setData(data));
        }

        LogUtils.logInfo("Post request sent to: " + path);
        return apiResponse;
    }

    public static APIResponse sendPutRequest(String token, String path, Object data) {
        createAPIRequestContext(token);
        APIResponse apiResponse = null;
        if (request != null) {
            apiResponse = request.put(path, RequestOptions.create().setData(data));
        }

        LogUtils.logInfo("Put request sent to: " + path);
        return apiResponse;
    }

    public static void disposeAPIRequestContext() {
        if (request != null) {
            request.dispose();
            request = null;
            LogUtils.logInfo("API request context disposed");
        }
    }
}