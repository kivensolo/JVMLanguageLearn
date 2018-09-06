package com.think.in.strings.unconscious_recursion;
// Accidental recursion.
// {RunByHand}
import java.util.*;

/**
 * Unconscious recursion of toString
 *
 */
public class InfiniteRecursion {
  public String toString() {
    //Wrong way to print out the address
    //return " InfiniteRecursion address: " + this + "\n";
    //Auto cast  'this' to String type.  So invoke this.toString() that happend recursion.  Make  StackOverflowError.

    //Right way to print out the address
    return " InfiniteRecursion address: " + super.toString() + "\n";

  }
  public static void main(String[] args) {
    List<InfiniteRecursion> v = new ArrayList<InfiniteRecursion>();
    for(int i = 0; i < 2; i++){
      v.add(new InfiniteRecursion());
    }
    System.out.println(v);
  }
} ///:~
