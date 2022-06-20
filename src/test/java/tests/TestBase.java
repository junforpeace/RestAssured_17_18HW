package tests;

import static com.codeborne.selenide.Selenide.open;

public class TestBase {

    String baseUrl = "https://reqres.in",
           createUrl = "/api/users",
            usersListUrl = "/api/users?page=2",
            userPutUrl = "/api/users/2",
            deleteUserUrl = "/api/users/2",
            registerUrl = "/api/register",
            notLogin = "/api/login",
            name = "morpheus",
            job = "leader",
            bodyForCreate = "{\"name\": \"morpheus\", " + "\"job\": \"leader\"}",
            bodyForDelete = "{\"email\": \"eve.holt@reqres.in\", " +
            "\"password\": \"pistol\"}",
            bodyForNotLogin = "{\"email\": \"peter@klaven\"}",
            userPutJob = "zion resident",
            bodyUpdate = "{\"nam" +
                    "e\": \"morpheus\", " + "\"job\": \"zion resident\"}";

}


