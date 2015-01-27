package com.lord.learn.xml;
import org.dom4j.Attribute;
import org.dom4j.VisitorSupport;

class MyVistor extends VisitorSupport {  
    public void visit(Attribute node) {  
        System.out.println("Attibute: " + node.getName() + "="  
                + node.getValue());  
    }  
  
    public void visit(org.dom4j.Element node) {  
        if (node.isTextOnly()) {  
            System.out.println("Element: " + node.getName() + "="  
                    + node.getText());  
        } else {  
            System.out.println(node.getName());  
        }  
    }  
  
    @Override  
    public void visit(org.dom4j.ProcessingInstruction node) {  
        System.out.println("PI:" + node.getTarget() + " " + node.getText());  
    }  
}  