package AutoGenerateJasperForMemberV1_6;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * 导出前置处理
 * @author ouan
 *
 */
public interface BeforeExport {
	/**
	 * 返回-1使用反射生产表头
	 * 返回1以上表明记录开始的行数偏移
	 * @param sheet
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int writeExcelHeader(Sheet sheet, HSSFWorkbook wb, Object param) throws Exception;
}
