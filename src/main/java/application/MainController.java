package application;

import commands.Commands;
import dataBaseConnect.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class MainController {
    private Commands commands = new Commands();

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

    @RequestMapping(value = {"/makeOrder"}, method = RequestMethod.GET)
    public String showCreatedOrder(Model model){
        Order order = new Order();
        model.addAttribute("order", order);
        return "makeOrder";
    }

    @RequestMapping(value = {"/makeOrder"}, method = RequestMethod.POST)
    public String createOrder(Model model, @ModelAttribute("order") Order order){
            int number = order.getNumber();
            String dateFrom = order.getDateFrom();
            String dateTill = order.getDateTill();
            String name = order.getName();
            int cost = order.getCost();
            String clean = order.getCleaning();
            String breakfast = order.getBreakfast();

        try {
            commands.add(number, dateFrom, dateTill, name, cost, clean, breakfast, getDateTime());
            return "redirect:/order";
        }catch(Exception e){
            e.printStackTrace();
        }
        return "makeOrder";
    }

    private String getDateTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    @RequestMapping(value = {"/order"})
    public String order(Model model, Order order){
        int number = order.getNumber();
        model.addAttribute("order", commands.selectOrder(number));
        return "order";
    }
}
