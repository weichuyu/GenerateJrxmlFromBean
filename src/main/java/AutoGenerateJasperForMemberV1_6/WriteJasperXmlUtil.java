package AutoGenerateJasperForMemberV1_6;


import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class WriteJasperXmlUtil {
    public static int margin = 0;
    public static boolean needHeaderAndFooter = true;
    public static void WriteJasperXml(ArrayList<MyFields> header,ArrayList<MyFields> footer,ArrayList<MyFields> fields,String fileName,int totalwidth,HashMap<String,Integer> keymap,String xmlname) throws Exception{
        String workpath="E:\\Tutorial\\Jasper\\";
        workpath = AutoGenerateJasperLoop.exportPath;
        //String xmlPath = "D:\\Tutorial\\Jasper\\JR_33_QueryCertainMan.jrxml";
        String printPath = workpath+xmlname+".jrxml";
        //printXML(xmlPath,printPath,header,footer,fields,totalwidth,fileName);
        printXML2(printPath,header,footer,fields,totalwidth,fileName);
        System.out.println("FIN");
    }
    public static void printXML(String xmlFile,String printPath,ArrayList<MyFields> header,ArrayList<MyFields> footer,ArrayList<MyFields> fields,int totalwidth,String fileName)throws Exception{
        SAXReader reader = new SAXReader(); //User.hbm.xml表示你要解析的xml文档
        Document doc = reader.read(new File(xmlFile));
        Element rootElt = doc.getRootElement(); // 获取根节点
        {
            rootElt.remove(rootElt.attribute("name"));
            //margain right=20
            rootElt.addAttribute("name", fileName+".jrxml");
            rootElt.remove(rootElt.attribute("columnWidth"));
            //margain right=20
            rootElt.addAttribute("columnWidth", totalwidth+"");
            rootElt.remove(rootElt.attribute("pageWidth"));
            //margain right=20
            rootElt.addAttribute("pageWidth", totalwidth+20+20+"");
        }
        System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称
        {
            ArrayList<Element> deleteValue=new ArrayList<Element>();
            Iterator iterInfo = rootElt.elementIterator("field");
            while (iterInfo.hasNext()){
                Element recordEleSQL = (Element) iterInfo.next();
                deleteValue.add(recordEleSQL);
            }
            for(Element a:deleteValue){
                a.detach();
            }
            for(MyFields fieldname:fields){
                Element cd = rootElt.addElement("field");
                cd.addAttribute("name", fieldname.name);
                cd.addAttribute("class", fieldname.type);
                Element cn = cd.addElement("name");
                //String sntext="<![CDATA["+fieldname.name+"]]>";
                cn.addCDATA(fieldname.name);
            }
            Element cd = rootElt.addElement("field");
            cd.addAttribute("name", "class");
            cd.addAttribute("class", "java.lang.Class");
            Element cn = cd.addElement("name");
            //String sntext="<![CDATA["+"class"+"]]>";
            //cn.setText(sntext);
            cn.addCDATA("class");
        }
        {
            Iterator iterInfo = rootElt.elementIterator("title");
            while (iterInfo.hasNext()){
                Element recordtitle = (Element) iterInfo.next();
                Iterator iterband = recordtitle.elementIterator("band");
                while (iterband.hasNext()){
                    Element recordband = (Element) iterband.next();
                    Iterator iterstatictext = recordband.elementIterator("staticText");
                    while (iterstatictext.hasNext()){
                        Element recordstatictext = (Element) iterstatictext.next();
                        Iterator iterreportElement = recordstatictext.elementIterator("reportElement");
                        while (iterreportElement.hasNext()){
                            Element recordreportElement = (Element) iterreportElement.next();
                            recordreportElement.setAttributeValue("width", ""+header.get(0).width);
                        }
                        Iterator itertext = recordstatictext.elementIterator("text");
                        while (itertext.hasNext()){
                            Element recordreportElement = (Element) itertext.next();
                            //String st="<![CDATA["+header.get(0).viewName+"]]>";
                            //recordreportElement.setText(st);
                            recordreportElement.addCDATA(header.get(0).viewName);
                        }
                    }
                }
            }
        }
        {
            Iterator iterInfo = rootElt.elementIterator("pageHeader");
            while (iterInfo.hasNext()){
                Element recordtitle = (Element) iterInfo.next();
                Iterator iterband = recordtitle.elementIterator("band");
                while (iterband.hasNext()){
                    Element recordband = (Element) iterband.next();
                    Iterator iterstatictext = recordband.elementIterator("staticText");
                    while (iterstatictext.hasNext()){
                        Element recordstatictext = (Element) iterstatictext.next();
                        Iterator iterreportElement = recordstatictext.elementIterator("reportElement");
                        while (iterreportElement.hasNext()){
                            Element recordreportElement = (Element) iterreportElement.next();
                            recordreportElement.setAttributeValue("width", ""+header.get(3).width);
                            recordreportElement.setAttributeValue("x", ""+header.get(3).x);
                        }
                    }
                    //
                    //
                    Iterator iterstatictext2 = recordband.elementIterator("textField");
                    int i=1;
                    while (iterstatictext2.hasNext()){
                        Element recordstatictext2 = (Element) iterstatictext2.next();
                        Iterator iterreportElement = recordstatictext2.elementIterator("reportElement");
                        while (iterreportElement.hasNext()){
                            Element recordreportElement = (Element) iterreportElement.next();
                            recordreportElement.setAttributeValue("width", ""+header.get(i).width);
                            recordreportElement.setAttributeValue("x", ""+header.get(i).x);
                        }
                        i++;
                    }
                }
            }
        }
        {
            Iterator iterInfo = rootElt.elementIterator("pageFooter");
            while (iterInfo.hasNext()){
                Element recordtitle = (Element) iterInfo.next();
                Iterator iterband = recordtitle.elementIterator("band");
                while (iterband.hasNext()){
                    Element recordband = (Element) iterband.next();
                    //
                    //
                    Iterator iterstatictext2 = recordband.elementIterator("textField");
                    int i=0;
                    while (iterstatictext2.hasNext()){
                        Element recordstatictext2 = (Element) iterstatictext2.next();
                        Iterator iterreportElement = recordstatictext2.elementIterator("reportElement");
                        while (iterreportElement.hasNext()){
                            Element recordreportElement = (Element) iterreportElement.next();
                            recordreportElement.setAttributeValue("width", ""+footer.get(i).width);
                            recordreportElement.setAttributeValue("x", ""+footer.get(i).x);
                        }
                        i++;
                    }
                }
            }
        }
        {
            Iterator iterInfo = rootElt.elementIterator("columnHeader");
            while (iterInfo.hasNext()){
                Element recordtitle = (Element) iterInfo.next();
                Iterator iterband = recordtitle.elementIterator("band");
                while (iterband.hasNext()){
                    Element recordband = (Element) iterband.next();
                    ArrayList<Element> deleteValue=new ArrayList<Element>();
                    Iterator iterstatictext2 = recordband.elementIterator("staticText");
                    while (iterstatictext2.hasNext()){
                        Element recordstatictext2 = (Element) iterstatictext2.next();
                        deleteValue.add(recordstatictext2);
                    }
                    for(Element a:deleteValue){
                        a.detach();
                    }
                    for(MyFields fieldname:fields){
                        Element cd = recordband.addElement("staticText");
                        Element cn1 = cd.addElement("reportElement");
                        cn1.addAttribute("x", ""+fieldname.x);
                        cn1.addAttribute("y", ""+fieldname.y);
                        cn1.addAttribute("width", ""+fieldname.width);
                        cn1.addAttribute("height", ""+fieldname.height);
                        Element cn2 = cd.addElement("box");
                        Element cn21 = cn2.addElement("pen");
                        cn21.addAttribute("lineWidth","0.5");
                        cn21.addAttribute("lineStyle","Solid");
                        Element cn22 = cn2.addElement("topPen");
                        cn22.addAttribute("lineWidth","0.5");
                        cn22.addAttribute("lineStyle","Solid");
                        Element cn23 = cn2.addElement("leftPen");
                        cn23.addAttribute("lineWidth","0.5");
                        cn23.addAttribute("lineStyle","Solid");
                        Element cn24 = cn2.addElement("bottomPen");
                        cn24.addAttribute("lineWidth","0.5");
                        cn24.addAttribute("lineStyle","Solid");
                        Element cn25 = cn2.addElement("rightPen");
                        cn25.addAttribute("lineWidth","0.5");
                        cn25.addAttribute("lineStyle","Solid");
                        Element cn3 = cd.addElement("textElement");
                        cn3.addAttribute("textAlignment", "Center");
                        cn3.addAttribute("verticalAlignment", "Middle");
                        Element cn31 = cn3.addElement("font");
                        cn31.addAttribute("fontName", "宋体");
                        cn31.addAttribute("pdfFontName", "STSong-Light");
                        cn31.addAttribute("pdfEncoding", "UniGB-UCS2-H");
                        Element cn4 = cd.addElement("text");
                        //cn4.setText("<![CDATA["+fieldname.viewName+"]]>");
                        cn4.addCDATA(fieldname.viewName);
                    }
                }
            }
        }
        {
            Iterator iterInfo = rootElt.elementIterator("detail");
            while (iterInfo.hasNext()){
                Element recordtitle = (Element) iterInfo.next();
                Iterator iterband = recordtitle.elementIterator("band");
                while (iterband.hasNext()){
                    Element recordband = (Element) iterband.next();
                    ArrayList<Element> deleteValue=new ArrayList<Element>();
                    Iterator iterstatictext2 = recordband.elementIterator("textField");
                    while (iterstatictext2.hasNext()){
                        Element recordstatictext2 = (Element) iterstatictext2.next();
                        deleteValue.add(recordstatictext2);
                    }
                    for(Element a:deleteValue){
                        a.detach();
                    }
                    for(MyFields fieldname:fields){
                        Element cd = recordband.addElement("textField");
                        cd.addAttribute("isBlankWhenNull", "true");

                        Element cn1 = cd.addElement("reportElement");
                        cn1.addAttribute("x", ""+fieldname.x);
                        cn1.addAttribute("y", ""+fieldname.y);
                        cn1.addAttribute("width", ""+fieldname.width);
                        cn1.addAttribute("height", ""+fieldname.height);
                        Element cn2 = cd.addElement("box");
                        Element cn21 = cn2.addElement("pen");
                        cn21.addAttribute("lineWidth","0.5");
                        cn21.addAttribute("lineStyle","Solid");
                        Element cn22 = cn2.addElement("topPen");
                        cn22.addAttribute("lineWidth","0.5");
                        cn22.addAttribute("lineStyle","Solid");
                        Element cn23 = cn2.addElement("leftPen");
                        cn23.addAttribute("lineWidth","0.5");
                        cn23.addAttribute("lineStyle","Solid");
                        Element cn24 = cn2.addElement("bottomPen");
                        cn24.addAttribute("lineWidth","0.5");
                        cn24.addAttribute("lineStyle","Solid");
                        Element cn25 = cn2.addElement("rightPen");
                        cn25.addAttribute("lineWidth","0.5");
                        cn25.addAttribute("lineStyle","Solid");
                        Element cn3 = cd.addElement("textElement");
                        String align= "Left";
                        if(fieldname.align.equals("left")){
                            align="Left";
                        }else if(fieldname.align.equals("right")){
                            align="Right";
                        }else if(fieldname.align.equals("center")){
                            align="Center";
                        }
                        cn3.addAttribute("textAlignment", align);
                        cn3.addAttribute("verticalAlignment", "Middle");
                        Element cn31 = cn3.addElement("font");
                        cn31.addAttribute("fontName", "宋体");
                        cn31.addAttribute("pdfFontName", "STSong-Light");
                        cn31.addAttribute("pdfEncoding", "UniGB-UCS2-H");
                        Element cn4 = cd.addElement("textFieldExpression");
                        //cn4.setText("<![CDATA[$F{"+fieldname.name+"}]]>");

                        if(fieldname.useNewBigDecimal && !fieldname.NumberType.equals(MyFields.numberTypeNone)){
                            if(fieldname.NumberType.equals(MyFields.numberTypePercent)){
                                cn4.addCDATA("(null!=$F{"+fieldname.name+"}&&$F{"+fieldname.name+"}.length()>0)?"+"new java.math.BigDecimal($F{"+fieldname.name+"}.replaceAll( \",\", \"\" ).replaceAll( \"%\", \"\" )).divide(new BigDecimal(100))"+":\"\"");
                            }else{
                                cn4.addCDATA("(null!=$F{"+fieldname.name+"}&&$F{"+fieldname.name+"}.length()>0)?"+"new java.math.BigDecimal($F{"+fieldname.name+"}.replaceAll( \",\", \"\" ))"+":\"\"");
                            }
                        }else{
                            cn4.addCDATA("$F{"+fieldname.name+"}");
                        }
                        if(fieldname.NumberType.equals(MyFields.numberTypeGroup)){
                            if (fieldname.decimalPlaces != 0 || fieldname.maxdecimalPlaces == 0) {
                                String pattern = PatternControlUtil.getPattern(fieldname.decimalPlaces, fieldname.maxdecimalPlaces, false, true);
                                cd.addAttribute("pattern", pattern);
                            }else if(fieldname.decimalPlaces == 0 && fieldname.maxdecimalPlaces != 0){
                                Element patternexpr = cd.addElement("patternExpression");
                                patternexpr.addCDATA("(null!=$F{"+fieldname.name+"}&&$F{"+fieldname.name+"}.length()>0&&$F{"+fieldname.name+"}.contains(\".\"))?"+"\""+PatternControlUtil.getPattern(1, fieldname.maxdecimalPlaces, false, true)+"\""+":\""+PatternControlUtil.getPattern(fieldname.decimalPlaces, fieldname.maxdecimalPlaces, false, true)+"\"");
                            }
                        }else if(fieldname.NumberType.equals(MyFields.numberTypePercent)){
                            if (fieldname.decimalPlaces != 0 || fieldname.maxdecimalPlaces == 0) {
                                String pattern = PatternControlUtil.getPattern(fieldname.decimalPlaces, fieldname.maxdecimalPlaces, false, true);
                                cd.addAttribute("pattern", pattern);
                            }else if(fieldname.decimalPlaces == 0 && fieldname.maxdecimalPlaces != 0){
                                Element patternexpr = cd.addElement("patternExpression");
                                patternexpr.addCDATA("(null!=$F{"+fieldname.name+"}&&$F{"+fieldname.name+"}.length()>0&&$F{"+fieldname.name+"}.contains(\".\"))?"+"\""+PatternControlUtil.getPattern(1, fieldname.maxdecimalPlaces, false, true)+"\""+":\""+PatternControlUtil.getPattern(fieldname.decimalPlaces, fieldname.maxdecimalPlaces, false, true)+"\"");
                            }
                        }
                    }
                }
            }
        }
        //
        //打印
        //
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(printPath),"UTF-8");
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setExpandEmptyElements(true);
        format.setTrimText(false);
        format.setIndent(true);      // 设置是否缩进
        format.setIndent("   ");     // 以空格方式实现缩进
        format.setNewlines(true);    // 设置是否换行
        format.setEncoding("UTF-8");    // 指定XML编码
        XMLWriter writer = new XMLWriter(osw,format);
        writer.write(doc);
        writer.close();
    }
    public static void printXML2(String printPath,ArrayList<MyFields> header,ArrayList<MyFields> footer,ArrayList<MyFields> fields,int totalwidth,String fileName)throws Exception{
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+"\r\n");
        localStringBuilder.append("<jasperReport xmlns=\"http://jasperreports.sourceforge.net/jasperreports\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://jasperreports.sourceforge.net/jasperreports http://jasperreports.sourceforge.net/xsd/jasperreport.xsd\" name=\""+fileName+""+"\" language=\"groovy\" pageWidth=\""+(totalwidth+margin+margin+"")+"\" pageHeight=\"842\" columnWidth=\""+totalwidth+"\" leftMargin=\""+margin+"\" rightMargin=\""+margin+"\" topMargin=\""+margin+"\" bottomMargin=\""+margin+"\" uuid=\""+UUID.randomUUID().toString()+"\">"+"\r\n");
        localStringBuilder.append("\t<property name=\"ireport.zoom\" value=\"1.0\"/>"+"\r\n");
        localStringBuilder.append("\t<property name=\"ireport.x\" value=\"176\"/>"+"\r\n");
        localStringBuilder.append("\t<property name=\"ireport.y\" value=\"0\"/>"+"\r\n");
        localStringBuilder.append("\t<property name=\"ireport.scriptlethandling\" value=\"0\"/>"+"\r\n");
        localStringBuilder.append("\t<property name=\"ireport.encoding\" value=\"UTF-8\"/>"+"\r\n");
        localStringBuilder.append("\t<import value=\"net.sf.jasperreports.engine.*\"/>"+"\r\n");
        localStringBuilder.append("\t<import value=\"java.util.*\"/>"+"\r\n");
        localStringBuilder.append("\t<import value=\"net.sf.jasperreports.engine.data.*\"/>"+"\r\n");
        for(MyFields fieldname:fields){
            localStringBuilder.append("\t<field name=\""+fieldname.name+"\" class=\""+fieldname.type+"\"/>"+"\r\n");
        }
        localStringBuilder.append("\t<field name=\"class\" class=\"java.lang.Class\"/>"+"\r\n");
        localStringBuilder.append("\t<background>"+"\r\n");
        localStringBuilder.append("\t\t<band splitType=\"Stretch\"/>"+"\r\n");
        localStringBuilder.append("\t</background>"+"\r\n");
        localStringBuilder.append("\t<title>"+"\r\n");
        if(needHeaderAndFooter){
            localStringBuilder.append("\t\t<band height=\"50\" splitType=\"Stretch\">"+"\r\n");
            localStringBuilder.append("\t\t\t<staticText>"+"\r\n");
            localStringBuilder.append("\t\t\t\t<reportElement key=\"staticText\" x=\"0\" y=\"0\" width=\""+header.get(0).width+"\" height=\"50\" uuid=\""+UUID.randomUUID().toString()+"\"/>"+"\r\n");
            localStringBuilder.append("\t\t\t\t<box>"+"\r\n");
            localStringBuilder.append("\t\t\t\t\t<pen lineWidth=\"0.0\" lineStyle=\"Solid\"/>"+"\r\n");
            localStringBuilder.append("\t\t\t\t\t<topPen lineWidth=\"0.0\" lineStyle=\"Solid\"/>"+"\r\n");
            localStringBuilder.append("\t\t\t\t\t<leftPen lineWidth=\"0.0\" lineStyle=\"Solid\"/>"+"\r\n");
            localStringBuilder.append("\t\t\t\t\t<bottomPen lineWidth=\"0.0\" lineStyle=\"Solid\"/>"+"\r\n");
            localStringBuilder.append("\t\t\t\t\t<rightPen lineWidth=\"0.0\" lineStyle=\"Solid\"/>"+"\r\n");
            localStringBuilder.append("\t\t\t\t</box>"+"\r\n");
            localStringBuilder.append("\t\t\t\t<textElement textAlignment=\"Center\" verticalAlignment=\"Middle\">"+"\r\n");
            localStringBuilder.append("\t\t\t\t\t<font fontName=\"宋体\" size=\"20\" pdfFontName=\"STSong-Light\" pdfEncoding=\"UniGB-UCS2-H\" isPdfEmbedded=\"true\"/>"+"\r\n");
            localStringBuilder.append("\t\t\t\t</textElement>"+"\r\n");
            localStringBuilder.append("\t\t\t\t<text><![CDATA["+header.get(0).viewName+"]]></text>"+"\r\n");
            localStringBuilder.append("\t\t\t</staticText>"+"\r\n");
            localStringBuilder.append("\t\t</band>"+"\r\n");
        }else{
            localStringBuilder.append("\t\t<band splitType=\"Stretch\"/>"+"\r\n");
        }
        localStringBuilder.append("\t</title>"+"\r\n");
        localStringBuilder.append("\t<pageHeader>"+"\r\n");
        if(needHeaderAndFooter){
            localStringBuilder.append("\t\t<band height=\"23\" splitType=\"Stretch\">"+"\r\n");
            localStringBuilder.append("\t\t\t<textField pattern=\"yyyyMMdd\" isBlankWhenNull=\"false\">"+"\r\n");
            localStringBuilder.append("\t\t\t\t<reportElement key=\"textField\" x=\""+header.get(1).x+"\" y=\"0\" width=\""+header.get(1).width+"\" height=\"23\" uuid=\""+UUID.randomUUID().toString()+"\"/>"+"\r\n");
            localStringBuilder.append("\t\t\t\t<box>"+"\r\n");
            localStringBuilder.append("\t\t\t\t\t<pen lineWidth=\"0.5\" lineStyle=\"Solid\"/>"+"\r\n");
            localStringBuilder.append("\t\t\t\t\t<topPen lineWidth=\"0.5\" lineStyle=\"Solid\"/>"+"\r\n");
            localStringBuilder.append("\t\t\t\t\t<leftPen lineWidth=\"0.5\" lineStyle=\"Solid\"/>"+"\r\n");
            localStringBuilder.append("\t\t\t\t\t<bottomPen lineWidth=\"0.5\" lineStyle=\"Solid\"/>"+"\r\n");
            localStringBuilder.append("\t\t\t\t\t<rightPen lineWidth=\"0.5\" lineStyle=\"Solid\"/>"+"\r\n");
            localStringBuilder.append("\t\t\t\t</box>"+"\r\n");
            localStringBuilder.append("\t\t\t\t<textElement textAlignment=\"Center\" verticalAlignment=\"Middle\">"+"\r\n");
            localStringBuilder.append("\t\t\t\t\t<font fontName=\"宋体\" pdfFontName=\"STSong-Light\" pdfEncoding=\"UniGB-UCS2-H\" isPdfEmbedded=\"true\"/>"+"\r\n");
            localStringBuilder.append("\t\t\t\t</textElement>"+"\r\n");
            localStringBuilder.append("\t\t\t\t<textFieldExpression><![CDATA[new java.util.Date()]]></textFieldExpression>"+"\r\n");
            localStringBuilder.append("\t\t\t</textField>"+"\r\n");
            localStringBuilder.append("\t\t\t<textField isBlankWhenNull=\"false\">"+"\r\n");
            localStringBuilder.append("\t\t\t\t<reportElement key=\"textField\" x=\""+header.get(2).x+"\" y=\"0\" width=\""+header.get(2).width+"\" height=\"23\" uuid=\""+UUID.randomUUID().toString()+"\"/>"+"\r\n");
            localStringBuilder.append("\t\t\t\t<box>"+"\r\n");
            localStringBuilder.append("\t\t\t\t\t<pen lineWidth=\"0.5\" lineStyle=\"Solid\"/>"+"\r\n");
            localStringBuilder.append("\t\t\t\t\t<topPen lineWidth=\"0.5\" lineStyle=\"Solid\"/>"+"\r\n");
            localStringBuilder.append("\t\t\t\t\t<leftPen lineWidth=\"0.5\" lineStyle=\"Solid\"/>"+"\r\n");
            localStringBuilder.append("\t\t\t\t\t<bottomPen lineWidth=\"0.5\" lineStyle=\"Solid\"/>"+"\r\n");
            localStringBuilder.append("\t\t\t\t\t<rightPen lineWidth=\"0.5\" lineStyle=\"Solid\"/>"+"\r\n");
            localStringBuilder.append("\t\t\t\t</box>"+"\r\n");
            localStringBuilder.append("\t\t\t\t<textElement textAlignment=\"Center\" verticalAlignment=\"Middle\">"+"\r\n");
            localStringBuilder.append("\t\t\t\t\t<font fontName=\"宋体\" pdfFontName=\"STSong-Light\" pdfEncoding=\"UniGB-UCS2-H\" isPdfEmbedded=\"true\"/>"+"\r\n");
            localStringBuilder.append("\t\t\t\t</textElement>"+"\r\n");
            localStringBuilder.append("\t\t\t\t<textFieldExpression><![CDATA[\"打印日期：\"]]></textFieldExpression>"+"\r\n");
            localStringBuilder.append("\t\t\t</textField>"+"\r\n");
            localStringBuilder.append("\t\t\t<staticText>"+"\r\n");
            localStringBuilder.append("\t\t\t\t<reportElement key=\"staticText\" x=\""+header.get(3).x+"\" y=\"0\" width=\""+header.get(3).width+"\" height=\"23\" uuid=\""+UUID.randomUUID().toString()+"\"/>"+"\r\n");
            localStringBuilder.append("\t\t\t\t<box>"+"\r\n");
            localStringBuilder.append("\t\t\t\t\t<pen lineWidth=\"0.5\"/>"+"\r\n");
            localStringBuilder.append("\t\t\t\t\t<topPen lineWidth=\"0.5\"/>"+"\r\n");
            localStringBuilder.append("\t\t\t\t\t<leftPen lineWidth=\"0.5\"/>"+"\r\n");
            localStringBuilder.append("\t\t\t\t\t<bottomPen lineWidth=\"0.5\"/>"+"\r\n");
            localStringBuilder.append("\t\t\t\t\t<rightPen lineWidth=\"0.5\"/>"+"\r\n");
            localStringBuilder.append("\t\t\t\t</box>"+"\r\n");
            localStringBuilder.append("\t\t\t\t<text><![CDATA[]]></text>"+"\r\n");
            localStringBuilder.append("\t\t\t</staticText>"+"\r\n");
            localStringBuilder.append("\t\t</band>"+"\r\n");
        }else{
            localStringBuilder.append("\t\t<band splitType=\"Stretch\"/>"+"\r\n");
        }
        localStringBuilder.append("\t</pageHeader>"+"\r\n");
        localStringBuilder.append("\t<columnHeader>"+"\r\n");
        localStringBuilder.append("\t\t<band height=\"20\" splitType=\"Stretch\">"+"\r\n");
        for(MyFields fieldname:fields){
            localStringBuilder.append("\t\t\t<staticText>"+"\r\n");
            localStringBuilder.append("\t\t\t\t<reportElement key=\"staticText\" x=\""+fieldname.x+"\" y=\""+fieldname.y+"\" width=\""+fieldname.width+"\" height=\""+fieldname.height+"\" uuid=\""+UUID.randomUUID().toString()+"\"/>"+"\r\n");
            localStringBuilder.append("\t\t\t\t<box>"+"\r\n");
            localStringBuilder.append("\t\t\t\t\t<pen lineWidth=\"0.5\" lineStyle=\"Solid\"/>"+"\r\n");
            localStringBuilder.append("\t\t\t\t\t<topPen lineWidth=\"0.5\" lineStyle=\"Solid\"/>"+"\r\n");
            localStringBuilder.append("\t\t\t\t\t<leftPen lineWidth=\"0.5\" lineStyle=\"Solid\"/>"+"\r\n");
            localStringBuilder.append("\t\t\t\t\t<bottomPen lineWidth=\"0.5\" lineStyle=\"Solid\"/>"+"\r\n");
            localStringBuilder.append("\t\t\t\t\t<rightPen lineWidth=\"0.5\" lineStyle=\"Solid\"/>"+"\r\n");
            localStringBuilder.append("\t\t\t\t</box>"+"\r\n");
            localStringBuilder.append("\t\t\t\t<textElement textAlignment=\"Center\" verticalAlignment=\"Middle\">"+"\r\n");
            localStringBuilder.append("\t\t\t\t\t<font fontName=\"宋体\" pdfFontName=\"STSong-Light\" pdfEncoding=\"UniGB-UCS2-H\"/>"+"\r\n");
            localStringBuilder.append("\t\t\t\t</textElement>"+"\r\n");
            localStringBuilder.append("\t\t\t\t<text><![CDATA["+fieldname.viewName+"]]></text>"+"\r\n");
            localStringBuilder.append("\t\t\t</staticText>"+"\r\n");
        }
        localStringBuilder.append("\t\t</band>"+"\r\n");
        localStringBuilder.append("\t</columnHeader>"+"\r\n");
        localStringBuilder.append("\t<detail>"+"\r\n");
        localStringBuilder.append("\t\t<band height=\"20\" splitType=\"Stretch\">"+"\r\n");

        for(MyFields fieldname:fields){
            String pattern = "";
            if(fieldname.NumberType.equals(MyFields.numberTypeNone)){
                localStringBuilder.append("\t\t\t<textField isBlankWhenNull=\"true\">"+"\r\n");
            }else if(fieldname.decimalPlaces != 0 || fieldname.maxdecimalPlaces == 0){
                if(fieldname.NumberType.equals(MyFields.numberTypeGroup)){
                    pattern = PatternControlUtil.getPattern(fieldname.decimalPlaces, fieldname.maxdecimalPlaces, false, true);
                }else if(fieldname.NumberType.equals(MyFields.numberTypePercent)){
                    pattern = PatternControlUtil.getPattern(fieldname.decimalPlaces, fieldname.maxdecimalPlaces, true, true);
                }else{
                    pattern = PatternControlUtil.getPattern(fieldname.decimalPlaces, fieldname.maxdecimalPlaces, false, true);
                }
                localStringBuilder.append("\t\t\t<textField pattern=\""+pattern+"\" isBlankWhenNull=\"true\">"+"\r\n");
            }else{
                localStringBuilder.append("\t\t\t<textField isBlankWhenNull=\"true\">"+"\r\n");
            }
            localStringBuilder.append("\t\t\t\t<reportElement key=\"textField\" x=\""+fieldname.x+"\" y=\""+fieldname.y+"\" width=\""+fieldname.width+"\" height=\""+fieldname.height+"\" uuid=\""+UUID.randomUUID().toString()+"\"/>"+"\r\n");
            localStringBuilder.append("\t\t\t\t<box>"+"\r\n");
            localStringBuilder.append("\t\t\t\t\t<pen lineWidth=\"0.5\" lineStyle=\"Solid\"/>"+"\r\n");
            localStringBuilder.append("\t\t\t\t\t<topPen lineWidth=\"0.5\" lineStyle=\"Solid\"/>"+"\r\n");
            localStringBuilder.append("\t\t\t\t\t<leftPen lineWidth=\"0.5\" lineStyle=\"Solid\"/>"+"\r\n");
            localStringBuilder.append("\t\t\t\t\t<bottomPen lineWidth=\"0.5\" lineStyle=\"Solid\"/>"+"\r\n");
            localStringBuilder.append("\t\t\t\t\t<rightPen lineWidth=\"0.5\" lineStyle=\"Solid\"/>"+"\r\n");
            localStringBuilder.append("\t\t\t\t</box>"+"\r\n");
            String align= "Left";
            if(fieldname.align.equals("left")){
                align="Left";
            }else if(fieldname.align.equals("right")){
                align="Right";
            }else if(fieldname.align.equals("center")){
                align="Center";
            }
            localStringBuilder.append("\t\t\t\t<textElement textAlignment=\""+align+"\" verticalAlignment=\"Middle\">"+"\r\n");
            localStringBuilder.append("\t\t\t\t\t<font fontName=\"宋体\" pdfFontName=\"STSong-Light\" pdfEncoding=\"UniGB-UCS2-H\"/>"+"\r\n");
            localStringBuilder.append("\t\t\t\t</textElement>"+"\r\n");
            String data = "";
            if(fieldname.useNewBigDecimal && !fieldname.NumberType.equals(MyFields.numberTypeNone)){
                if(fieldname.NumberType.equals(MyFields.numberTypePercent)){
                    data = "(null!=$F{"+fieldname.name+"}&&$F{"+fieldname.name+"}.length()>0)?"+"new java.math.BigDecimal($F{"+fieldname.name+"}.replaceAll( \",\", \"\" ).replaceAll( \"%\", \"\" )).divide(new BigDecimal(100))"+":\"\"";
                }else{
                    data = "(null!=$F{"+fieldname.name+"}&&$F{"+fieldname.name+"}.length()>0)?"+"new java.math.BigDecimal($F{"+fieldname.name+"}.replaceAll( \",\", \"\" ))"+":\"\"";
                }
            }else{
                data = "$F{"+fieldname.name+"}";
            }
            localStringBuilder.append("\t\t\t\t<textFieldExpression><![CDATA["+data+"]]></textFieldExpression>"+"\r\n");
            if(fieldname.NumberType.equals(MyFields.numberTypeNone)){

            }else if(fieldname.decimalPlaces != 0 || fieldname.maxdecimalPlaces == 0){

            }else{
                boolean ispercent = false;
                if(fieldname.NumberType.equals(MyFields.numberTypePercent)){
                    ispercent = true;
                }else{
                    ispercent = false;
                }
                String judge = "null!=$F{"+fieldname.name+"}&&$F{"+fieldname.name+"}.length()>0&&$F{"+fieldname.name+"}.contains(\".\")";
                if(fieldname.useNewBigDecimal){
                    judge = "null!=$F{"+fieldname.name+"}&&$F{"+fieldname.name+"}.length()>0&&$F{"+fieldname.name+"}.contains(\".\")";
                }else{
                    judge = "null!=$F{"+fieldname.name+"}&&$F{"+fieldname.name+"}.signum() != 0&&$F{"+fieldname.name+"}.scale() > 0&&$F{"+fieldname.name+"}.stripTrailingZeros().scale() > 0";
                }
                String patternexpr="("+judge+")?"+"\""+PatternControlUtil.getPattern(1, fieldname.maxdecimalPlaces, ispercent, true)+"\""+":\""+PatternControlUtil.getPattern(fieldname.decimalPlaces, fieldname.maxdecimalPlaces, ispercent, true)+"\"";
                localStringBuilder.append("                <patternExpression><![CDATA["+patternexpr+"]]></patternExpression>"+"\r\n");

            }
            localStringBuilder.append("\t\t\t</textField>"+"\r\n");
        }
