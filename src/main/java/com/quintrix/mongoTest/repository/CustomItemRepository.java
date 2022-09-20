package com.quintrix.mongoTest.repository;

public interface CustomItemRepository {
  void updateItemQuantity(String name, float newQuantity);
}
