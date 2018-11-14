package application;

import commands.Commands;
import commands.CountTotalCost;
import dataBaseConnect.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Controller
public class MainController {
    private Commands commands = new Commands();
    private String name;

    @Value("Incorrect date format")
    private String errorMessageIncorrectDateFormat;

    @Value("Incorrect date insertion, the number of days can not be negative")
    private String errorMessageNegativeDate;

    @Value("The name is to long, must be no more than 50 characters")
    private String errorMessageNameToLong;

    @Value("Enter name")
    private String errorMessageNameToShort;

    @Value("The name not found")
    private String nameNotFound;

    @Value("Rooms number not found")
    private String incorrectNumber;

    @RequestMapping(value = {"/", "/startPage"})
    public String startPage(){
        return "startPage";
    }

    @RequestMapping(value = {"/listRooms"}, method = RequestMethod.GET)
    public String listRooms(Model model){
        model.addAttribute("rooms",commands.selectAllCategory());
        return "listRooms";
    }

    @RequestMapping(value = {"/premiumCategory"}, method = RequestMethod.GET)
    public String premiumCategory(Model model){
        model.addAttribute("rooms",commands.selectPremiumCategory());
        return "listRooms";
    }

    @RequestMapping(value = {"/averageCategory"}, method = RequestMethod.GET)
    public String averageCategory(Model model){
        model.addAttribute("rooms",commands.selectAverageCategory());
        return "listRooms";
    }

    @RequestMapping(value = {"/budgetCategory"}, method = RequestMethod.GET)
    public String budgetCategory(Model model){
        model.addAttribute("rooms",commands.selectBudgetCategory());
        return "listRooms";
    }

    @RequestMapping(value = {"/allOrders"}, method = RequestMethod.GET)
    public String allOrders(Model model){
        model.addAttribute("orders",commands.selectAllOrders());
        return "allOrders";
    }

    @RequestMapping(value = {"/makeOrder"}, method = RequestMethod.GET)
    public String showCreatedOrder(Model model){
        Order order = new Order();
        model.addAttribute("order", order);
        return "makeOrder";
    }

    @RequestMapping(value = {"/makeOrder"}, method = RequestMethod.POST)
    public String createOrder(Model model, @ModelAttribute("order") Order order, HttpServletRequest request) {
        name = order.getName();
        String clean = (request.getParameter("cleaning") == null) ? "no" : "yes";
        String breakfast = (request.getParameter("breakfast") == null) ? "no" : "yes";
        if (name.length()>50){
            model.addAttribute("errorMessage", errorMessageNameToLong);
            return "makeOrder";
        }
        if (name.length()<1){
            model.addAttribute("errorMessage", errorMessageNameToShort);
            return "makeOrder";
        }
        int number = order.getNumber();
        if (number < 1 || commands.checkRoomNumber().stream().noneMatch(q -> q.equals(number))){
            System.out.println(commands.checkRoomNumber().stream().noneMatch(q -> q.equals(number)));
            model.addAttribute("errorMessage", incorrectNumber);
            return "makeOrder";
        }
        try {
            String dateFrom = order.getDateFrom();
            String dateTill =  order.getDateTill();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = simpleDateFormat.parse(dateFrom);
            Date date2 = simpleDateFormat.parse(dateTill);
            int diff = (int) TimeUnit.DAYS.convert((date2.getTime() - date1.getTime()),TimeUnit.MILLISECONDS);
            if (diff < 0){
                model.addAttribute("errorMessage", errorMessageNegativeDate);
                return "makeOrder";
            }
            int cost = ((new CountTotalCost().selectPrice(number)) +
                ((clean.equalsIgnoreCase("yes") ? 1 : 0)) +
                ((breakfast.equalsIgnoreCase("yes") ? 5 : 0))) * diff;

            commands.add(number, dateFrom, dateTill, name, cost, clean, breakfast, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            return "redirect:/order";
        }catch(Exception e){
            model.addAttribute("errorMessage", errorMessageIncorrectDateFormat);
            e.printStackTrace();
            return "makeOrder";
        }
    }

    @RequestMapping(value = {"/order"})
    public String order(Model model){
        model.addAttribute("order", commands.selectOrder(name));
        return "order";
    }

    @RequestMapping(value = {"/listOrdersByName"}, method = RequestMethod.GET)
    public String listOrdersByName(Model model, HttpServletRequest request){
        try {
            String name = request.getParameter("name");
            Model a = model.addAttribute("orders",commands.selectByName(name));
            if (a.toString().equals("{orders=[]}")){
                model.addAttribute("errorMessage", nameNotFound);
            }
        }catch (Exception e){
            e.getMessage();
            return "listOrdersByName";
        }
        return "listOrdersByName";
    }
}

