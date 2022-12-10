package com.usa.mintic.reto3.service;


import com.usa.mintic.reto3.model.Machine;
import com.usa.mintic.reto3.repository.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MachineService {

    @Autowired
    private MachineRepository machineRepository;

    public List<Machine> getAll(){
        return machineRepository.getAll();
    }

    public Optional<Machine> getMachine(int id){
        return machineRepository.getMachine(id);
    }

    public Machine save(Machine p){
        if(p.getId()==null){
            return machineRepository.save(p);
        }else{
            Optional<Machine> e =machineRepository.getMachine(p.getId());
            if(e.isPresent()){
                return p;
            }else {
                return machineRepository.save(p);
            }
        }

    }

    public Machine update(Machine p){
        if (p.getId()!=null){
            Optional<Machine> m = machineRepository.getMachine(p.getId());
            if(m.isPresent()){
                if (p.getBrand()!= null){
                    m.get().setBrand(p.getBrand());
                }
                if(p.getCategory()!=null){
                    m.get().setCategory(p.getCategory());
                }
                if (p.getDescription()!=null){
                    m.get().setDescription(p.getDescription());
                }
                if (p.getName()!=null) {
                    m.get().setName(p.getName());
                }
                machineRepository.save(m.get());
                return m.get();
            }else{
                return p;
            }
        }else{
            return p;
        }
    }

    public boolean delete(int id){
        Optional<Machine> m = machineRepository.getMachine(id);
        boolean flag = false;
        if (m.isPresent()){
            machineRepository.delete(m.get());
            flag = true;
        }
        return flag;
    }
}
