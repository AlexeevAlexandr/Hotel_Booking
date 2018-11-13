import commands.Commands;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Tests {
    private Commands command = new Commands();

    @Test
    public void clear(){
        command.clearListRooms();
        List list = command.selectAllCategory();
        assertEquals("[]", list.toString());
    }

    @Test
    public void selectAllCategory(){
        command.clearListRooms();
        String [] category = {"budget","average", "premium"};
        int [] price = {100, 250, 500};
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < category.length; j++) {
                command.addToListRooms(category[j],price[j]);
            }
        }
        String expected = "[Number = 1\n" +
                "Category = budget\n" +
                "Price = 100, Number = 2\n" +
                "Category = average\n" +
                "Price = 250, Number = 3\n" +
                "Category = premium\n" +
                "Price = 500, Number = 4\n" +
                "Category = budget\n" +
                "Price = 100, Number = 5\n" +
                "Category = average\n" +
                "Price = 250, Number = 6\n" +
                "Category = premium\n" +
                "Price = 500]";
        List list = command.selectAllCategory();
        assertEquals(expected,list.toString());
    }

    @Test
    public void selectPremiumCategory(){
        String expected = "[Number = 3\n" +
                "Category = premium\n" +
                "Price = 500, Number = 6\n" +
                "Category = premium\n" +
                "Price = 500]";
        List list = command.selectPremiumCategory();
        assertEquals(expected,list.toString());
    }

    @Test
    public void selectAverageCategory(){
        String expected = "[Number = 2\n" +
                "Category = average\n" +
                "Price = 250, Number = 5\n" +
                "Category = average\n" +
                "Price = 250]";
        List list = command.selectAverageCategory();
        assertEquals(expected,list.toString());
    }

    @Test
    public void selectBudgetCategory(){
        String expected = "[Number = 1\n" +
                "Category = budget\n" +
                "Price = 100, Number = 4\n" +
                "Category = budget\n" +
                "Price = 100]";
        List list = command.selectBudgetCategory();
        assertEquals(expected,list.toString());
    }

    @Test
    public void addAndSelectOrder(){
        String expected = "[Name = Name\n" +
                "Number = 1\n" +
                "Date from = 2018-12-12\n" +
                "Date till = 2018-12-13\n" +
                "Cost = 100\n" +
                "Clean = no\n" +
                "Breakfast = no\n" +
                "Registration date = 2018-12-10 00:00:00]";
        command.clearOrders();
        command.add(1,"2018-12-12", "2018-12-13", "Name", 100, "no", "no", "2018-12-10");
        List list = command.selectOrder("Name");
        assertEquals(expected,list.toString());
    }

    @Test
    public void selectAllOrders() {
        String expected = "[Name = Name\n" +
                "Number = 1\n" +
                "Date from = 2018-12-12\n" +
                "Date till = 2018-12-13\n" +
                "Cost = 100\n" +
                "Clean = no\n" +
                "Breakfast = no\n" +
                "Registration date = 2018-12-10 00:00:00]";
        List list = command.selectAllOrders();
        assertEquals(expected, list.toString());
    }

    @Test
    public void checkRoomNumber() {
        List list = command.checkRoomNumber();
        assertTrue(list.stream().anyMatch(q -> q.equals(1)));
        assertFalse(list.stream().anyMatch(q -> q.equals(11)));
    }
}
