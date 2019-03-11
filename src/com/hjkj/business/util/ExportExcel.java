package com.hjkj.business.util;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * 导出报表的公共类,提供公共的导出报表的方法
 * @Copyright: Copyright (c) 2015
 * @Company: 
 * @author luweiwei
 * 
 */
public class ExportExcel {
	
	static boolean tempDirflag1 = true;
	static boolean tempDirflag2 = true;

	/**
	 * 根据传入的集合数据生成报表的方法
	 * 
	 * @param list
	 *            报表标题表头及数据封装的list,格式：list的第�?条数据是标题，String类型；第二条数据是表头列表，List类型；从第三条开始是数据列表
	 *            ，List类型
	 * @param filename
	 *            报表excel的文件名，不�?要扩展名
	 * @param response
	 *            HttpServletResponse
	 * @return 1表示成功 0表示失败
	 * @throws IOException
	 * @Create luweiwei 2015-08-18
	 * @change 
	 */
	@SuppressWarnings("unchecked")
	public static int createReport(List<Object> list, String filename,
			HttpServletResponse response) throws IOException {

		// 数据list至少包括标题和表�?
		if (list.size() < 2) {
			return 0;
		}

		// 组装excel
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();

		// 设置Excel标题字体和样�?
		HSSFFont headFont = workbook.createFont();
		headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headFont.setFontHeightInPoints((short) 20);
		HSSFCellStyle headCellStyle = workbook.createCellStyle();
		headCellStyle.setFont(headFont);
		// headCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		// headCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		// headCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		// headCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		headCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headCellStyle.setWrapText(true);
		headCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// headCellStyle.setFillForegroundColor(GREEN_COLOR);

		// 设置Excel表头的字体和样式
		HSSFFont thfont = workbook.createFont();
		thfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		HSSFCellStyle thCellStyle = workbook.createCellStyle();
		thCellStyle.setFont(thfont);
		thCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		thCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		thCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		thCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		thCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		thCellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		thCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		// 设置Excel内容的字体和样式
		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		HSSFCellStyle titleCellStyle = workbook.createCellStyle();
		titleCellStyle.setFont(font);
		titleCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		titleCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		titleCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		titleCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		titleCellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		// titleCellStyle.setFillForegroundColor(GREEN_COLOR);

		// 单元格居左样�?
		HSSFCellStyle cellLeft = null;

		// 单元格居中样�?
		HSSFCellStyle cellCenter = null;

		// 单元格居右样�?
		HSSFCellStyle cellRight = null;

		// 单元格居左样�?
		cellLeft = workbook.createCellStyle();
		cellLeft.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellLeft.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellLeft.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellLeft.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);

		// 单元格居中样�?
		cellCenter = workbook.createCellStyle();
		cellCenter.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellCenter.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellCenter.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellCenter.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		// 单元格居右样�?
		cellRight = workbook.createCellStyle();
		cellRight.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellRight.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellRight.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellRight.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);

		// 第一�?
		// 合并标题单元�?
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0,
				((List<String>) list.get(1)).size() - 1));
		HSSFRow row = sheet.createRow(0);
		row.setHeightInPoints(40);
		HSSFCell cell = null;
		cell = row.createCell(0);
		cell.setCellStyle(headCellStyle);
		cell.setCellValue((String) (list.get(0)));

		// 设置表头单元�?
		List<String> tmpList = null;
		tmpList = (List<String>) list.get(1);
		int rownum = sheet.getLastRowNum();
		row = sheet.createRow(rownum + 1);
		for (int i = 0; i < tmpList.size(); i++) {
			sheet.setColumnWidth(i, 6000);
			cell = row.createCell(i);
			cell.setCellStyle(thCellStyle);
			cell.setCellValue(tmpList.get(i));
		}

		// 循环设置数据的单元格
		for (int i = 2; i < list.size(); i++) {
			tmpList = (List<String>) list.get(i);
			rownum = sheet.getLastRowNum();
			row = sheet.createRow(rownum + 1);
			for (int j = 0; j < tmpList.size(); j++) {
				cell = row.createCell(j);
				cell.setCellStyle(titleCellStyle);
				cell.setCellValue(tmpList.get(j));
			}
		}
		response.setHeader("Content-disposition", "attachment; filename="
				+ URLEncoder.encode(filename, "utf-8") + ".xls");
		response.setContentType("application/msexcel;charset=utf-8");
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			out.flush();
			workbook.write(out);
		} finally {
			try {
				if(out!=null){
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return 1;
	}
}