//        localStringBuilder.append("         <textField pattern=\"#,##0;-#,##0\" isBlankWhenNull=\"true\">"+"\r\n");
//        localStringBuilder.append("             <reportElement key=\"textField\" x=\"411\" y=\"0\" width=\"150\" height=\"20\" uuid=\""+UUID.randomUUID().toString()+"\"/>"+"\r\n");
//        localStringBuilder.append("             <box>"+"\r\n");
//        localStringBuilder.append("                 <pen lineWidth=\"0.5\" lineStyle=\"Solid\"/>"+"\r\n");
//        localStringBuilder.append("                 <topPen lineWidth=\"0.5\" lineStyle=\"Solid\"/>"+"\r\n");
//        localStringBuilder.append("                 <leftPen lineWidth=\"0.5\" lineStyle=\"Solid\"/>"+"\r\n");
//        localStringBuilder.append("                 <bottomPen lineWidth=\"0.5\" lineStyle=\"Solid\"/>"+"\r\n");
//        localStringBuilder.append("                 <rightPen lineWidth=\"0.5\" lineStyle=\"Solid\"/>"+"\r\n");
//        localStringBuilder.append("             </box>"+"\r\n");
//        localStringBuilder.append("             <textElement textAlignment=\"Right\" verticalAlignment=\"Middle\">"+"\r\n");
//        localStringBuilder.append("                 <font fontName=\"宋体\" pdfFontName=\"STSong-Light\" pdfEncoding=\"UniGB-UCS2-H\"/>"+"\r\n");
//        localStringBuilder.append("             </textElement>"+"\r\n");
//        localStringBuilder.append("             <textFieldExpression><![CDATA[(null!=$F{todayLong}&&$F{todayLong}.length()>0)?new java.math.BigDecimal($F{todayLong}.replaceAll( \",\", \"\" )):\"\"]]></textFieldExpression>"+"\r\n");
//        localStringBuilder.append("         </textField>"+"\r\n");
//
//        localStringBuilder.append("         <textField isBlankWhenNull=\"true\">"+"\r\n");
//        localStringBuilder.append("             <reportElement key=\"textField\" x=\"498\" y=\"0\" width=\"150\" height=\"20\" uuid=\""+UUID.randomUUID().toString()+"\"/>"+"\r\n");
//        localStringBuilder.append("             <box>"+"\r\n");
//        localStringBuilder.append("                 <pen lineWidth=\"0.5\" lineStyle=\"Solid\"/>"+"\r\n");
//        localStringBuilder.append("                 <topPen lineWidth=\"0.5\" lineStyle=\"Solid\"/>"+"\r\n");
//        localStringBuilder.append("                 <leftPen lineWidth=\"0.5\" lineStyle=\"Solid\"/>"+"\r\n");
//        localStringBuilder.append("                 <bottomPen lineWidth=\"0.5\" lineStyle=\"Solid\"/>"+"\r\n");
//        localStringBuilder.append("                 <rightPen lineWidth=\"0.5\" lineStyle=\"Solid\"/>"+"\r\n");
//        localStringBuilder.append("             </box>"+"\r\n");
//        localStringBuilder.append("             <textElement textAlignment=\"Right\" verticalAlignment=\"Middle\">"+"\r\n");
//        localStringBuilder.append("                 <font fontName=\"宋体\" pdfFontName=\"STSong-Light\" pdfEncoding=\"UniGB-UCS2-H\"/>"+"\r\n");
//        localStringBuilder.append("             </textElement>"+"\r\n");
//        localStringBuilder.append("             <textFieldExpression><![CDATA[(null!=$F{amount}&&$F{amount}.length()>0)?new java.math.BigDecimal($F{amount}.replaceAll( \",\", \"\" )):\"\"]]></textFieldExpression>"+"\r\n");
//        localStringBuilder.append("                <patternExpression><![CDATA[(null!=$F{amount}&&$F{amount}.length()>0&&$F{amount}.contains(\".\"))?\"#,##0.0#####;-#,##0.0#####\":\"#,##0;-#,##0\"]]></patternExpression>"+"\r\n");
//        localStringBuilder.append("         </textField>"+"\r\n");
//
//
//        localStringBuilder.append("         <textField isBlankWhenNull=\"true\">"+"\r\n");
//        localStringBuilder.append("             <reportElement key=\"textField\" x=\"1038\" y=\"0\" width=\"106\" height=\"20\" uuid=\""+UUID.randomUUID().toString()+"\"/>"+"\r\n");
//        localStringBuilder.append("             <box>"+"\r\n");
//        localStringBuilder.append("                 <pen lineWidth=\"0.5\" lineStyle=\"Solid\"/>"+"\r\n");
//        localStringBuilder.append("                 <topPen lineWidth=\"0.5\" lineStyle=\"Solid\"/>"+"\r\n");
//        localStringBuilder.append("                 <leftPen lineWidth=\"0.5\" lineStyle=\"Solid\"/>"+"\r\n");
//        localStringBuilder.append("                 <bottomPen lineWidth=\"0.5\" lineStyle=\"Solid\"/>"+"\r\n");
//        localStringBuilder.append("                 <rightPen lineWidth=\"0.5\" lineStyle=\"Solid\"/>"+"\r\n");
//        localStringBuilder.append("             </box>"+"\r\n");
//        localStringBuilder.append("             <textElement textAlignment=\"Left\" verticalAlignment=\"Middle\">"+"\r\n");
//        localStringBuilder.append("                 <font fontName=\"宋体\" pdfFontName=\"STSong-Light\" pdfEncoding=\"UniGB-UCS2-H\"/>"+"\r\n");
//        localStringBuilder.append("             </textElement>"+"\r\n");
//        localStringBuilder.append("             <textFieldExpression><![CDATA[$F{traderName}]]></textFieldExpression>"+"\r\n");
//        localStringBuilder.append("         </textField>"+"\r\n");

        localStringBuilder.append("\t\t</band>"+"\r\n");
        localStringBuilder.append("\t</detail>"+"\r\n");
        localStringBuilder.append("\t<columnFooter>"+"\r\n");
        localStringBuilder.append("\t\t<band splitType=\"Stretch\"/>"+"\r\n");
        localStringBuilder.append("\t</columnFooter>"+"\r\n");
        localStringBuilder.append("\t<pageFooter>"+"\r\n");
        if(needHeaderAndFooter){
            localStringBuilder.append("\t\t<band height=\"20\" splitType=\"Stretch\">"+"\r\n");
            localStringBuilder.append("\t\t\t<textField isBlankWhenNull=\"false\">"+"\r\n");
            localStringBuilder.append("\t\t\t\t<reportElement key=\"textField\" x=\""+footer.get(0).x+"\" y=\"0\" width=\""+footer.get(0).width+"\" height=\"20\" uuid=\""+UUID.randomUUID().toString()+"\"/>"+"\r\n");
            localStringBuilder.append("\t\t\t\t<textElement textAlignment=\"Right\">"+"\r\n");
            localStringBuilder.append("\t\t\t\t\t<font fontName=\"宋体\" pdfFontName=\"STSong-Light\" pdfEncoding=\"UniGB-UCS2-H\" isPdfEmbedded=\"true\"/>"+"\r\n");
            localStringBuilder.append("\t\t\t\t</textElement>"+"\r\n");
            localStringBuilder.append("\t\t\t\t<textFieldExpression><![CDATA[\"Page \"+$V{PAGE_NUMBER}+\" of\"]]></textFieldExpression>"+"\r\n");
            localStringBuilder.append("\t\t\t</textField>"+"\r\n");
            localStringBuilder.append("\t\t\t<textField evaluationTime=\"Report\" isBlankWhenNull=\"false\">"+"\r\n");
            localStringBuilder.append("\t\t\t\t<reportElement key=\"textField\" x=\""+footer.get(1).x+"\" y=\"0\" width=\""+footer.get(1).width+"\" height=\"20\" uuid=\""+UUID.randomUUID().toString()+"\"/>"+"\r\n");
            localStringBuilder.append("\t\t\t\t<textElement>"+"\r\n");
            localStringBuilder.append("\t\t\t\t\t<font fontName=\"宋体\" pdfFontName=\"STSong-Light\" pdfEncoding=\"UniGB-UCS2-H\" isPdfEmbedded=\"true\"/>"+"\r\n");
            localStringBuilder.append("\t\t\t\t</textElement>"+"\r\n");
            localStringBuilder.append("\t\t\t\t<textFieldExpression><![CDATA[\" \" + $V{PAGE_NUMBER}]]></textFieldExpression>"+"\r\n");
            localStringBuilder.append("\t\t\t</textField>"+"\r\n");
            localStringBuilder.append("\t\t</band>"+"\r\n");
        }else{
            localStringBuilder.append("\t\t<band splitType=\"Stretch\"/>"+"\r\n");
        }
        localStringBuilder.append("\t</pageFooter>"+"\r\n");
        localStringBuilder.append("\t<summary>"+"\r\n");
        localStringBuilder.append("\t\t<band splitType=\"Stretch\"/>"+"\r\n");
        localStringBuilder.append("\t</summary>"+"\r\n");
        localStringBuilder.append("</jasperReport>"+"\r\n");

        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(printPath),"UTF-8");
        osw.write(localStringBuilder.toString());
        osw.close();
    }
}
