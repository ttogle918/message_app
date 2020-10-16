package app.messages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/messages")
public class MessageController {
  // Http get 요청 매핑
  @GetMapping("/welcome")   // = (value = "/welcome", method = RequestMethod.GET)
  @ResponseBody // return값이 무엇을 할지 알려줌
  public String welcome(Model model) {
    return "Hello, Welcome to Spring Boot!";
  }

  // @GetMapping("/welcome")
  // public ModelAndView welcome() {
  //   ModelAndView mv = new ModelAndView("welcome");
  //   mv.addObject("message", "Hello, Welcome to Spring Boot!");
  //   return mv;
  // }
}