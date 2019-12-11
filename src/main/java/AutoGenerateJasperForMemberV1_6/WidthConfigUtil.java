package AutoGenerateJasperForMemberV1_6;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class WidthConfigUtil {
    public static int getWidthByFieldName(String fieldname,HashMap<String,Integer> keymap) throws Exception{
        String upname = fieldname.toUpperCase();
        Integer result;
        //HashMap<String,Integer> keymap = new HashMap<String,Integer>();

//        keymap.put(("num").toUpperCase(),34);
//        keymap.put(("certainManId").toUpperCase(),95);
//        keymap.put(("certainManName").toUpperCase(),97);
//        keymap.put(("companyName").toUpperCase(),214);
//        keymap.put(("memberName").toUpperCase(),185);
//        keymap.put(("certificateType").toUpperCase(),70);
//        keymap.put(("certificateNo").toUpperCase(),115);
//        keymap.put(("tel").toUpperCase(),80);
//        keymap.put(("fax").toUpperCase(),80);
//        keymap.put(("email").toUpperCase(),120);
//        keymap.put(("state").toUpperCase(),70);
//        //
//        //
//        //
        keymap.put(("matchdate").toUpperCase(),106);
        keymap.put(("matchno").toUpperCase(),106);
        keymap.put(("Orderno").toUpperCase(),89);

        keymap.put(("matchno").toUpperCase(),106);
        keymap.put(("Orderno").toUpperCase(),89);

        keymap.put(("sMemberabbr").toUpperCase(),150);
        keymap.put(("sClientabbr").toUpperCase(),150);
        keymap.put(("bMemberabbr").toUpperCase(),150);
        keymap.put(("bClientabbr").toUpperCase(),150);
        keymap.put(("memberabbr").toUpperCase(),150);
        keymap.put(("clientabbr").toUpperCase(),150);
        keymap.put(("abbr").toUpperCase(),150);

        keymap.put(("address").toUpperCase(),300);
        keymap.put(("tel").toUpperCase(),120);
        keymap.put(("fax").toUpperCase(),120);
        keymap.put(("companyname").toUpperCase(),220);
        keymap.put(("certificateno").toUpperCase(),115);

        keymap.put(("depositsheetno").toUpperCase(),115);
        keymap.put(("pickupsheetno").toUpperCase(),115);
        keymap.put(("drawsheetno").toUpperCase(),115);

        keymap.put(("accountcode").toUpperCase(),106);
        keymap.put(("depositaccumulate").toUpperCase(),120);
        keymap.put(("loanaccumulate").toUpperCase(),120);
        keymap.put(("loanrate").toUpperCase(),120);

        keymap.put(("name").toUpperCase(),220);

        keymap.put(("turnover").toUpperCase(),220);
//        keymap.put(("eabbr").toUpperCase(),185);
//        keymap.put(("abbr").toUpperCase(),185);
        //
        //
        result=keymap.get(upname);
        Set<String> key = keymap.keySet();
        if(result == null){
//            if(upname.contains("NAME")){
//                result = 185;
//            }else if(upname.contains("ID")){
//                result = 95;
//            }else{
//                result = 70;
//            }
            result=185;
            for (Iterator it = key.iterator(); it.hasNext();) {
                String s = (String) it.next();
                if(upname.contains(s)){
                    result=keymap.get(s);
                }
            }
        }
        return result;
    }
    public static HashMap<String,Integer> getWidthFromFolderAll() throws Exception{
        String workpath="D:\\Tutorial\\Jasper\\OrigionWidth\\";
        //String xmlPath = "D:\\Tutorial\\Jasper\\JR_33_QueryCertainMan.jrxml";
        HashMap<String,Integer> keymap = new HashMap<String,Integer>();
        File file=new File(workpath);
        String test[];
        test=file.list();
        for (int i = 0; i < test.length; i++) {
            //System.out.println(test[i]);
            String xmlPath=workpath+test[i];
            getFieldWidth(xmlPath,keymap);
            //System.out.println(xmlPath + " FIN");
        }
//        Set<String> key = keymap.keySet();
//        for (Iterator it = key.iterator(); it.hasNext();) {
//            String s = (String) it.next();
//            System.out.println(s+"||"+keymap.get(s));
//        }
        return keymap;
    }
    public static void getFieldWidth(String xmlPath,HashMap<String,Integer> keymap) throws Exception{
        SAXReader reader = new SAXReader(); //User.hbm.xml表示你要解析的xml文档
        Document doc = reader.read(new File(xmlPath));
        Element rootElt = doc.getRootElement();
        //System.out.println("根节点：" + rootElt.getName());
        {
            Iterator iterInfo = rootElt.elementIterator("detail");
            while (iterInfo.hasNext()){
                Element recordtitle = (Element) iterInfo.next();
                Iterator iterband = recordtitle.elementIterator("band");
                while (iterband.hasNext()){
                    Element recordband = (Element) iterband.next();
                    Iterator iterstatictext2 = recordband.elementIterator("textField");
                    while (iterstatictext2.hasNext()){
                        Element recordstatictext2 = (Element) iterstatictext2.next();
                        Iterator iterstatictext3 = recordstatictext2.elementIterator("reportElement");
                        String width="";
                        while (iterstatictext3.hasNext()){
                            Element recordstatictext3 = (Element) iterstatictext3.next();
                            width=recordstatictext3.attributeValue("width");
                        }
                        Iterator iterstatictext31 = recordstatictext2.elementIterator("textFieldExpression");
                        String field="";
                        while (iterstatictext31.hasNext()){
                            Element recordstatictext3 = (Element) iterstatictext31.next();
                            field=recordstatictext3.getTextTrim();
                        }
                        field=field.substring(3);
                        field=field.substring(0, field.length()-1);
                        //System.out.println(field+"||"+width);
                        Integer result=keymap.get(field.toUpperCase());
                        if(result == null){
                            keymap.put(field.toUpperCase(), Integer.parseInt(width));
                        }else if(Integer.parseInt(width)>result){
                            keymap.put(field.toUpperCase(), Integer.parseInt(width));
                        }
                    }

                }
            }
        }
    }

    public static HashMap<String,Integer> getWidthFromFolderAll_mock() throws Exception{
        HashMap<String,Integer> keymap = new HashMap<String,Integer>();

        keymap.put(("matchdate").toUpperCase(),106);
        keymap.put(("matchno").toUpperCase(),106);
        keymap.put(("Orderno").toUpperCase(),89);

        keymap.put(("matchno").toUpperCase(),106);
        keymap.put(("Orderno").toUpperCase(),89);

        keymap.put(("sMemberabbr").toUpperCase(),150);
        keymap.put(("sClientabbr").toUpperCase(),150);
        keymap.put(("bMemberabbr").toUpperCase(),150);
        keymap.put(("bClientabbr").toUpperCase(),150);
        keymap.put(("memberabbr").toUpperCase(),150);
        keymap.put(("clientabbr").toUpperCase(),150);
        keymap.put(("abbr").toUpperCase(),150);

        keymap.put(("address").toUpperCase(),300);
        keymap.put(("tel").toUpperCase(),120);
        keymap.put(("fax").toUpperCase(),120);
        keymap.put(("companyname").toUpperCase(),220);
        keymap.put(("certificateno").toUpperCase(),115);

        keymap.put(("depositsheetno").toUpperCase(),115);
        keymap.put(("pickupsheetno").toUpperCase(),115);
        keymap.put(("drawsheetno").toUpperCase(),115);

        keymap.put(("accountcode").toUpperCase(),106);
        keymap.put(("depositaccumulate").toUpperCase(),120);
        keymap.put(("loanaccumulate").toUpperCase(),120);
        keymap.put(("loanrate").toUpperCase(),120);

        keymap.put(("name").toUpperCase(),220);

        keymap.put(("turnover").toUpperCase(),220);
        return keymap;
    }
}
