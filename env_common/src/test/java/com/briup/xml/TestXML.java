package com.briup.xml;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * @Auther: vanse(lc)
 * @Date: 2023/9/27-09-27-14:09
 * @Description：com.briup.xml
 */
public class TestXML {
    public static void main(String[] args) throws Exception {
        // 1.将xml -> Document
        // 文档构建器工厂
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // 文档构建器
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        // 文档对象 env_common/src/main/resources/book.xml
        Document document = documentBuilder.parse("env_common/src/main/resources/book.xml");
        // 2.操作根节点
        Element documentElement = document.getDocumentElement();
//        System.out.println("documentElement = " + documentElement.getTagName());
        NodeList childNodes = documentElement.getChildNodes();
        // 9个节点
        System.out.println("childNodes = " + childNodes.getLength());
        for (int i = 0; i < childNodes.getLength(); i++) {
            // 每个节点
            Node item = childNodes.item(i);
//            System.out.println("item = " + item);
            short nodeType = item.getNodeType();
            // 如果是文本节点
//            if(nodeType == Node.TEXT_NODE){
//                System.out.println(item.getNodeValue());
//            }
            // 如果是元素节点
            if(nodeType == Node.ELEMENT_NODE){
                // book的属性
                NamedNodeMap attributes = item.getAttributes();
//                Node category = attributes.getNamedItem("category");
                System.out.println(item.getNodeName() + "属性：");
                for (int i1 = 0; i1 < attributes.getLength(); i1++) {
                    Node item1 = attributes.item(i1);
                    System.out.println(item1.getNodeName() + "=" + item1.getNodeValue());
                }
                // book的子节点
                System.out.println(item.getNodeName() + "子节点：");
                NodeList childNodes1 = item.getChildNodes();
                for (int i1 = 0; i1 < childNodes1.getLength(); i1++) {
                    // title author year
                    Node item1 = childNodes1.item(i1);
                    if(item1.getNodeType() == 1){
//                        System.out.println(item1.getNodeName());
                        NamedNodeMap attributes1 = item1.getAttributes();
                        for (int i2 = 0; i2 < attributes1.getLength(); i2++) {
                            Node item2 = attributes1.item(i2);
                            System.out.println(item2.getNodeName() + "=" + item2.getNodeValue());
                        }

                        System.out.println(item1.getNodeName() +"=" + item1.getTextContent());
                    }
                }
            }
        }
    }
}
