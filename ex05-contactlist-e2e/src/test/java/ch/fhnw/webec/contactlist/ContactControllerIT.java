package ch.fhnw.webec.contactlist;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ContactControllerIT {


    @LocalServerPort
    int port;
    WebDriver driver = new HtmlUnitDriver();

    @Test
    void allContactLinksShown() {
        var links = ContactsPage.create(driver, port).getContactsLinks();
        assertEquals(30, links.size());
    }

    @Test
    void clickingContactShowsDetails(){

        var page = ContactsPage.create(driver,port);
        page.getContactsLinks().get(0).click();
        assertFalse(page.getTables().isEmpty());
    }

}