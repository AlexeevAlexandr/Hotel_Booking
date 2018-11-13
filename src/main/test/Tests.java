import commands.Commands;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class Tests {
    private Commands command = new Commands();

    @Test
    public void selectAllCategory(){
        String expected = "[Number = 1\n" +
                "Category = budget\n" +
                "Price = 100, Number = 2\n" +
                "Category = premium\n" +
                "Price = 500, Number = 3\n" +
                "Category = budget\n" +
                "Price = 120, Number = 4\n" +
                "Category = average\n" +
                "Price = 300, Number = 5\n" +
                "Category = premium\n" +
                "Price = 450, Number = 6\n" +
                "Category = average\n" +
                "Price = 350]";
        List list = command.selectAllCategory();
        assertEquals(expected,list.toString());
    }
}
