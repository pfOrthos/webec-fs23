package ch.fhnw.webec.contactlist;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ContactsPage {

    public static ContactsPage create(WebDriver driver, int port){
        driver.navigate().to("http://localhost:" + port + "/contacts");
        return PageFactory.initElements(driver, ContactsPage.class);
    }

    @FindBy(css = "#contacts nav a")
    private List<WebElement> contactsLinks;

    @FindBy(css= "#contacts table")
    private List<WebElement> tables;

    public List <WebElement> getContactsLinks() {
        return contactsLinks;
    }

    public List<WebElement> getTables(){
        return tables;
    }

}
