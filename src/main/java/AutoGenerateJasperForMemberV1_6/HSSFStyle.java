package AutoGenerateJasperForMemberV1_6;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.WorkbookUtil;

/**
 * Excel样式设置
 *
 * @author lilei
 *
 */
public class HSSFStyle {

	/**
	 * EXCEL标题样式
	 *
	 * @param workbook
	 * @return
	 */
	public static HSSFCellStyle initHeadStyle(HSSFWorkbook workbook) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(HSSFColor.WHITE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN); // 表格顶部线条
		style.setBorderRight(HSSFCellStyle.BORDER_THIN); // 表格右部线条
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 表格底部线条
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN); // 表格左部线条
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //水平居中  

		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.BLACK.index); // 文字颜色
		font.setFontHeightInPoints((short) 10); // 文字大小
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 文字加粗

		style.setFont(font);
		return style;
	}

	public static HSSFCellStyle initStringCellStyle(HSSFWorkbook workbook) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setBorderRight(HSSFCellStyle.BORDER_THIN); // 表格右部线条
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 表格底部线条
		style.setBorderTop(HSSFCellStyle.BORDER_THIN); // 表格顶部线条
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN); // 表格左部线条
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 表格文字居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 表格文字垂直居中
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT); //水平居中  
		return style;
	}

	public static HSSFCellStyle initIntCellStyle(HSSFWorkbook workbook) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setBorderRight(HSSFCellStyle.BORDER_THIN); // 表格右部线条
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 表格底部线条
		style.setBorderTop(HSSFCellStyle.BORDER_THIN); // 表格顶部线条
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN); // 表格左部线条
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 表格文字居中
		style.setVerticalAlignment(HSSFCellStyle.ALIGN_RIGHT); // 表格文字居右
		style.setAlignment(HSSFCellStyle.ALIGN_RIGHT); //水平居右
		style.setDataFormat(workbook.createDataFormat().getFormat("#,##0"));
		return style;
	}

	public static HSSFCellStyle initDoubleCellStyle(HSSFWorkbook workbook) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setBorderRight(HSSFCellStyle.BORDER_THIN); // 表格右部线条
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 表格底部线条
		style.setBorderTop(HSSFCellStyle.BORDER_THIN); // 表格顶部线条
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN); // 表格左部线条
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 表格文字居中
		style.setVerticalAlignment(HSSFCellStyle.ALIGN_RIGHT); // 表格文字居右
		style.setAlignment(HSSFCellStyle.ALIGN_RIGHT); //水平居右
		return style;
	}
}
