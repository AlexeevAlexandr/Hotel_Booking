package application;

import commands.Commands;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {
    private Commands commands = new Commands();
}
