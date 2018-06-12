package com.xml.test.jaxb.base.dimain;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;

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
@XmlRootElement(name = "Root")
public class TestJaxbBean {

    @XmlJavaTypeAdapter(value = StringTrimAdapter.class)
    @XmlElement(name = "name")
    public String name;

    @XmlElementWrapper(name="games")
    @XmlElement(name = "item")
    public ArrayList<TestItem> games;

    //@XmlElement(name = "birthDate")
    public String birth;

    @Override
    public String toString() {
        return "TestJaxbBean{" +
                "birth='" + birth + '\'' +
                ", games='" + games.toString() + '\'' +
                ", name='" + name + '\'' +
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

}
