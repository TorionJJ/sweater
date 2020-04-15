package com.example.sweater;


import com.example.sweater.domain.Message;
import com.example.sweater.repos.MessageRepo;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;

@Controller
public class GreetingController {
   @Autowired
   private MessageRepo MessageRepo;

    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name="name", required=false, defaultValue="World") String name,
            @NotNull Map<String, Object> model)
    {
        model.put("name", name);
        return "greeting";
    }
 @GetMapping
    public String main(Map<String, Object> model){
        Iterable<Message> messages = MessageRepo.findAll();

        model.put("messages", messages);

        return "main";
 }
 @PostMapping
    public String add(@RequestParam String text,@RequestParam String tag,
         Map<String, Object> model) {
    Message message = new Message(text, tag);

    MessageRepo.save(message);

     Iterable<Message> messages = MessageRepo.findAll();

     model.put("messages", messages);

        return "main";
 }
}
