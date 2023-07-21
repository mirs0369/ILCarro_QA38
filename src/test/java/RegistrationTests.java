import manager.ProviderData;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {

    @BeforeMethod
    public void precondition(){
        if(app.getUser().isLogged()) app.getUser().logout();
    }

    @Test
    public void registrationPositive(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        User user = new User()
                .withName("John")
                .withLastName("Snow")
                .withEmail("john_" + i + "@mail.com")
                .withPassword("$Asdf1234");

        app.getUser().openRegistrationForm();
        logger.info("openRegistrationForm invoked");
        app.getUser().fillRegistrationForm(user);
        logger.info("fillRegistrationForm invoked");
        app.getUser().submitLogin();
        logger.info("submitLogin invoked");
        logger.info("registrationPositive starts with credentials: login "
                + user.getEmail() + " & password: " + user.getPassword());
        Assert.assertTrue(app.getUser().isLoggedSuccess());
    }
    @Test(dataProvider = "userDtoCSV", dataProviderClass = ProviderData.class)
    public void registrationPositiveDTO(User user){
//        int i = (int)(System.currentTimeMillis()/1000)%3600;
//        User user = new User()
//                .withName("John")
//                .withLastName("Snow")
//                .withEmail("john_" + i + "@mail.com")
//                .withPassword("$Asdf1234");

        app.getUser().openRegistrationForm();
        logger.info("openRegistrationForm invoked");
        app.getUser().fillRegistrationForm(user);
        logger.info("fillRegistrationForm invoked");
        app.getUser().submitLogin();
        logger.info("submitLogin invoked");
        logger.info("registrationPositive starts with credentials: login "
                + user.getEmail() + " & password: " + user.getPassword());
        Assert.assertTrue(app.getUser().isLoggedSuccess());
    }
    @Test
    public void registrationNegativeWrongPassword(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        User user = new User()
                .withName("John")
                .withLastName("Snow")
                .withEmail("john_" + i + "@mail.com")
                .withPassword("Asdf1234");

        app.getUser().openRegistrationForm();
        app.getUser().fillRegistrationForm(user);
        app.getUser().submitLogin();
//        Assert.assertTrue(app.getUser().isLoggedSuccess());
    }

    @AfterMethod
    public void postcondition(){
        app.getUser().clickOkButton();
    }
}
