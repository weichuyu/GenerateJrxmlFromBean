package AutoGenerateJasperForMemberV1_6;

public class MyFields implements Comparable {
    public static String numberTypeNone="None";
    public static String numberTypeGroup="Group";
    public static String numberTypePercent="Percent";
    public String alias;
    public int sortno;
    public String name;
    public String type;
    public String viewName;
    public String align;
    public int x;
    public int y = 0;
    public int height = 0;
    public int width;
    public String NumberType = MyFields.numberTypeNone;
    public int decimalPlaces = 2;
    public int maxdecimalPlaces = 6;
    public boolean useNewBigDecimal = false;
    @Override
    public int compareTo(Object o) {
        // TODO 自动生成方法存根
        if(this.sortno>((MyFields)o).sortno){
            return 1;
        }
        return 0;
    }
    public MyFields(int sortno, String name, String type, String viewName, int x, int y, int height, int width) {
        super();
        this.sortno = sortno;
        this.name = name;
        this.type = type;
        this.viewName = viewName;
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }
    public MyFields() {
        super();
    }
}
