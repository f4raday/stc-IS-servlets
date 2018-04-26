import model.classes.User;
import model.services.UserService;
import model.services.interfaces.IUserService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserServiceTests {

    private IUserService userService;
    private User testUser;

    @Before
    public void setUpDAO() {
        userService = new UserService();
        testUser = new User("JUNIT_TEST_USER","JUNIT_TEST_USER", "JUNIT_TEST_USER");
    }

    @Test
    public void newUserTestLoginOnly() {
        Assert.assertTrue(userService.findUserByLogin("JUNIT_TEST_USER").equals(User.Empty()));
        userService.save("JUNIT_TEST_USER","JUNIT_TEST_USER","JUNIT_TEST_USER");
        Assert.assertTrue(userService.findUserByLogin("JUNIT_TEST_USER").equals(testUser));
    }

    @Test
    public void newUserTest() {
        Assert.assertTrue(userService.findUserByLoginData("JUNIT_TEST_USER", "JUNIT_TEST_USER").equals(User.Empty()));
        userService.save("JUNIT_TEST_USER", "JUNIT_TEST_USER", "JUNIT_TEST_USER");
        Assert.assertTrue(userService.findUserByLoginData("JUNIT_TEST_USER","JUNIT_TEST_USER").equals(testUser));
    }

    @After
    public void cleanUpUsers() {
        userService.remove(testUser);
    }
}
