package com.usa.mintic.reto3.repository;


import com.usa.mintic.reto3.model.Admin;
import com.usa.mintic.reto3.repository.crudRepository.AdminCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AdminRepository {
     @Autowired
     private AdminCrudRepository adminCrudRepository;

     public List<Admin> getAll(){
         return (List<Admin>) adminCrudRepository.findAll();
     }

     public Optional<Admin> getAdmin(int idAdmin){
          return adminCrudRepository.findById(idAdmin);
     }

     public Admin save(Admin s){
          return adminCrudRepository.save(s);
     }

     public void delete(Admin s){
          adminCrudRepository.delete(s);
     }

}
