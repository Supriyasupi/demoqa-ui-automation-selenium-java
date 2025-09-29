package com.demoqa.tests;

import com.demoqa.pages.CheckBoxPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckBoxTests extends BaseTest {

    @Test
    public void selectCheckboxesAndValidateOutput(){
        CheckBoxPage page = new CheckBoxPage(driver);
        page.open();
        page.expandAll();
        page.selectNode("Documents");
        page.selectNode("Downloads");
        String out = page.getOutput().toLowerCase();
        Assert.assertTrue(out.contains("documents") && out.contains("downloads"),
                "Output should mention the selected nodes");
    }
}
