package com.usa.mintic.reto3.service;


import com.usa.mintic.reto3.model.Message;
import com.usa.mintic.reto3.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAll(){
        return messageRepository.getAll();
    }

    public Optional<Message> getMessage(int id){
        return messageRepository.getMessage(id);
    }

    public Message save(Message m){
        if(m.getIdMessage()==null){
            return messageRepository.save(m);
        }else{
            Optional<Message> e = messageRepository.getMessage(m.getIdMessage());
            if(e.isPresent()){
                return m;
            }else {
                return messageRepository.save(m);
            }
        }

    }

    public Message update(Message m){
        if (m.getIdMessage()!=null){
            Optional<Message> e = messageRepository.getMessage(m.getIdMessage());
            if(e.isPresent()){
                if (m.getMessageText()!= null){
                    e.get().setMessageText(m.getMessageText());
                }
                messageRepository.save(e.get());
                return e.get();
            }else{
                return m;
            }
        }else{
            return m;
        }
    }

    public boolean delete(int id){
        Optional<Message> m = messageRepository.getMessage(id);
        boolean flag = false;
        if (m.isPresent()){
            messageRepository.delete(m.get());
            flag = true;
        }
        return flag;
    }
}
