package app.messages;

import java.util.logging.Logger;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/messages")
public class MessageController {
  private final static Logger log = Logger.getGlobal();

  private MessageService messageService;

  public MessageController(MessageService messageService) {
    this.messageService = messageService;
  }

  @GetMapping("/welcome")
  public String welcome(Model model) {
    model.addAttribute("message", "Hello, Welcome to Spring Boot!");
    return "welcome";
  }
  
  @PostMapping("")
  @ResponseBody
  public ResponseEntity<Message> saveMessage(@RequestBody MessageData data) {
    
    log.info("saveMsg start");
    Message saved = messageService.save(data.getText());
    log.info("msg saved");
    if (saved == null) {
      log.info("msg saved null");

      return ResponseEntity.status(500).build();
    }
    return ResponseEntity.ok(saved);
  }
}
