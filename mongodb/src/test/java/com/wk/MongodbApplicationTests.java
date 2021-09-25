package com.wk;

import com.jayway.jsonpath.Criteria;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.wk.Entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@SpringBootTest
class MongodbApplicationTests {
        @Autowired
        private MongoTemplate template;
    //添加
        @Test
    void contextLoads() {
        User user = new User();
        user.setAge(15);
        user.setName("w");
        user.setEmail("q@qq.com");
        User insert = template.insert(user);
        System.out.println(insert);
    }
    //查询所有
    @Test
    public void findAll(){
        List<User> all = template.findAll(User.class);
        System.out.println(all);
    }
    //根据ID查询
    @Test
    public void findById(){
        User byId = template.findById("614e7f14f5757322f1223c4c", User.class);
        System.out.println(byId);
    }
    //条件查询操作
    @Test
    public void findByLike(){
        Query query = new Query((CriteriaDefinition) Criteria
                .where("name").is("test")
                .and("age").is(20));

        List<User> users = template.find(query, User.class);
        System.out.println(users);

    }
    //模糊查询
    @Test
    public void findBymohu(){
        String name = "est";
        String regex = String.format("%s%s%s", "^.*", name, ".*$");
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Query query = new Query((CriteriaDefinition) Criteria.where("name").regex(pattern));
        List<User> userList = template.find(query, User.class);
        System.out.println(userList);
    }
        //分页查询
        @Test
        public void findUsersPage() {
            String name = "est";
            int pageNo = 1;
            int pageSize = 10;

            Query query = new Query();
            String regex = String.format("%s%s%s", "^.*", name, ".*$");
            Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            query.addCriteria((CriteriaDefinition) Criteria.where("name").regex(pattern));
            int totalCount = (int) template.count(query, User.class);
            List<User> userList = template.find(query.skip((pageNo - 1) * pageSize).limit(pageSize), User.class);

            Map<String, Object> pageMap = new HashMap<>();
            pageMap.put("list", userList);
            pageMap.put("totalCount",totalCount);
            System.out.println(pageMap);
        }
    //修改
    @Test
    public void updateUser() {
        User user = template.findById("614e7f14f5757322f1223c4c", User.class);
        user.setName("test_1");
        user.setAge(25);
        user.setEmail("493220990@qq.com");
        Query query = new Query((CriteriaDefinition) Criteria.where("_id").is(user.getId()));
        Update update = new Update();
        update.set("name", user.getName());
        update.set("age", user.getAge());
        update.set("email", user.getEmail());
        UpdateResult result = template.upsert(query, update, User.class);
        long count = result.getModifiedCount();
        System.out.println(count);
    }

    //删除操作
    @Test
    public void delete() {
        Query query =
                new Query((CriteriaDefinition) Criteria.where("_id").is("5ffbfa2ac290f356edf9b5aa"));
        DeleteResult result = template.remove(query, User.class);
        long count = result.getDeletedCount();
        System.out.println(count);
    }


}
