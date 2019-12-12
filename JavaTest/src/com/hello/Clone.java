package com.hello;

import org.json.JSONObject;

import java.text.ParseException;

public class Clone {
    public static void main(String[] args) {
        //InHe t1 = new InHe();
        //InHe t2 = t1;
        //
        //System.out.println("t1  = @" + t1.hashCode() +"  ;t2 = @"+t2.hashCode());
        //System.out.println("t2.fuu  = " + t2.fuu);
        //System.out.println("给t2.fuu赋值333");
        //t2.fuu = 333;
        //System.out.println("改变t2后 t1.fuu  = " + t1.fuu);
        //
        //InHe t3 = t1.clone();
        //System.out.println("\n 克隆t1 ————> t3 = @" + t3.hashCode());
        //System.out.println("给t3.fuu赋值344");
        //t3.fuu = 344;
        //System.out.println("改变t3后 t1.fuu  = " + t1.fuu);
        String json = "{\"com.starcor.xinjiang.gov\": \"http://172.31.15.249/1.3.0.0.0.SC-XJCBC-GOV-STB-QZ.0.0_Beta.apk\",\"com.starcor.xinjiang.dispatcher\": {\"url\": \"http://172.31.15.249/AppDispatcher_0.0.SC-XJCBC-STB-RK.0.0_Beta_1.3.0_SC41383_VC54383.apk\",\"versionCode\": \"54383\"}}";
        try {
            JSONObject object = new JSONObject(json);
            JSONObject dispatcherData = object.optJSONObject("com.starcor.xinjiang.dispatcher");
            String s = dispatcherData.toString();
            System.out.println("sss ： " + s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
