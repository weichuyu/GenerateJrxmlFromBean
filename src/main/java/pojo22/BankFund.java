package pojo22;
import java.math.BigDecimal;

import cn.com.sge.gems.ods.dao.util.ExcelFiledResources;
import cn.com.sge.gems.ods.dao.util.ExcelHeaderResources;

/**
 * 清算行资金调拨表查询
 *
 * @author HY
 * @CreateTime 2015年7月16日
 *
 */
@ExcelHeaderResources(title="清算行资金调拨表查询")
public class BankFund {
    @ExcelFiledResources(order = 1, title = "序号")
    private long num;
    @ExcelFiledResources(order = 2, title = "银行名称")
    private String bankName;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }



    public void setTransIn(BigDecimal transIn) {
        this.transIn = transIn;
    }



    public void setTransOut(BigDecimal transOut) {
        this.transOut = transOut;
    }
    @ExcelFiledResources(order = 3, title = "发生额收入",isNaN=true,mininum=0,maximum=3)
    private BigDecimal received;
    @ExcelFiledResources(order = 4, title = "发生额付出",isNaN=true,mininum=0,maximum=3)
    private BigDecimal payment;
    @ExcelFiledResources(order = 5, title = "调拨额调入",isNaN=true,mininum=0,maximum=3)
    private BigDecimal transIn;
    @ExcelFiledResources(order = 6, title = "调拨额调出",isNaN=true,mininum=0,maximum=3)
    private BigDecimal transOut;

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }





    public void setReceived(BigDecimal received) {
        this.received = received;
    }



    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "BankFund [num=" + num + ", bankName=" + bankName
                + ", received=" + received + ", payment=" + payment
                + ", transIn=" + transIn + ", transOut=" + transOut + "]";
    }

}

