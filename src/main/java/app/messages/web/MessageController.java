package app.messages.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import app.messages.model.Message;
import app.messages.service.MessageService;

@Controller
public class MessageController {

  private MessageService messageService;

  public MessageController(MessageService messageService) {
    this.messageService = messageService;
  }

  //  ui 렌더링, view 이름 반환
  @GetMapping("/messages")
  public String index(){
    return "index";
  }

  @GetMapping("/messages/welcome")
  public String welcome(Model model) {
    model.addAttribute("message", "Hello, Welcome to Spring Boot!");
    return "welcome";
  }
  
  @GetMapping("/api/messages")
  @ResponseBody
  public ResponseEntity<List<Message>> getMessages() {
    List<Message> messages = messageService.getMessages();
    return ResponseEntity.ok(messages);
  }
  
  @PostMapping("/api/messages")
  @ResponseBody
  public ResponseEntity<Message> saveMessage(@RequestBody MessageData data) {
    Message saved = messageService.save(data.getText());
    if (saved == null) {
      return ResponseEntity.status(500).build();
    }
    return ResponseEntity.ok(saved);
  }
  @DeleteMapping("/api/messages")
  @ResponseBody
  public ResponseEntity<Message> deleteMessage(@RequestBody MessageData data) {
    Message deleted = messageService.delete(data);
    if (deleted == null) {
      return ResponseEntity.status(500).build();
    }
    return ResponseEntity.ok(deleted);
  }
}
