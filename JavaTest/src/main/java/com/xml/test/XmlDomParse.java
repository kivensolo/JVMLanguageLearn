package com.xml.test;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


/**
 * DOMj解析 */

public class XmlDomParse {

    public static void main(String[] args) throws Exception {
        read();
    }

    private static void read() throws ParserConfigurationException, SAXException, IOException {
        // TODO Auto-generated method stub
        //创建工厂---->创建Builder
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();

//        Document doc = db.parse(is);
        Document doc = db.parse("E:\\111.xml");
        Element root = doc.getDocumentElement();

        System.out.println("根节点：" + root.getTagName()+";name参数为："+root.getAttribute("name"));

        NodeList collegeLists = root.getChildNodes();
        if (collegeLists == null) {
            return;
        }
        for (int i = 0; i < collegeLists.getLength(); i++) {
            Node node_1 = collegeLists.item(i);
            System.out.println("第" + i + "个college节点为：" + node_1);
            if (node_1 != null && node_1.getNodeType() == Node.ELEMENT_NODE) {
                System.err.println("属性值为："+node_1.getAttributes().getNamedItem("name").getNodeValue());
//                // all class node
//                NodeList classLists = node_1.getChildNodes();
//                if (classLists == null) {
//                    continue;
//                }
//                for (int j = 0; j < classLists.getLength(); j++) {
//                    Node node_2 = classLists.item(j);
//                    if(node_2 != null && node_2.getNodeType() == Node.ELEMENT_NODE){
//                        System.err.println("\t\t" + node_2.getAttributes().getNamedItem("name").getNodeValue());
//                    }
//                }
            }

        }
//         String rootName = root.getNodeName();
//         String rootValue = root.getNodeValue();
//         System.out.println("�ڵ���Ϊ��"+rootName +"; �ڵ�ֵΪ��"+rootValue);


    }

}

