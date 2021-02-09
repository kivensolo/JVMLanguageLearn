package com.xml.test.jaxb.base.dimain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class TestItem {
    public TestItem() {
    }

    public TestItem(String price, String favoriteGame) {
        this.price = price;
        this.favoriteGame = favoriteGame;
    }

    @XmlAttribute()
    String price;

    @XmlElement(name = "game")
    String favoriteGame;

    @Override
    public String toString() {
        return "TestItem{" +
                "price='" + price + '\'' +
                ", favoriteGame='" + favoriteGame + '\'' +
                '}';
    }
}
