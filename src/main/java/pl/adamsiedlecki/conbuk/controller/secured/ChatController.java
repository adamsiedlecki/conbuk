package pl.adamsiedlecki.conbuk.controller.secured;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.HtmlUtils;
import pl.adamsiedlecki.conbuk.pojo.Message;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
public class ChatController {

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @MessageMapping("/chat")
    @SendTo("/topic/chat")
    public Message greeting(Message message, Principal principal) throws Exception {
        Message m = new Message();
        m.setContent(HtmlUtils.htmlEscape(message.getContent()));
        m.setUser(principal.getName());
        m.setTime(LocalDateTime.now());
        return m;
    }

    @GetMapping("/chat")
    public String getChat() {
        return "chat";
    }
}
