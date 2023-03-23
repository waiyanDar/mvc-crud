package com.example.mvccrud.ds;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedList;
import java.util.Objects;

@Getter
@Setter
@ToString
/*
* id,title,price,quantity*/
public class CartItem{
        private int id;
        private String title;
        private double price;
       private int quantity;
       private boolean render;
       private LinkedList<Integer> quantityLinkedList = new LinkedList<>();

    public CartItem() {
    }

    public CartItem(int id, String title, double price, int quantity) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return id == cartItem.id && Objects.equals(title, cartItem.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
