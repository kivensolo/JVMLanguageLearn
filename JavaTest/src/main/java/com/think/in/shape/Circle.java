//: polymorphism/shape/Circle.java
package com.think.in.shape;

public class Circle extends Shape {
  @Override
  public void draw() {
    System.out.println("Circle.draw()");
  }
  @Override
  public void erase() {
    System.out.println("Circle.erase()");
  }

  @Override
  public void printcc() {
    super.printcc();
  }
}
