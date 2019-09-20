package com.techprimers.springbatchexample1.controller;

import com.techprimers.springbatchexample1.model.User;
import com.techprimers.springbatchexample1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/load")
public class OldLoadController {

    @Autowired
    UserRepository repository;

    @GetMapping
    public String load() throws FileNotFoundException {
        Long start = System.currentTimeMillis();
        File inputF = new File("/home/rogerio/code/projects/spring-batch-example-1/src/main/resources/lean-users.csv");
        InputStream inputFS = new FileInputStream(inputF);
        BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
        br.lines().skip(1).map(mapUser).forEach(user -> repository.save(user));
        Long finish = (System.currentTimeMillis() - start);
        return "FINISHED IN: " + finish.toString();
    }


    private Function<String, User> mapUser = (line) -> {
        String[] p = line.split(",");
        User item = new User();
        item.setId(Integer.parseInt(p[0]));
        item.setName(p[1]);
        item.setDept(p[1]);
        item.setSalary(Integer.parseInt(p[3]));
        item.setTime(new Date());
        //more initialization goes here
        return item;
    };
}
