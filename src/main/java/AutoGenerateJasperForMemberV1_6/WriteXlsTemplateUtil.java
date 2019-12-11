package AutoGenerateJasperForMemberV1_6;


import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class WriteXlsTemplateUtil {
	public static int margin = 0;
	public static boolean needHeaderAndFooter = true;
	public static void WriteExcel(ArrayList<MyFields> header,ArrayList<MyFields> footer,ArrayList<MyFields> fields,String fileName,int totalwidth,HashMap<String,Integer> keymap,String xmlname) throws Exception{
		String workpath="E:\\Tutorial\\Jasper\\";
		workpath = AutoGenerateJasperLoop.exportPath;
		//String xmlPath = "D:\\Tutorial\\Jasper\\JR_33_QueryCertainMan.jrxml";
		String printPath = workpath+xmlname+".xls";
		//printXML(xmlPath,printPath,header,footer,fields,totalwidth,fileName);
		printExcel(printPath,header,footer,fields,totalwidth,fileName);
		System.out.println("FIN");
	}
	/**
	 * @param printPath
	 * @param header
	 * @param footer
	 * @param fields
	 * @param totalwidth
	 * @param fileName
	 * @throws Exception
	 */
	public static void printExcel(String printPath,ArrayList<MyFields> header,ArrayList<MyFields> footer,ArrayList<MyFields> fields,int totalwidth,String fileName)throws Exception{
		ArrayList<CellData> rowData = new ArrayList<CellData>();
		Integer currentWriteNum = 0;
		//
		HSSFWorkbook wb = new HSSFWorkbook();

		//
		HSSFCellStyle headStyle = HSSFStyle.initHeadStyle(wb); // 初始化表头样式
		HSSFCellStyle StringCellStyle = HSSFStyle.initStringCellStyle(wb); // 初始化字符内容样式
		HSSFCellStyle numberDoubleStyle = HSSFStyle.initDoubleCellStyle(wb); // 初始化Double数字内容样式
		HSSFCellStyle numberIntStyle = HSSFStyle.initIntCellStyle(wb); // 初始化整型数字内容样式
		//ConfigStyle
		HSSFCellStyle configStyle = wb.createCellStyle();
		HSSFFont font = wb.createFont();
		font.setFontName("ＭＳ Ｐゴシック");
		font.setFontHeightInPoints((short) 11);
		configStyle.setFont(font);

		HSSFCellStyle configStyle2 = wb.createCellStyle();
		HSSFFont font2 = wb.createFont();
		font2.setFontName("宋体");
		font2.setFontHeightInPoints((short) 12);
		configStyle2.setFont(font2);
		//targetStyle
		HSSFCellStyle targetStyle = wb.createCellStyle();
		targetStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont font3 = wb.createFont();
		font3.setFontName("微软雅黑");
		font3.setFontHeightInPoints((short) 10);
		targetStyle.setFont(font3);
		targetStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
		targetStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		targetStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		targetStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框


		HSSFCellStyle targetStyle2 = wb.createCellStyle();
		targetStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont font4 = wb.createFont();
		font4.setFontName("宋体");
		font4.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
		font4.setFontHeightInPoints((short) 11);
		targetStyle2.setFont(font4);
		//写config

		HSSFSheet sheet1 = wb.createSheet("CONFIG");
		sheet1.setDefaultColumnWidth(15);
		sheet1.setColumnWidth(0, 30*256);
		sheet1.setColumnWidth(1, 30*256);
		sheet1.setColumnWidth(2, 17*256);
		sheet1.setColumnWidth(3, 11*256);
		sheet1.setColumnWidth(4, 9*256);
		sheet1.setColumnWidth(5, 9*256);
		sheet1.setColumnWidth(6, 9*256);

		rowData.add(new CellData("SHEET",false));
		rowData.add(new CellData("BEGIN",false));
		rowData.add(new CellData("Target",false));
		writeRow(sheet1,rowData,configStyle,currentWriteNum);
		currentWriteNum++;
		rowData.add(new CellData("MULTI_VALUE",false));
		rowData.add(new CellData("BEGIN",false));
		rowData.add(new CellData("detailInfo",false));
		rowData.add(new CellData("3",true));
		rowData.add(new CellData("1",true));
		rowData.add(new CellData("3",true));
		rowData.add(new CellData(fields.size()+"",true));
		writeRow(sheet1,rowData,configStyle,currentWriteNum);
		currentWriteNum++;
		for(MyFields field:fields){
			rowData.add(new CellData("SINGLE_VALUE",false));
			rowData.add(new CellData(field.name,false));
			rowData.add(new CellData("3",true));
			rowData.add(new CellData(field.sortno+"",true));
			rowData.add(new CellData("LINE",false));
			if(!field.NumberType.equals(MyFields.numberTypeNone)){
				rowData.add(new CellData("NUMBER",false));
			}
			writeRow(sheet1,rowData,configStyle,currentWriteNum);
			currentWriteNum++;
		}


		rowData.add(new CellData("MULTI_VALUE",false));
		rowData.add(new CellData("END",false));
		writeRow(sheet1,rowData,configStyle,currentWriteNum);
		currentWriteNum++;
		rowData.add(new CellData("SHEET",false));
		rowData.add(new CellData("END",false));
		writeRow(sheet1,rowData,configStyle2,currentWriteNum);
		currentWriteNum++;
		//写
		HSSFSheet sheet2 = wb.createSheet("Target");
		//设列宽
		sheet1.setDefaultColumnWidth(15);
		for(int i=0;i<fields.size();i++){
			MyFields field = fields.get(i);
			sheet2.setColumnWidth(i, (field.width/4)*256);
		}
		//标题
		currentWriteNum = 0;
		String title = header.get(0).viewName;


		rowData.add(new CellData(title,false));
		writeRow(sheet2,rowData,targetStyle2,currentWriteNum);
		currentWriteNum++;
		for(MyFields field:fields){
			rowData.add(new CellData(field.viewName,false));
		}
		writeRow(sheet2,rowData,targetStyle,currentWriteNum);
		currentWriteNum++;
		for(MyFields field:fields){
			rowData.add(new CellData(null,false));
		}
		writeRow(sheet2,rowData,targetStyle,currentWriteNum);
		currentWriteNum++;

		sheet2.addMergedRegion(new CellRangeAddress(0,0,0,fields.size()-1));
		//写文件
		OutputStream osw = new FileOutputStream(printPath);

		wb.write(osw);
		osw.flush();
		osw.close();
	}
	private static void writeRow(HSSFSheet sheet,List<CellData> rowData,HSSFCellStyle headStyle,Integer currentWriteNum){
		Row r = sheet.createRow(currentWriteNum);
		for (int i = 0; i < rowData.size(); i++) {
			HSSFCell cell = (HSSFCell) r.createCell(i);
			if(headStyle!=null){
				cell.setCellStyle(headStyle);
			}
			if(rowData.get(i).isNumeric()){
				cell.setCellValue(Double.parseDouble(rowData.get(i).getText()));
				cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			}else{
				cell.setCellValue(rowData.get(i).getText());
			}
		}
		rowData.clear();
	}
}
