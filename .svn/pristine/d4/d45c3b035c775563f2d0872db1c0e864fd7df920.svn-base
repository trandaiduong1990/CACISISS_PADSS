package org.transinfo.cacis.util;

// jdk1.4.1
import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

// using DOM
public class XmlUtil {
    /**
     * This method process the xml and returns the elements.
     * @param File Input xml file.
     * @param String Header tag.
     * @param ArrayList List of Child tags.
     * @return Hashtable.
     */
    public static Hashtable processXml(File file, String headerTag, ArrayList childTag) {
        //File file = new File("howto.xml");
        Hashtable hmpAllTags = new Hashtable();
        System.out.println("=====1102..1=====");
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            System.out.println("=====1102..2=====");
            System.out.println("file = " + file.toString());
            System.out.println("file = " + file.exists());
            System.out.println("file = " + file.getName());
            System.out.println("file = " + file.getPath());
            Document doc = builder.parse(file);
            System.out.println("=====1102..3=====");
            NodeList nodes = doc.getElementsByTagName(headerTag);
            System.out.println("=====1102..4=====");
            for (int i = 0; i < nodes.getLength(); i++) {
            	System.out.println("=====1102..for01=====");
                Element element = (Element) nodes.item(i);
                Hashtable hmpEelement = new Hashtable();
                String hmpName = "";
                for(int j=0;j<childTag.size();j++){
                	System.out.println("=====1102..for02=====");
                    String cTag = (String)childTag.get(j);
                    NodeList child = element.getElementsByTagName(cTag);
                    Element line = (Element) child.item(0);
                    String cValue = getCharacterDataFromElement(line);
                    //System.out.println(cTag+ " : " + cValue);
                    hmpEelement.put(cTag, cValue);
                    //if(j==0) hmpName = cValue; // considaring the first child tag value is unique.
                    hmpName = "" + i;
                }
                System.out.println("=====1102..5=====");
                hmpAllTags.put(hmpName,hmpEelement);
                System.out.println("=====1102..6=====");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return hmpAllTags;
    }
    /**
     * This method returns the element value.
     * @param Element.
     * @return String Element value.
     */
    public static String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "?";
    }
}

