package application;

import commands.Commands;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        model.addAttribute("rooms",commands.select());
        return "listRooms";
    }
}
