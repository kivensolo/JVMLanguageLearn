package com.xml.test.jaxb.base;

import com.xml.test.jaxb.base.dimain.TestItem;
import com.xml.test.jaxb.base.dimain.TestJaxbBean;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class JAXBDemo {
    public static void  marshalTest(){
        File xmlFile = new File("TestJaxbBean.xml");
        try {
            JAXBContext ctx = JAXBContext.newInstance(TestJaxbBean.class);
            Marshaller marshaller = ctx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING,"UTF-8");

            TestJaxbBean dataBean = new TestJaxbBean();
            dataBean.name = "KingZ";
            ArrayList<TestItem>  itemArrayList = new ArrayList<>();
            itemArrayList.add(new TestItem("30","Witcher"));
            itemArrayList.add(new TestItem("22","NR"));
            dataBean.games = itemArrayList;
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
