package com.briup.xml;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.List;

/**
 * @Auther: vanse(lc)
 * @Date: 2023/9/27-09-27-14:09
 * @Description：com.briup.xml
 */
public class TestXML2 {
    public static void main(String[] args) throws Exception {
        // 1.dom4j解析器
        SAXReader reader = new SAXReader();
        // 2.获取文档对象
        Document document = reader.read("env_common/src/main/resources/book.xml");
//        System.out.println("document = " + document);
        // 3.文档对象的根节点
        Element rootElement = document.getRootElement();
//        System.out.println(rootElement.getName());
        // 4.遍历所有元素节点
        List<Element> elements = rootElement.elements();
        for (Element element : elements) {
            // book节点
            List<Attribute> attributes = element.attributes();
            System.out.println(element.getName()+"属性");
            for (Attribute attribute : attributes) {
                System.out.println(attribute.getName()+"="+attribute.getValue());
            }
            System.out.println(element.getName()+"子节点");
            List<Element> childElements = element.elements();
            // 子节点
            for (Element childElement : childElements) {
                // 子节点属性
                List<Attribute> childAttributes = childElement.attributes();
                for (Attribute attribute : childAttributes) {
                    System.out.println(attribute.getName()+"="+attribute.getValue());
                }
                // 子节点键值
                System.out.println(childElement.getName()+"="+childElement.getText());
            }




        }

    }
}
