package com.example.HttpConnection;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by lester.ding on 9/5/2017.
 */

public class ContentHandler extends DefaultHandler {
    private String tagName;
    private StringBuilder id, name, version;
    public StringBuilder parsedText;

    @Override
    public void startDocument() throws SAXException {
        id = new StringBuilder();
        name = new StringBuilder();
        version = new StringBuilder();
        parsedText = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        tagName = localName;    //记录当前标签名
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        //根据当前的标签名判断将内容添加到哪一个StringBuilder对象中
        if("id".equals(tagName))
            id.append(ch, start, length);
        else if("name".equals(tagName))
            name.append(ch, start, length);
        else if("version".equals(tagName))
            version.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if("app".equals(localName)){
            parsedText.append("id is "+id.toString().trim()+"; name is "+name.toString().trim()+"; version is "+version.toString().trim()+";\r\n");
            //读取完一个app标签后要清空所有StringBuilder
            id.setLength(0);
            name.setLength(0);
            version.setLength(0);
        }
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }
}
