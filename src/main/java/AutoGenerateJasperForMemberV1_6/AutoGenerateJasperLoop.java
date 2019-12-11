package AutoGenerateJasperForMemberV1_6;

import java.io.File;
import java.net.URL;
import java.util.*;

public class AutoGenerateJasperLoop {
    public static String exportPath = "";
    public static String exportPackageName = "pojo";
    /**
     * TODO 添加方法注释
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // TODO 读取配置文件 2019-12-11
        ResourceBundle resource = ResourceBundle.getBundle("config");
        exportPath = resource.getString("config.output.path");
        exportPackageName = resource.getString("config.output.package");

        // TODO 自动生成方法存根
        HashMap<String,Integer> keymap= WidthConfigUtil.getWidthFromFolderAll_mock();
        ArrayList<String> fileNames = new ArrayList<String>();
        ArrayList<String> ClassNames = new ArrayList<String>();
        ArrayList<String> titles = new ArrayList<String>();

        Set<Object> result = getObjectsInPackage(exportPackageName);
        for(Object ob:result){
            System.out.println(ob.getClass().getName());
            ClassNames.add(ob.getClass().getName());
        }

//        ClassNames.add("formBean.BullionDetailForPDF");
        for (int i = 0; i < ClassNames.size(); i++) {
            String ClassName = ClassNames.get(i);
            try {
                AutoGenerateJasper.GenerateJasper(ClassName, keymap);
            }
            catch (Exception e) {
                System.out.println("" + "|" + ClassName + "|" + "");
                e.printStackTrace();
            }
        }
        System.out.println("ALL FIN");
    }
    /*
     * 根据包名来获取此包下所有的类名及其实例
     * @param packName
     * @return
     */
    public static Set<Object> getObjectsInPackage(String packName){
        Set<Object> objs = new HashSet<Object>();
        String packageName = packName;
        String packageDirName = packageName.replace(".", "/");
        Enumeration<URL> dirs = null;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            //迭代此 Enumeration
            while(dirs.hasMoreElements()){
                URL url = dirs.nextElement();
                File file = new File(url.getFile().replace("%20", " "));
                //把此目录下的所有文件列出
                String[] classes = file.list();
                //循环此数组，并把.class去掉
                for(String className : classes){
                    className = className.substring(0,className.length()-6);
                    //拼接上包名，变成全限定名
                    String qName = packageName+"."+className;
                    //如有需要，把每个类生实一个实例
                    Object obj = Class.forName(qName).newInstance();
                    //添加到集合中
                    objs.add(obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objs;
    }
}
