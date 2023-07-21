import manager.ProviderData;
import manager.TestNgListener;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNgListener.class)
public class LoginTests extends TestBase{

    @BeforeMethod
    public void precondition(){
        if(app.getUser().isLogged()) app.getUser().logout();
    }


    @Test
    public void loginPositive(){

        app.getUser().openLoginForm();
        app.getUser().fillLoginForm("asd@fgh.com","$Asdf1234");
        app.getUser().submitLogin();
        Assert.assertTrue(app.getUser().isLoggedSuccess());

    }
    @Test
    public void loginPositiveProps(){

        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(app.getEmail(), app.getPassword());
        app.getUser().submitLogin();
        Assert.assertTrue(app.getUser().isLoggedSuccess());

    }
    @Test
    public void loginPositiveUser(){
//    User user = new User("asd@fgh.com","$Asdf1234");
        User user = new User()
                .withEmail("asd@fgh.com")
                .withPassword("$Asdf1234")
                ;
//        user.setEmail("asd@fgh.com");
//        user.setPassword("$Asdf1234");
//        user.setEmail("");
        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(user.getEmail(), user.getPassword());
        app.getUser().submitLogin();
        Assert.assertTrue(app.getUser().isLoggedSuccess());
    }
    @Test
    public void loginPositiveUserData(){
        User user = new User()
                .withEmail("asd@fgh.com")
                .withPassword("$Asdf1234")
                ;

        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(user);
        app.getUser().submitLogin();
        Assert.assertTrue(app.getUser().isLoggedSuccess());
    }
    @Test(dataProvider = "userDto", dataProviderClass = ProviderData.class)
    public void loginPositiveUserDTO(User user){
//        User user = new User()
//                .withEmail("asd@fgh.com")
//                .withPassword("$Asdf1234")
//                ;
        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(user);
        app.getUser().submitLogin();
        Assert.assertTrue(app.getUser().isLoggedSuccess());
    }

    @AfterMethod
    public void postcondition(){
        app.getUser().clickOkButton();
    }
}
