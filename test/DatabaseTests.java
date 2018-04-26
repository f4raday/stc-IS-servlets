import model.services.DataBase;
import org.junit.Assert;
import org.junit.Test;

public class DatabaseTests  {

    @Test
    public void ConnectionTest() {
        Assert.assertNotNull(DataBase.getConnection());
    }
}
