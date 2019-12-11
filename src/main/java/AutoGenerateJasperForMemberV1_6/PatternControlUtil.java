package AutoGenerateJasperForMemberV1_6;

public class PatternControlUtil {
    public static String getPattern(int decimalPlaces,boolean isPercentage,boolean isGroup){
//        String integer="";
//        String decimal="0.";
//        if(isGroup==true){
//            integer="#,##";
//        }else{
//            integer="###";
//        }
//        if(decimalPlaces>0){
//            
//            for(int i=0;i<decimalPlaces;i++){
//                decimal+="0";
//            }
//        }else{
//            decimal="0";
//        }
//        if(isPercentage){
//            decimal+=" %";
//        }
//        String total=integer+decimal+";-"+integer+decimal;
//        return total;
        return getPattern(decimalPlaces,decimalPlaces,isPercentage,isGroup);
    }
    public static String getPattern(int decimalPlaces,int maxdecimalPlaces,boolean isPercentage,boolean isGroup){
//        if(decimalPlaces == 0 && maxdecimalPlaces!=0){
//            decimalPlaces=1;
//        }
        String integer="";
        String decimal="0.";
        if(isGroup==true){
            integer="#,##";
        }else{
            integer="###";
        }
        if(decimalPlaces>0){

            for(int i=0;i<decimalPlaces;i++){
                decimal+="0";
            }
            for(int i=0;i<maxdecimalPlaces-decimalPlaces;i++){
                decimal+="#";
            }
        }else{
            decimal="0";
        }
        if(isPercentage){
            decimal+=" %";
        }
        String total=integer+decimal+";-"+integer+decimal;
        return total;
    }
}
