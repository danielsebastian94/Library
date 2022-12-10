package com.usa.mintic.reto3.service;


import com.usa.mintic.reto3.model.Category;
import com.usa.mintic.reto3.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll(){
        return categoryRepository.getAll();
    }

    public Optional<Category> getCategory(int id){
        return categoryRepository.getCategory(id);
    }

    public Category save(Category c){
        if(c.getId()==null){
            return categoryRepository.save(c);
        }else{
            Optional<Category> e = categoryRepository.getCategory(c.getId());
            if(e.isPresent()){
                return c;
            }else {
                return categoryRepository.save(c);
            }
        }

    }

    public Category update(Category c){
        if (c.getId()!=null){
            Optional<Category> m = categoryRepository.getCategory(c.getId());
            if(m.isPresent()){
                for (Field f : c.getClass().getDeclaredFields()) {
                    f.setAccessible(true);
                    Object value;
                    try {
                        value = f.get(c);
                        if (value != null) {
                            System.out.println("entro");
                            f.set(m.get(), value);
                        }
                    } catch (IllegalArgumentException ex) {
                        Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                categoryRepository.save(m.get());
                return m.get();
            }else{
                return c;
            }
        }else{
            return c;
        }
    }

    public boolean delete(int id){
        Optional<Category> c = categoryRepository.getCategory(id);
        boolean flag = false;
        if (c.isPresent()){
            categoryRepository.delete(c.get());
            flag = true;
        }
        return flag;
    }


}
