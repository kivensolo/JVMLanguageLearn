package reference;

public class Student {
    @Override
    protected void finalize() {
        System.out.println("Student 被回收了");
    }
}
