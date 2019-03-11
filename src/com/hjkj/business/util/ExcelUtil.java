package com.hjkj.business.util;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.multipart.commons.CommonsMultipartFile;



public class ExcelUtil {
	
	              
	    /* 
	     * 导出数据 
	     * */  
	    public static void export(HttpServletResponse response, String sheetName,String[] rowName,  List<Object[]>  dataList) throws Exception{  
	    	try{  
	            HSSFWorkbook workbook = new HSSFWorkbook();                     // 创建工作簿对�?  
	            HSSFSheet sheet = workbook.createSheet(sheetName);                  // 创建工作�?  
	              
	            // 产生表格标题�?  
	            HSSFRow rowm = sheet.createRow(0);  
	            HSSFCell cellTiltle = rowm.createCell(0);  
	              
	            //sheet样式定义【getColumnTopStyle()/getStyle()均为自定义方�? - 在下�?  - 可扩展�??  
	            HSSFCellStyle columnTopStyle = getColumnTopStyle(workbook);//获取列头样式对象  
	            HSSFCellStyle style = getStyle(workbook);                  //单元格样式对�?  
	              
	            HSSFDataFormat format = workbook.createDataFormat(); 
	            style.setDataFormat(format.getFormat("@")); 
	            
	            sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, (rowName.length-1)));    
	            cellTiltle.setCellStyle(columnTopStyle);  
	              
	            // 定义�?�?列数  
	            int columnNum = rowName.length;  
	            HSSFRow rowRowName = sheet.createRow(2);                // 在索�?2的位置创建行(�?顶端的行�?始的第二�?)  
	              
	            // 将列头设置到sheet的单元格�?  
	            for(int n=0;n<columnNum;n++){  
	                HSSFCell  cellRowName = rowRowName.createCell(n);               //创建列头对应个数的单元格  
	                cellRowName.setCellType(HSSFCell.CELL_TYPE_STRING);             //设置列头单元格的数据类型  
	                HSSFRichTextString text = new HSSFRichTextString(rowName[n]);  
	                cellRowName.setCellValue(text);                                 //设置列头单元格的�?  
	                cellRowName.setCellStyle(columnTopStyle);                       //设置列头单元格样�?  
	            }  
	              
	            //将查询出的数据设置到sheet对应的单元格�?  
	            for(int i=0;i<dataList.size();i++){  
	                  
	                Object[] obj = dataList.get(i);//遍历每个对象  
	                HSSFRow row = sheet.createRow(i+3);//创建�?�?的行�?  
	                  
	                for(int j=0; j<obj.length; j++){  
	                    HSSFCell  cell = null;   //设置单元格的数据类型  
	                    if(j == 0){  
	                        cell = row.createCell(j,HSSFCell.CELL_TYPE_NUMERIC);  
	                        cell.setCellValue(i+1);   
	                    }else{  
	                        cell = row.createCell(j,HSSFCell.CELL_TYPE_STRING);  
	                        if(!"".equals(obj[j]) && obj[j] != null){  
	                            cell.setCellValue(obj[j].toString());                       //设置单元格的�?  
	                        }  
	                    }  
	                    cell.setCellStyle(style);                                   //设置单元格样�?  
	                }  
	            }  
	            //让列宽随�?导出的列长自动�?�应  
	          /* for (int colNum = 0; colNum < columnNum; colNum++) {  
	                int columnWidth = sheet.getColumnWidth(colNum) / 256;  
	                for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {  
	                    HSSFRow currentRow;  
	                    //当前行未被使用过  
	                    if (sheet.getRow(rowNum) == null) {  
	                        currentRow = sheet.createRow(rowNum);  
	                    } else {  
	                        currentRow = sheet.getRow(rowNum);  
	                    }  
	                    if (currentRow.getCell(colNum) != null) {  
	                        HSSFCell currentCell = currentRow.getCell(colNum);  
	                        if (currentCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {  
	                            int length = currentCell.getStringCellValue().getBytes().length;  
	                            if (columnWidth < length) {  
	                                columnWidth = length;  
	                            }  
	                        }  
	                    }  
	                }  
	                if(colNum == 0){  
	                    sheet.setColumnWidth(colNum, (columnWidth-2) * 256);  
	                }else{  
	                    sheet.setColumnWidth(colNum, (columnWidth+4) * 256);  
	                }  
	            }  */
	              
