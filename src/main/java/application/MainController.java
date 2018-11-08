package application;

import commands.Commands;
import dataBaseConnect.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
    private Commands commands = new Commands();

    @RequestMapping(value = {"/", "startPage"})
    public String startPage(){
        return "startPage";
    }

    @RequestMapping(value = {"listRooms"}, method = RequestMethod.GET)
    public String listRooms(Model model){
        model.addAttribute("rooms",commands.selectAllCategory());
        return "listRooms";
    }

    @RequestMapping(value = {"listRooms"}, method = RequestMethod.POST)
    public String getTotalCost(Model model, @ModelAttribute("rooms") Order order){
        model.addAttribute("rooms",commands.selectAllCategory());
        return "listRooms";
    }

    @RequestMapping(value = {"/getTotalCost"}, method = RequestMethod.GET)
    public String createOrder(Model model){
        model.addAttribute("rooms", commands.selectOrder(2));
        return "getTotalCost";
    }

    @RequestMapping(value = {"premiumCategory"}, method = RequestMethod.GET)
    public String premiumCategory(Model model){
        model.addAttribute("rooms",commands.selectPremiumCategory());
        return "listRooms";
    }

    @RequestMapping(value = {"averageCategory"}, method = RequestMethod.GET)
    public String averageCategory(Model model){
        model.addAttribute("rooms",commands.selectAverageCategory());
        return "listRooms";
    }

    @RequestMapping(value = {"budgetCategory"}, method = RequestMethod.GET)
    public String budgetCategory(Model model){
        model.addAttribute("rooms",commands.selectBudgetCategory());
        return "listRooms";
    }
}
