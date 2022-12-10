package com.usa.mintic.reto3.service;


import com.usa.mintic.reto3.model.Admin;
import com.usa.mintic.reto3.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;
    public List<Admin> getAll(){
        return adminRepository.getAll();
    }

    public Optional<Admin> getAdmin(int id){
        return adminRepository.getAdmin(id);
    }

    public Admin save(Admin a){
        if(a.getId()==null){
            return adminRepository.save(a);
        }else{
            Optional<Admin> e = adminRepository.getAdmin(a.getId());
            if(e.isPresent()){
                return a;
            }else {
                return adminRepository.save(a);
            }
        }

    }

    public Admin update(Admin ad){
        if (ad.getId()!=null){
            Optional<Admin> z = adminRepository.getAdmin(ad.getId());
            if(z.isPresent()){
               if(ad.getName()!=null){
                    z.get().setName(ad.getName());
               }
                if(ad.getEmail()!=null){
                    z.get().setEmail(ad.getEmail());
                }
                if (ad.getPassword()!=null){
                    z.get().setPassword(ad.getPassword());
                }
                adminRepository.save(z.get());
                return z.get();
            }else{
                return ad;
            }
        }else{
            return ad;
        }
    }

    public boolean delete(int id){
        Optional<Admin> a = adminRepository.getAdmin(id);
        boolean flag = false;
        if (a.isPresent()){
            adminRepository.delete(a.get());
            flag = true;
        }
        return flag;
    }
}
