/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
https://stackoverflow.com/questions/10323957/posting-json-to-rest-api
 */

package com.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.example.Ledger;
import com.example.MiningService;
import com.google.gson.Gson;

@Controller
@SpringBootApplication
public class Main implements ChainInterface{

  @Value("${spring.datasource.url}")
  private String dbUrl;

  @Autowired
  private DataSource dataSource;

  public static void main(String[] args) throws Exception {
    SpringApplication.run(Main.class, args);
    BlockChain bcObject = BlockChain.getInstance();
    bcObject.new_transaction("sender", "receiver", "5");
    bcObject.new_block(1,"0");
  }

  @RequestMapping("/")
  String index() {
    return "index";
  }

  @RequestMapping("/home")
  String home() {
    return "home";
  }

  @RequestMapping(value="/transact", method = RequestMethod.POST)
  String transact() {
    try {
    return "home";
    }
    catch(Exception e) {
        return "except";
    }
  }
  
  @RequestMapping(value="/register", method = RequestMethod.POST)
  String register() {
    try {
    return "register";
    }
    catch(Exception e) {
        return "except";
    }
  }  
  
  @RequestMapping(value="/chain", method = RequestMethod.GET)
  @ResponseBody 
  public ArrayList<ArrayList<String>> getChain() {
	  BlockChain bcObject = BlockChain.getInstance();
    try {
    return bcObject.getChain();
    }
    catch(Exception e) {
        return bcObject.getChain();
    }
  }  
  
  @RequestMapping(value="/testresponse", method = RequestMethod.GET)
  @ResponseBody 
  public List<String> testArray() {
	  BlockChain bcObject = BlockChain.getInstance();
    try {
    return bcObject.getTransactions();
    }
    catch(Exception e) {
        return bcObject.getTransactions();
    }
  }   
  
  @RequestMapping(value="/testlist", method = RequestMethod.GET)
  public @ResponseBody 
   List<String> testList() {
	  List<String> returnlist = new ArrayList<String>();
	  returnlist.add("hopefully works");
	  returnlist.add("really hopefully works");
    try {
    return returnlist;
    }
    catch(Exception e) {
        return returnlist;
    }
  }     
  
  @RequestMapping(value="/newtestlist", method = RequestMethod.GET)
  public @ResponseBody 
   List<String> newtestList() {
	  BlockChain bcObject = BlockChain.getInstance();
	  List<String> returnlist = bcObject.getTransactions();
    try {
    return returnlist;
    }
    catch(Exception e) {
        return returnlist;
    }
  } 
  
  @RequestMapping("/db")
  String db(Map<String, Object> model) {
    try (Connection connection = dataSource.getConnection()) {
      Statement stmt = connection.createStatement();
      stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
      stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
      ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

      ArrayList<String> output = new ArrayList<String>();
      while (rs.next()) {
        output.add("Read from DB: " + rs.getTimestamp("tick"));
      }

      model.put("records", output);
      return "db";
    } catch (Exception e) {
      model.put("message", e.getMessage());
      return "error";
    }
  }

  @Bean
  public DataSource dataSource() throws SQLException {
    if (dbUrl == null || dbUrl.isEmpty()) {
      return new HikariDataSource();
    } else {
      HikariConfig config = new HikariConfig();
      config.setJdbcUrl(dbUrl);
      return new HikariDataSource(config);
    }
  }
  
  public ArrayList<ArrayList<String>> returnChain(BlockChain bc){
	  return bc.getChain();
  }

}
