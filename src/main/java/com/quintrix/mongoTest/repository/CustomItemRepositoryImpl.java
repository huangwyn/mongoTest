package com.quintrix.mongoTest.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class CustomItemRepositoryImpl implements CustomItemRepository{

  @Autowired
  MongoTemplate mongoTemplate;

  @Override
  public void updateItemQuantity(String name, float newQuantity) {


  }
}
