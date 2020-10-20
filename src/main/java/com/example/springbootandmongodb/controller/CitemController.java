package com.example.springbootandmongodb.controller;

import com.example.springbootandmongodb.bean.Citem;
import com.example.springbootandmongodb.service.CitemService;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import javafx.scene.shape.Circle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 郭文文
 * @version 1.0
 * @date 2020/10/20 9:58
 */
@RestController
@RequestMapping("citem")
public class CitemController {
    @Autowired
    private CitemService citemService;

    @GetMapping("/all")
    public List<Citem> findAll(){
        return citemService.findAll();
    }

    @GetMapping("/name")
    public Citem findOne(String name){
        return citemService.findName(name);
    }

    @PostMapping("/save")
    public Citem save(Citem citem){
        return citemService.save(citem);
    }

    @PostMapping("/update")
    public UpdateResult update(Citem citem){
        Query query = new Query(Criteria.where("name").is(citem.getName()));
        Update update = new Update().set("num", citem.getNum());
        return citemService.update(query, update);
    }

    @GetMapping("/deleteById")
    public DeleteResult deleteById(String id){
        return citemService.deleteById(id);
    }

    @PostMapping("/delete")
    public DeleteResult delete(Citem citem){
        return citemService.delete(citem);
    }

    @GetMapping("/search")
    public List<Citem> findByLike(String search){
        return citemService.findByLike(search);
    }

    @GetMapping("/a")
    public String a(){
        return "abc";
    }
}