	            if(workbook !=null){  
	                try  
	                {  
	                    String fileName = "Excel-" + String.valueOf(System.currentTimeMillis()).substring(4, 13) + ".xls";  
	                    String headStr = "attachment; filename=\"" + fileName + "\"";  
	                    response.setContentType("APPLICATION/OCTET-STREAM");  
	                    response.setHeader("Content-Disposition", headStr);  
	                    OutputStream out = response.getOutputStream();  
	                    workbook.write(out);  
	                }  
	                catch (IOException e)  
	                {  
	                    e.printStackTrace();  
	                }  
	            }  
	  
	        }catch(Exception e){  
	            e.printStackTrace();  
	        }
	          
	    }  
	      
	    /*  
	     * 列头单元格样�? 
	     */      
	    private static HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {  
	          
	          // 设置字体  
	          HSSFFont font = workbook.createFont();  
	          //设置字体大小  
	          font.setFontHeightInPoints((short)11);  
	          //字体加粗  
	          font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
	          //设置字体名字   
	          font.setFontName("Courier New");  
	          //设置样式;   
	          HSSFCellStyle style = workbook.createCellStyle();  
	          //设置底边�?;   
	          style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
	          //设置底边框颜�?;    
	          style.setBottomBorderColor(HSSFColor.BLACK.index);  
	          //设置左边�?;     
	          style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
	          //设置左边框颜�?;   
	          style.setLeftBorderColor(HSSFColor.BLACK.index);  
	          //设置右边�?;   
	          style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
	          //设置右边框颜�?;   
	          style.setRightBorderColor(HSSFColor.BLACK.index);  
	          //设置顶边�?;   
	          style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
	          //设置顶边框颜�?;    
	          style.setTopBorderColor(HSSFColor.BLACK.index);  
	          //在样式用应用设置的字�?;    
	          style.setFont(font);  
	          //设置自动换行;   
	          style.setWrapText(false);  
	          //设置水平对齐的样式为居中对齐;    
	          style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
	          //设置垂直对齐的样式为居中对齐;   
	          style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
	            
	          return style;  
	            
	    }  
	      
	    /*   
	     * 列数据信息单元格样式 
	     */    
	    private static HSSFCellStyle getStyle(HSSFWorkbook workbook) {  
	          // 设置字体  
	          HSSFFont font = workbook.createFont();  
	          //设置字体大小  
	          //font.setFontHeightInPoints((short)10);  
	          //字体加粗  
	          //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
	          //设置字体名字   
	          font.setFontName("Courier New");  
	          //设置样式;   
	          HSSFCellStyle style = workbook.createCellStyle();  
	          //设置底边�?;   
	          style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
	          //设置底边框颜�?;    
	          style.setBottomBorderColor(HSSFColor.BLACK.index);  
	          //设置左边�?;     
	          style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
	          //设置左边框颜�?;   
	          style.setLeftBorderColor(HSSFColor.BLACK.index);  
	          //设置右边�?;   
	          style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
	          //设置右边框颜�?;   
	          style.setRightBorderColor(HSSFColor.BLACK.index);  
	          //设置顶边�?;   
	          style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
	          //设置顶边框颜�?;    
	          style.setTopBorderColor(HSSFColor.BLACK.index);  
	          //在样式用应用设置的字�?;    
	          style.setFont(font);  
	          //设置自动换行;   
	          style.setWrapText(false);  
	          //设置水平对齐的样式为居中对齐;    
	          style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
	          //设置垂直对齐的样式为居中对齐;   
	          style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
	           
	          return style;  
	      
	    }  
    
	
	/**
	 * 适用于第�?行是标题行的excel，例�?
	 * 姓名   年龄  性别  身高
	 * 张三   25  �?   175
	 * 李四   22  �?   160
	 * 每一行构成一个map，key值是列标题，value是列值�?�没有�?�的单元格其value值为null
	 * 返回结果�?外层的list对应�?个excel文件，第二层的list对应�?个sheet页，第三层的map对应sheet页中的一�?
	 * @throws Exception 
	 */
	public static List<Map<String, String>> readExcelWithTitle(CommonsMultipartFile file,List<String> keyList) throws Exception{
	    
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream()); 
        //List<List<Map<String, String>>> result = new ArrayList<List<Map<String, String>>>();//对应excel文件
        
		try {
			
	        int sheetCount = workbook.getNumberOfSheets();  //Sheet的数�?  
	        //遍历每个Sheet  
	        /*for (int s = 0; s < sheetCount; s++) { */ 
	        	List<String> titles = new ArrayList<String>();//放置�?有的标题
				List<Map<String, String>> sheetList = new ArrayList<Map<String, String>>();//对应sheet�?
	        	Sheet sheet = workbook.getSheetAt(0);  
	        	int rowCount = sheet.getPhysicalNumberOfRows(); //获取总行�?  
	        	for (int j = 0; j < rowCount; j++) {//遍历�?
	        		Row row = sheet.getRow(j);
	                if (row == null) {//略过空行
	                    continue;
	                }
	                int cellSize = row.getLastCellNum();//行中有多少个单元格，也就是有多少�?
	                if (j == 0) {//第一行是标题�?
	                    for (int k = 0; k < cellSize; k++) {
	                        Cell cell = row.getCell(k);
	                        titles.add(cell.toString());
	                    }
	                } else {//其他行是数据�?
	                	Map<String,String> rowMap = new HashMap<String, String>();//对应�?个数据行
	                    for (int k = 0; k < titles.size(); k++) {
	                        Cell cell = row.getCell(k);
	                        String key = titles.get(k);
	                        String value = null;
	                       
	                        if (cell != null) {
	                        	int cellType = cell.getCellType();  
	                        	switch(cellType) {  
		                        case Cell.CELL_TYPE_STRING: //文本  
		                        	value = cell.getStringCellValue().trim();  
		                        	if(value.equals("合格")){
		                        		value = "1";
		                        	}else if(value.equals("不合�?")){
		                        		value = "2";
		                        	}else if(value.equals("未�?�试")){
		                        		value = "3";
		                        	}else if(value.equals("科目�?")){
		                        		value = "1";
		                        	}else if(value.equals("科目�?")){
		                        		value = "2";
		                        	}else if(value.equals("科目�?")){
		                        		value = "3";
		                        	}else if(value.equals("科目�?")){
		                        		value = "4";
		                        	}else if(value.equals("请假")){
		                        		value = "3";
		                        	}
		                            break;  
		                        case Cell.CELL_TYPE_NUMERIC: //数字、日�?  
		                            if(DateUtil.isCellDateFormatted(cell)) {  
		                            	value = fmt.format(cell.getDateCellValue()); //日期�?  
		                            }  
		                            else {  
		                            	value = String.valueOf(cell.getNumericCellValue()); //数字  
		                            }  
		                            break;  
		                        case Cell.CELL_TYPE_BOOLEAN: //布尔�?  
		                        	value = String.valueOf(cell.getBooleanCellValue());  
		                            break;  
		                        case Cell.CELL_TYPE_BLANK: //空白  
		                        	value = cell.getStringCellValue();  
		                            break;  
		                        case Cell.CELL_TYPE_ERROR: //错误  
		                        	value = "错误";  
		                            break;  
		                        case Cell.CELL_TYPE_FORMULA: //公式  
		                        	value = "错误";  
		                            break;  
		                        default:  
		                        	value = "错误";  
	                        	}  
	                        }
	                        rowMap.put(key, value);
	                    }
	                    sheetList.add(rowMap);
	                }
	        	}
	        	//result.add(sheetList);
	        //}
	        return sheetList;
	        
	    } finally {
	        if (workbook != null) {
	        	workbook.close();
	        }
	    }
	}
	
	
	/**
	 * 适用于第�?行是标题行的excel，例�?
	 * 姓名   年龄  性别  身高
	 * 张三   25  �?   175
	 * 李四   22  �?   160
	 * 每一行构成一个map，key值是列标题，value是列值�?�没有�?�的单元格其value值为null
	 * 返回结果�?外层的list对应�?个excel文件，第二层的list对应�?个sheet页，第三层的map对应sheet页中的一�?
	 * @throws Exception 
	 */
	public static List<List<String>> readExcel(CommonsMultipartFile file) throws Exception{
	    
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream()); 
		List<List<String>> result = new ArrayList<List<String>>();//对应sheet�?
		try {
			
	        int sheetCount = workbook.getNumberOfSheets();  //Sheet的数�?  
        	List<String> titles = new ArrayList<String>();//放置�?有的标题
			
        	Sheet sheet = workbook.getSheetAt(0);  
        	int rowCount = sheet.getPhysicalNumberOfRows(); //获取总行�?  
        	for (int j = 0; j < rowCount; j++) {//遍历�?
        		Row row = sheet.getRow(j);
                if (row == null) {//略过空行
                    continue;
                }
                int cellSize = row.getLastCellNum();//行中有多少个单元格，也就是有多少�?
                if (j == 0) {//第一行是标题�?
                    for (int k = 0; k < cellSize; k++) {
                        Cell cell = row.getCell(k);
                        titles.add(cell.toString());
                    }
                    result.add(0, titles); 
                } else {//其他行是数据�?
                	List<String> rowList = new ArrayList<String>();//对应�?个数据行
                    for (int k = 0; k < titles.size(); k++) {
                        Cell cell = row.getCell(k);
                        String value = null;
                        int cellType = cell.getCellType();  
                        if (cell != null) {
                        	switch(cellType) {  
	                        case Cell.CELL_TYPE_STRING: //文本  
	                        	value = cell.getStringCellValue();  
	                            break;  
	                        case Cell.CELL_TYPE_NUMERIC: //数字、日�?  
	                            if(DateUtil.isCellDateFormatted(cell)) {  
	                            	value = fmt.format(cell.getDateCellValue()); //日期�?  
	                            }  
	                            else {  
	                            	value = String.valueOf(cell.getNumericCellValue()); //数字  
	                            }  
	                            break;  
	                        case Cell.CELL_TYPE_BOOLEAN: //布尔�?  
	                        	value = String.valueOf(cell.getBooleanCellValue());  
	                            break;  
	                        case Cell.CELL_TYPE_BLANK: //空白  
	                        	value = cell.getStringCellValue();  
	                            break;  
	                        case Cell.CELL_TYPE_ERROR: //错误  
	                        	value = "错误";  
	                            break;  
	                        case Cell.CELL_TYPE_FORMULA: //公式  
	                        	value = "错误";  
	                            break;  
	                        default:  
	                        	value = "错误";  
                        	}  
                        }
                        rowList.add(value);
                    }
                    
                    result.add(rowList);
                }
                
        	}
	        return result;
	        
	    } finally {
	        if (workbook != null) {
	        	workbook.close();
	        }
	    }
	}
	
	
	
	
	
}
