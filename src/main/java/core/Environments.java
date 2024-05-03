package core;

import utils.LogUtils;

public class Environments {

    public static String switchEnvironment(String currentEnvironment) {
        String environment = "";
        switch (currentEnvironment) {
            case "dev":
                environment = ConfigReader.getValue("url.dev");
                break;
            case "qa":
                environment = ConfigReader.getValue("url.qa");
                break;
            case "stage":
                environment = ConfigReader.getValue("url.stage");
                break;
            default:
                LogUtils.logError("Invalid environment specified: " + currentEnvironment);
                break;
        }
        return environment;
    }
}