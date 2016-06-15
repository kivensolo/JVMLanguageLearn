package com.think.in.reusing;//: reusing/DetergentProx.java
// Inheritance syntax & properties.

class Cleanser {
    private String s = "Cleanser";

    public void append(String a) {
        s += a;
    }

    public void dilute() {
        append(" dilute()");
    }

    public void apply() {
        append(" apply()");
    }

    public void scrub() {
        append(" scrub()");
    }

    public String toString() {
        return s;
    }

    public static void main(String[] args) {
        Cleanser x = new Cleanser();
        x.dilute();
        x.apply();
        x.scrub();
        System.out.println(x);
    }
}

class Detergent extends Cleanser {
    // Change a method:
    public void scrub() {
        append(" Detergent.scrub()");
        super.scrub(); // Call base-class version
    }

    // Add methods to the interface:
    public void foam() {
        append(" foam()");
    }

    // Test the new class:
    public static void main(String[] args) {
        Detergent x = new Detergent();
        x.dilute();
        x.apply();
        x.scrub();
        x.foam();
        System.out.println(x);
        System.out.println("Testing base class:");
        Cleanser.main(args);
    }
}
/* Output:
Cleanser dilute() apply() Detergent.scrub() scrub() foam()
Testing base class:
Cleanser dilute() apply() scrub()
*/


/*************************
 * 复用性之 —— 代理
 *
 **********************/
public class DetergentProx{

    private Cleanser clearner = new Cleanser();

    public DetergentProx() {
    }

    public DetergentProx(Cleanser clearner) {
        this.clearner = clearner;
    }

    public void append(String a) {
        clearner.append("");
    }

    public void dilute() {
        clearner.dilute();
    }

    public void apply() {
        clearner.apply();
    }

    public void scrub() {
        append(" Detergent.scrub()");
        clearner.scrub();
    }

    public String toString() {
        return clearner.toString();
    }
    public void foam() {
        append("foam()");
    }


    // Test the new class:
    public static void main(String[] args) {
        DetergentProx x = new DetergentProx();
        x.dilute();
        x.apply();
        x.scrub();
        x.foam();
        System.out.println(x);
        System.out.println("Testing base class:");
        Cleanser.main(args);
    }
}
