package com.kingz.format.xml.jaxb;

import com.kingz.format.xml.jaxb.dimain.TestItem;
import com.kingz.format.xml.jaxb.dimain.TestJaxbBean;
import com.kingz.format.xml.jaxb.manifest.AndroidManifest;

import javax.xml.bind.*;
import javax.xml.namespace.QName;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

public class JAXBDemo {

    public static void unMarshalAndroidManifest() {
        File xmlFile = new File("JavaTest/AndroidManifest.xml");
        AndroidManifest manifest = JAXB.unmarshal(xmlFile, AndroidManifest.class);
        System.out.println("解析Android Manifest 完毕!");

        File targetFile = new File("JavaTest/AndroidManifest_Changed.xml");
        HashSet<AndroidManifest.Permission> usesPermissions = manifest.usesPermissions;
            for (AndroidManifest.Permission usesPermission : usesPermissions) {
//                if(usesPermission.getName().equals("android.permission.GET_TASKS")){
//                    usesPermission.addAttribute(new QName("http://schemas.android.com/tools","node"),"hhhhhhh");
//                }
            }
        JAXB.marshal(manifest, targetFile);

    }

    public static void  marshalTest(){
        File xmlFile = new File("TestJaxbBean.xml");
        try {
            JAXBContext ctx = JAXBContext.newInstance(TestJaxbBean.class);
            Marshaller marshaller = ctx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING,"UTF-8");

            TestJaxbBean dataBean = new TestJaxbBean();
            TestJaxbBean.UserInfoAttr userInfoAttr = new TestJaxbBean.UserInfoAttr();
            userInfoAttr.setName("KingZ");
            ArrayList<TestItem>  itemArrayList = new ArrayList<>();
            itemArrayList.add(new TestItem("30","Witcher"));
            itemArrayList.add(new TestItem("22","NR"));
            dataBean.games = itemArrayList;
            dataBean.userInfo = userInfoAttr;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			dataBean.birth = sdf.format(new Date());
			marshaller.marshal(dataBean, xmlFile);
			System.out.println("Obj2Xml Over!");
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    /**
     *  <p>
     *      Unmarshalling from a File
     *  <blockquote>
     *    <pre>
     *       JAXBContext jc = JAXBContext.newInstance( "com.acme.foo" );
     *       Unmarshaller u = jc.createUnmarshaller();
     *       Object o = u.unmarshal( new File( "nosferatu.xml" ) );
     *   </pre>
     * </blockquote>
     */
    public static void  unMarshalTest(){
        File xmlFile = new File("foo.xml");
        try {
            JAXBContext jc = JAXBContext.newInstance(TestJaxbBean.class);
            Unmarshaller u = jc.createUnmarshaller();
            TestJaxbBean o = (TestJaxbBean)u.unmarshal(xmlFile);
            System.out.println("Xml2Obj Over!");
            System.out.println(o.toString());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
