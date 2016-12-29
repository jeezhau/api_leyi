package me.jeekhan.leyi.common;

import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class XmlDocUtils {

    public void parserXml(String xml) {
        try {
            Document document = DocumentHelper.parseText(xml);
            Element users = document.getRootElement();
            for (Iterator i = users.elementIterator(); i.hasNext();) {
                Element user = (Element) i.next();
                for (Iterator j = user.elementIterator(); j.hasNext();) {
                    Element node = (Element) j.next();
                    System.out.println(node.getName() + ":" + node.getText());
                }
                System.out.println();
            }
        } catch (DocumentException e) {
            System.out.println(e.getMessage());
        }
    }

}
