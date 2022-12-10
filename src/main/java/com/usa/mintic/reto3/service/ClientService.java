package com.usa.mintic.reto3.service;


import com.usa.mintic.reto3.model.Client;
import com.usa.mintic.reto3.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll(){
        return clientRepository.getAll();
    }

    public Optional<Client> getClient(int id){
        return clientRepository.getClient(id);
    }

    public Client save(Client c){
        if(c.getIdClient()==null){
            return clientRepository.save(c);
        }else{
            Optional<Client> e = clientRepository.getClient(c.getIdClient());
            if(e.isPresent()){
                return c;
            }else {
                return clientRepository.save(c);
            }
        }

    }

    public Client update(Client c){
        if (c.getIdClient()!=null){
            Optional<Client> m = clientRepository.getClient(c.getIdClient());
            if(m.isPresent()){
                if(c.getName()!= null){
                    m.get().setName(c.getName());
                }
                if(c.getEmail()!=null){
                    m.get().setEmail(c.getEmail());
                }
                if (c.getPassword()!=null){
                    m.get().setPassword(c.getPassword());
                }
                if (c.getAge()!=null) {
                    m.get().setAge(c.getAge());
                }
                clientRepository.save(m.get());
                return m.get();
            }else{
                return c;
            }
        }else{
            return c;
        }
    }

    public boolean delete(int id){
        Optional<Client> m = clientRepository.getClient(id);
        boolean flag = false;
        if (m.isPresent()){
            clientRepository.delete(m.get());
            flag = true;
        }
        return flag;
    }
}
