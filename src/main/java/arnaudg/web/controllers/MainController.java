package arnaudg.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

  @RequestMapping("/")
  @ResponseBody
  //@CrossOrigin(origins = "*")
  public String index() {
    return "Games API created by Arnaud Guilhaumon";
  }
}