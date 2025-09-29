package com.demoqa.tests;

import com.demoqa.pages.LoginPage;
import com.demoqa.pages.ProfilePage;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.File;

public class LoginDataDrivenTests extends BaseTest {

    @DataProvider(name="loginData")
    public Object[][] loginData() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode arr = mapper.readTree(new File("src/test/resources/testdata/loginData.json"));
        Object[][] data = new Object[arr.size()][3];
        for (int i=0;i<arr.size();i++){
            JsonNode n = arr.get(i);
            data[i][0] = n.get("username").asText();
            data[i][1] = n.get("password").asText();
            data[i][2] = n.get("expected").asText();
        }
        return data;
    }

    @Test(dataProvider = "loginData")
    public void testLoginScenarios(String user, String pass, String expected){
        LoginPage lp = new LoginPage(driver);
        lp.open();
        lp.login(user, pass);
        ProfilePage pp = new ProfilePage(driver);
        boolean onProfile = pp.isAt();
        if ("valid".equalsIgnoreCase(expected)){
            Assert.assertTrue(onProfile, "Expected successful login for valid user");
        } else {
            Assert.assertFalse(onProfile, "Expected to remain off profile for invalid user");
        }
    }
}
