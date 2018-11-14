import commands.Commands;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Tests {
    private Commands command = new Commands();

    @Test
    public void Aclear(){
        command.clearTable("listrooms");
        List list = command.selectAllCategory();
        assertEquals("[]", list.toString());
    }

    @Test
    public void BselectAllCategory(){
        command.clearTable("listrooms");
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
    public void CselectPremiumCategory(){
        String expected = "[Number = 3\n" +
                "Category = premium\n" +
                "Price = 500, Number = 6\n" +
                "Category = premium\n" +
                "Price = 500]";
        List list = command.selectPremiumCategory();
        assertEquals(expected,list.toString());
    }

    @Test
    public void DselectAverageCategory(){
        String expected = "[Number = 2\n" +
                "Category = average\n" +
                "Price = 250, Number = 5\n" +
                "Category = average\n" +
                "Price = 250]";
        List list = command.selectAverageCategory();
        assertEquals(expected,list.toString());
    }

    @Test
    public void EselectBudgetCategory(){
        String expected = "[Number = 1\n" +
                "Category = budget\n" +
                "Price = 100, Number = 4\n" +
                "Category = budget\n" +
                "Price = 100]";
        List list = command.selectBudgetCategory();
        assertEquals(expected,list.toString());
    }

    @Test
    public void FaddAndSelectOrder(){
        command.clearTable("orders");
        String expected = "[Name = Name\n" +
                "Number = 1\n" +
                "Date from = 2018-12-12\n" +
                "Date till = 2018-12-13\n" +
                "Cost = 100\n" +
                "Clean = no\n" +
                "Breakfast = no\n" +
                "Registration date = 2018-12-10]";
        command.add(1,"2018-12-12", "2018-12-13", "Name", 100, "no", "no", "2018-12-10");
        List list = command.selectOrder("Name");
        assertEquals(expected,list.toString());
    }

    @Test
    public void GselectAllOrders() {
        String expected = "[Name = Name\n" +
                "Number = 1\n" +
                "Date from = 2018-12-12\n" +
                "Date till = 2018-12-13\n" +
                "Cost = 100\n" +
                "Clean = no\n" +
                "Breakfast = no\n" +
                "Registration date = 2018-12-10]";
        List list = command.selectAllOrders();
        assertEquals(expected, list.toString());
    }

    @Test
    public void HselectByName(){
        String expected = "[Name = Name\n" +
                "Number = 1\n" +
                "Date from = 2018-12-12\n" +
                "Date till = 2018-12-13\n" +
                "Cost = 100\n" +
                "Clean = no\n" +
                "Breakfast = no\n" +
                "Registration date = 2018-12-10]";
        List list = command.selectByName("Name");
        assertEquals(expected, list.toString());
    }

    @Test
    public void JheckRoomNumber() {
        List list = command.checkRoomNumber();
        assertTrue(list.stream().anyMatch(q -> q.equals(1)));
        assertFalse(list.stream().anyMatch(q -> q.equals(11)));
    }

    @Test
    public void selectPrice(){
        assertEquals(100, command.selectPrice(1));
    }

    @Test (expected = NumberFormatException.class)
    public void exception(){
        command.selectPrice(-5);
    }

}
