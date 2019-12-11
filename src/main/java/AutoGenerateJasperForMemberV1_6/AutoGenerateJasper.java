package AutoGenerateJasperForMemberV1_6;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import cn.com.sge.gems.ods.dao.util.ExcelFiledResources;
import cn.com.sge.gems.ods.dao.util.ExcelHeaderResources;

public class AutoGenerateJasper {


    public static void GenerateJasper(String ClassName, HashMap<String,Integer> keymap) throws Exception{
        //String fileName="JR_49_QueryMemberBasicInfo";
        //MemberBasicInfo ob=new MemberBasicInfo();
        String fileName="test";
        String title="test";
        Object ob = Class.forName(ClassName).newInstance();
        ArrayList<MyFields> result = getFieldsByClass(ob.getClass(),keymap);
        if(ob.getClass().isAnnotationPresent(ExcelHeaderResources.class)){
            ExcelHeaderResources reportanno = ob.getClass().getAnnotation(ExcelHeaderResources.class);
            fileName=reportanno.title();
            title=reportanno.title();
        }
        int totalwidth=0;
        for(MyFields o : result){
            totalwidth = totalwidth+o.width;
        }
        ArrayList<MyFields> header = new ArrayList<MyFields>();
        //header.add(new MyFields(int sortno, String name, String type, String viewName, int x, int y, int height, int width));
        header.add(new MyFields(1, "", "", title, 0, 0, 50, totalwidth));
//        header.add(new MyFields(2, "", "", "new java.util.Date()", totalwidth-70, 0, 23, 70));
//        header.add(new MyFields(3, "", "", "打印日期：", totalwidth-70-120, 0, 23, 120));
//        header.add(new MyFields(4, "", "", "", 0, 0, 23, totalwidth-70-120));
        header.add(new MyFields(2, "", "", "new java.util.Date()", result.get(result.size()-1).x, 0, 23, result.get(result.size()-1).width));
        header.add(new MyFields(3, "", "", "打印日期：", result.get(result.size()-2).x, 0, 23, result.get(result.size()-2).width));
        header.add(new MyFields(4, "", "", "", 0, 0, 23, totalwidth-result.get(result.size()-2).width-result.get(result.size()-1).width));
        ArrayList<MyFields> footer = new ArrayList<MyFields>();
        footer.add(new MyFields(1, "", "", "", result.get(result.size()-2).x, 0, 23, result.get(result.size()-2).width));
        footer.add(new MyFields(1, "", "", "", result.get(result.size()-1).x, 0, 23, result.get(result.size()-1).width));
        WriteJasperXmlUtil.WriteJasperXml(header,footer,result,fileName,totalwidth,keymap,"JR_"+Class.forName(ClassName).getSimpleName());
        WriteXlsTemplateUtil.WriteExcel(header,footer,result,fileName,totalwidth,keymap,"JR_"+Class.forName(ClassName).getSimpleName());
        System.out.println(fileName+"FIN");
    }
    @SuppressWarnings("unchecked")
    public static ArrayList<MyFields> getFieldsByClass(Class< ? > c,HashMap<String,Integer> keymap) throws Exception{
//        Object o = ob;
//        Class< ? > c=ob.getClass();
//        String result = c.getSimpleName( ) + ":";
        ArrayList<MyFields> result=new ArrayList<MyFields>();
        Field[ ] fields = c.getDeclaredFields( );
        for ( Field field : fields )
        {
            try
            {
                if(field.isAnnotationPresent(ExcelFiledResources.class)){
                    ExcelFiledResources anno = field.getAnnotation(ExcelFiledResources.class);
                    MyFields e = new MyFields();
                    e.type=field.getType().getName();
                    e.name=field.getName();
//                    if(null != anno.alias() && anno.alias().length()>0){
//                        e.alias=anno.alias();
//                        if(anno.useAlias()){
//                            e.name=anno.alias();
//                        }
//                    }
                    e.viewName=anno.title();
                    e.sortno=anno.order();
//                    e.width=anno.width();
//                    e.useNewBigDecimal=anno.useNewBigDecimal();
//                    if(anno.forceFormat()!=null && anno.forceFormat().length()>0){
//                        e.type=anno.forceFormat();
//                        if(anno.percent()==false){
//                            e.NumberType=MyFields.numberTypeGroup;
//                        }else{
//                            e.NumberType=MyFields.numberTypePercent;
//                        }
//                        e.decimalPlaces=anno.decimal();
//                        e.maxdecimalPlaces=anno.maxdecimal();
//                        if(e.width==0){
//                            e.width=150;
//                        }
//                    }else{
//                        if(anno.isNumber()==true){
//                            if(anno.percent()==false){
//                                e.NumberType=MyFields.numberTypeGroup;
//                            }else{
//                                e.NumberType=MyFields.numberTypePercent;
//                            }
//                            e.decimalPlaces=anno.decimal();
//                            e.maxdecimalPlaces=anno.maxdecimal();
//                            if(e.width==0){
//                                e.width=150;
//                            }
//                        }
//                        e.type=field.getType().getName();
//                    }
                    if(anno.isNaN() == true){
                        e.decimalPlaces=anno.mininum();
                        e.maxdecimalPlaces = anno.maximum();
                        e.NumberType=MyFields.numberTypeGroup;
                        if (e.width == 0) {
                            e.width = 150;
                        }
                    }
//                    if(anno.align().equals(MyAnnotation.Align.left)){
//                        e.align="left";
//                    }else if(anno.align().equals(MyAnnotation.Align.center)){
//                        e.align="center";
//                    }else if(anno.align().equals(MyAnnotation.Align.right)){
//                        e.align="right";
//                    }else{
//                        if(e.name.equals("num")){
//                            e.align="left";
//                        }else if(field.getType().getName().equals("java.math.BigDecimal")){
//                            e.align="right";
//                        }else{
//                            e.align="left";
//                        }
//                    }
                    if(e.name.equals("num")){
                        e.align="left";
                    }else if(field.getType().getName().equals("java.math.BigDecimal")){
                        e.align="right";
                    }else{
                        e.align="left";
                    }
                    result.add(e);
                }
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }
        }
        //排序
        Collections.sort(result);
        //让总列宽趋于定植
        if(true){
            for(int i=0;i<result.size();i++){
                MyFields ob = result.get(i);
                if (ob.width == 0) {
                    int width=0;
                    if(null!=ob.alias && ob.alias.length()>0){
                        width = WidthConfigUtil.getWidthByFieldName(ob.alias, keymap);
                    }else{
                        width = WidthConfigUtil.getWidthByFieldName(ob.name, keymap);
                    }
                    ob.width = width;
                }
            }
            int targeWidth = 802;
            int currentWidth = 0;
            for(MyFields o : result){
                currentWidth = currentWidth+o.width;
            }
            for(MyFields o : result){
                o.width = o.width*targeWidth/currentWidth;
            }
        }
        for(int i=0;i<result.size();i++){
            MyFields ob = result.get(i);
            if (ob.width == 0) {
                int width=0;
                if(null!=ob.alias && ob.alias.length()>0){
                    width = WidthConfigUtil.getWidthByFieldName(ob.alias, keymap);
                }else{
                    width = WidthConfigUtil.getWidthByFieldName(ob.name, keymap);
                }
                ob.width = width;
            }
            if(i==0){
                ob.x = 0;
            }else{
                ob.x = result.get(i-1).x+result.get(i-1).width;
            }
            ob.y=0;
            ob.height=20;
        }
//        for(MyFields ob : result){
//            System.out.println(ob.name+"|||"+ob.x+"|||"+ob.width);
//        }
        //str+="<tr><td>"+list[i].num+"</td><td>"+list[i].enterprisecode+"</td><td>"
        //+list[i].companyname+"</td><td>"+list[i].juridicalperson+"</td><td>"+list[i].tel+"</td><td>"
        //+list[i].name+"</td><td>"+list[i].destroyflag+"</td></tr>";
        String str="str+=";
        int i=0;
        for(MyFields ob : result){
            if (i == 0) {
                str = str + "\"<tr><td style=\\\"text-align: left;\\\">\"+list[i]." + ob.name;
            }else{
                if(!(ob.align.equals("no")|| ob.align.equals("left"))){
                    String az="";
                    if(!ob.NumberType.equals("None")){
                        az = "after";
                    }
                    str = str + "+\"</td><td style=\\\"text-align: "+ob.align+";\\\">\"+list[i]." +az+ ob.name;
                }
//                else if(ob.type.equals("java.math.BigDecimal")){
//                    str = str + "+\"</td><td style=\\\"text-align: right;\\\">\"+list[i]." + ob.name;
//                }
                else{
                    str = str + "+\"</td><td>\"+list[i]." + ob.name;
                }
            }
            i++;
        }
        str=str+"+\"</td></tr>\";";
        System.out.println(str);
        //
        //
        {
            for(MyFields ob : result){
                System.out.println("<th>"+ob.viewName+"</th>");
            }
        }
        {
            for(MyFields ob : result){
                //System.out.println("<td th:text=\"${certainMan."+ob.name+"}\">"+ob.viewName+"</td>");
                if(!(ob.align.equals("no")|| ob.align.equals("left"))){
                    String az="";
                    if(!ob.NumberType.equals("None")){
                        az = "after";
                    }
                    System.out.println("<td name=\""+ob.name+"\" th:text=\"${vo."+az+ob.name+"}\" style=\"text-align: right;\"></td>");
                }
                else{
                    System.out.println("<td name=\""+ob.name+"\" th:text=\"${vo."+ob.name+"}\"></td>");
                }
                //System.out.println("<td name=\""+ob.name+"\" th:text=\"${vo."+ob.name+"}\"></td>");
            }
        }
        {
            for(MyFields ob : result){
                //System.out.println("<td name=\""+ob.name+"\" th:text=\"${vo."+ob.name+"}\" style=\"text-align: right;\"></td>");
                int az=0;
                String za="";
                if(!ob.NumberType.equals("None")){
                    az=1;
                    za="after";
                }
                System.out.println("<th onclick=\"sort(\'"+za+ob.name+"\',"+az+")\">"+ob.viewName+"</th>");
            }
        }
        {
            for(MyFields ob : result){
                //System.out.println("<td name=\""+ob.name+"\" th:text=\"${vo."+ob.name+"}\" style=\"text-align: right;\"></td>");
                if(!ob.NumberType.equals("None")){
                    System.out.println("\tpublic String getAfter"+ob.name+"() {");
                    System.out.println("\t\treturn BaseFormatUtil.numberFormat("+ob.name+", "+ob.decimalPlaces+","+ob.maxdecimalPlaces+");");
                    System.out.println("\t}");
                }

            }
        }
        return result;
    }

}
