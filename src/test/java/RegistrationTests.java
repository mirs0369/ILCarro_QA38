import manager.TestNgListener;
import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNgListener.class)


public class RegistrationTests extends TestBase{
    @BeforeMethod
    public void precondition(){
        if(app.getUser().isLogged()) app.getUser().logout();
    }




    @Test
    public void registrationPositive() {
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        User user = new User()
                .withName("John")
                .withLastName("Brown")
                .withEmail("john__" +i + "@gmail.com")
                .withPassword("123Ertg!");

        app.getUser().openRegistrationForm();
        logger.info("Open registration form invoked");
        app.getUser().fillRegistrationForm(user);
        logger.info("Fill registration form invoked");
        app.getUser().submitLogin();
        logger.info("Submit Login invoked");
        logger.info("RegistrationPositive starts with credentials: login " + user.getEmail()
                + " and password" + user.getPassword());

     Assert.assertTrue(app.getUser().isLoggedSuccess());

    }
    @Test
    public void registrationNegativePassword() {
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        User user = new User()
                .withName("John")
                .withLastName("Brown")
                .withEmail("john__" +i + "@gmail.com")
                .withPassword("123Ertg");

        app.getUser().openRegistrationForm();
        app.getUser().fillRegistrationForm(user);
        app.getUser().submitLogin();
       // Assert.assertTrue(app.getUser().isLoggedSuccess());

    }

    @AfterMethod
    public void postcondition(){
        app.getUser().clickOkButton();
    }
}
