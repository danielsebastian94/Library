package com.usa.mintic.reto3.repository.crudRepository;


import com.usa.mintic.reto3.model.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageCrudRepository extends CrudRepository<Message,Integer> {
}
