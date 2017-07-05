package com.hello;
public class Clone {
    public static void main(String[] args) {
        InHe t1 = new InHe();
        InHe t2 = t1;

        System.out.println("t1  = @" + t1.hashCode() +"  ;t2 = @"+t2.hashCode());
        System.out.println("t2.fuu  = " + t2.fuu);
        System.out.println("给t2.fuu赋值333");
        t2.fuu = 333;
        System.out.println("改变t2后 t1.fuu  = " + t1.fuu);

        InHe t3 = t1.clone();
        System.out.println("\n 克隆t1 ————> t3 = @" + t3.hashCode());
        System.out.println("给t3.fuu赋值344");
        t3.fuu = 344;
        System.out.println("改变t3后 t1.fuu  = " + t1.fuu);

    }

    static class InHe implements  Cloneable{
        Info mInfo;
        int fuu = 4;

        public InHe() {
            this.mInfo = new Info();
        }

        public InHe clone(){
             InHe o = null;
            try{
                o = (InHe)super.clone();
            }catch(CloneNotSupportedException e){
                e.printStackTrace();
            }
            return o;
        }

        class Info{
            String name = "111";
        }
    }


}
