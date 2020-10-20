package com.example.springbootandmongodb.service;

import com.example.springbootandmongodb.bean.Citem;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import javafx.scene.shape.Circle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @author 郭文文
 * @version 1.0
 * @date 2020/10/20 9:35
 */
@Service
public class CitemService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Citem> findAll(){
        List<Citem> all = mongoTemplate.findAll(Citem.class);
        return all;
    }

    public Citem findName(String name){
        Query name1 = new Query(Criteria.where("name").is(name));
        Citem one = mongoTemplate.findOne(name1, Citem.class);
        return one;
    }

    public Citem save(Citem citem){
        Citem save = mongoTemplate.save(citem);
        return save;
    }

    public UpdateResult update(Query query,Update update){
        return mongoTemplate.updateMulti(query,update,Citem.class);
    }

    public DeleteResult deleteById(String id){
        Query query = new Query(Criteria.where("_id").is(id));
        return mongoTemplate.remove(query,Citem.class);
    }

    public DeleteResult delete(Citem citem){
        return mongoTemplate.remove(citem);
    }

    public List<Citem> findByLike(String search){
        Pattern compile = Pattern.compile("^.*" + search + ".*$" , Pattern.CASE_INSENSITIVE);
        Query name = new Query(Criteria.where("name").regex(compile));
        List<Citem> allAndRemove = mongoTemplate.findAllAndRemove(name, Citem.class);
        return allAndRemove;
    }

}
