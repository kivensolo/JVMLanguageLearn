package com.kingz.format.xml.jaxb.dimain;

//import com.sun.org.apache.xerces.internal.dom.ElementNSImpl;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p> <b>Annotations explain:</b> </p>
 *
 *<p> {@link XmlJavaTypeAdapter}   Data Adapter/Prox
 *<p> {@link XmlElementWrapper}
 *          <p>   The usage is subject to the following constraints:
 *          <ul>
 *            <li> The property must be a collection property </li>
 *            <li> This annotation can be used with the following annotations:
 *                     {@link XmlElement},
 *                     {@link XmlElements},
 *                     {@link XmlElementRef},
 *                     {@link XmlElementRefs},
 *                     {@link XmlJavaTypeAdapter}.
 *            </li>
 *          </ul>
 *<p> {@link XmlElement}
 *<p> {@link XmlElements}
 *<p> {@link XmlElementRef}
 *<p> {@link XmlElementRefs}
 */
@SuppressWarnings("Pass")
@XmlRootElement(name = "Root")
public class TestJaxbBean {

    @XmlElement(name = "user_info")
    public UserInfoAttr userInfo;

    @XmlElementWrapper(name="games")
    @XmlElement(name = "item")
    public ArrayList<TestItem> games;

    @XmlJavaTypeAdapter(value = StringTrimAdapter.class)
    @XmlElement(name = "birthDate")
    public String birth;

    @XmlAnyElement()
    Object[] otherElements;

    @Override
    public String toString() {
        return "TestJaxbBean{\n" + userInfo.toString() + '\n' +
                "   birth=" + birth + '\n' +
                "   games=" + games.toString() + '\n' +
//                "   othersA=" + (otherElements == null ? "null" : ((ElementNSImpl)otherElements[0]).getTextContent()) + '\n' +
                '}';
    }


    public static class StringTrimAdapter extends XmlAdapter<String, String> {
        @Override
        public String unmarshal(String v) throws Exception {
            if (v == null) {
                return null;
            }
            v = v.trim();
            String extra = "\n|\r|\t";
            if (v.contains(extra)) {
                v = v.replace(extra, "");
            }
            return v.isEmpty() ? null : v;
        }

        @Override
        public String marshal(String v) throws Exception {
            return v + " -ex";
        }
    }

    public static class UserInfoAttr {
        public static final String NAME_SPACE = "http://www.mynamespace.com";
        //----------------- 指定的属性值 -----------------
        @XmlAttribute(namespace = NAME_SPACE, required = true)
		String name;

        @XmlAttribute(namespace = NAME_SPACE)
		String age;

        @XmlAttribute(namespace = NAME_SPACE)
		String sex;
        //----------------- 指定的属性值 -----------------


        //----------------- 未指定的属性值 -----------------
        @XmlAnyAttribute()
        HashMap<QName, String> attributes;

        @Override
        public String toString() {
            return "    UserInfoAttr{" + '\n' +
                    "       name=" + name + '\n' +
                    "       age=" + age + '\n' +
                    "       sex=" + sex + '\n' +
                    "       otherAttr=" + attributes.toString() + '\n' +
                    "   }";
        }

        public void setName(String n){
            this.name = n;
        }
    }
}
