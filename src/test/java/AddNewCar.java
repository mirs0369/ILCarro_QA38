import models.Car;
import models.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddNewCar extends TestBase{

    @BeforeMethod
    public void precondition(){

        if(app.getUser().isLogged() == false)
            app.getUser().login(
                    new User().withEmail("tanyha-333@mail.ru").withPassword("722Roksana!"));
    }

    @Test
    public void addNewCarPositive(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        Car car = Car.builder()
                .location("Tel Aviv")
                .make("Ford")
                .model("Mustang")
                .year("2023")
                .fuel("Petrol")
                .seats("5")
                .carClass("B")
                .carRegNumber("100-200-" + i)
                .price("500")
                .about("")
                .build();
        app.getCar().openCarFOrm();
        app.getCar().fillCarForm(car);
        app.getUser().submitLogin();
    }
}
