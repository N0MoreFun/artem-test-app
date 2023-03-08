package main;

import main.model.Message;
import main.model.MessageRepository;
import main.model.User;
import main.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@RestController
public class ChatController {
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/init")
    public boolean init() {
        String sessionID = RequestContextHolder.currentRequestAttributes().getSessionId();
        return userRepository.existsBySessionID(sessionID);
    }

    @GetMapping("/updateUserList")
    public ArrayList<String> updateUserList (){
        ArrayList<String>userNames=new ArrayList<>();

        userRepository.findAll().forEach(user -> {userNames.add(user.getName());});

        return userNames;
    }
    @GetMapping("/updateMessageList")
    public ArrayList<String> updateMessageList (){
        ArrayList<String>messages=new ArrayList<>();

        messageRepository.findAll().forEach(message -> {messages.add(message.toString());});

        return messages;
    }
    @GetMapping(value = "/login")
    public boolean login(@RequestParam String name, @RequestParam String pass) {
        String sessionID = RequestContextHolder.currentRequestAttributes().getSessionId();
        User user = userRepository.findByName(name);
        if (user!=null)
        if (user.getPass().equals(pass)) {
            user.setSessionID(sessionID);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @GetMapping(value = "/deleteHistory")
    public void deleteAll(){
        messageRepository.deleteAll();
    }
    @GetMapping(value = "/deleteUsers")
    public void deleteUsers(){
        deleteAll();
        userRepository.deleteAll();
    }
    @PostMapping(value = "/reg")
    public boolean registration(@RequestParam String name, @RequestParam String pass) {
        String sessionID = RequestContextHolder.currentRequestAttributes().getSessionId();
        userRepository.save(new User(name, pass, sessionID));
        return true;
        //TODO: проверка на повтор имени и возврат нужной булеан пока тру))
    }


    @PostMapping(value = "/send")
    public Long send(@RequestParam String textMessage) {

        String sessionID = RequestContextHolder.currentRequestAttributes().getSessionId();
        long id = messageRepository.count();

        User user = userRepository.findBySessionID(sessionID);
        Message ms = new Message(textMessage, LocalDateTime.now(), user);

        messageRepository.save(ms);


        return id;
    }
}
