package com.cityiot.guanxin.common.utils;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;



public class ExcelUtils {
	
	public static final String OFFICE_EXCEL_2003_POSTFIX = "xls";  
    public static final String OFFICE_EXCEL_2010_POSTFIX = "xlsx";  
    public static final String EMPTY = "";  
    public static final String POINT = ".";  
    public static SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd");  
	
	public static HSSFWorkbook eqEcel(List<Map<String, Object>> rowlist,String[] titles,String sheetName,String[] columnKey) throws Exception {
		  
        // 创建工作表和标题  
        HSSFWorkbook workbook = null;  
        try {  
            workbook = new HSSFWorkbook();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
          
        // 定义字体  
        HSSFFont celltbnamefont = workbook.createFont();  
        celltbnamefont.setFontHeightInPoints((short) 12); // 字体大小  
        celltbnamefont.setColor((short) (HSSFFont.COLOR_NORMAL)); // 颜色  
        celltbnamefont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 粗体  

        // 定义 date 的数据样式  
        HSSFCellStyle datestyle = workbook.createCellStyle();  
        HSSFDataFormat df = workbook.createDataFormat();  
        datestyle.setAlignment((short) HSSFCellStyle.ALIGN_LEFT);  
        datestyle.setDataFormat(df.getFormat("yyyy-mm-dd hh:mm:ss"));  
  
        // 定义 int 的数据样式  
        HSSFCellStyle intdatestyle = workbook.createCellStyle();  
        intdatestyle.setAlignment((short) HSSFCellStyle.ALIGN_LEFT);  
  
        // 定义 float 的数据样式  
        HSSFCellStyle floatdatestyle = workbook.createCellStyle();  
        floatdatestyle.setAlignment((short) HSSFCellStyle.ALIGN_LEFT);  
        df = workbook.createDataFormat();  
        floatdatestyle.setDataFormat(df.getFormat("#.##"));  
  
        // 定义 long 的数据样式  
        HSSFCellStyle longdatestyle = workbook.createCellStyle();  
        longdatestyle.setAlignment((short) HSSFCellStyle.ALIGN_LEFT);  
  
        //title样式  
        HSSFCellStyle titledatestyle = workbook.createCellStyle();  
        titledatestyle.setAlignment((short) HSSFCellStyle.ALIGN_CENTER_SELECTION);  
        titledatestyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);   
        titledatestyle.setFont(celltbnamefont);  
        
        //title样式  
        HSSFCellStyle titledatestyle2 = workbook.createCellStyle();  
        titledatestyle2.setAlignment((short) HSSFCellStyle.ALIGN_CENTER_SELECTION);  
        titledatestyle2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        titledatestyle2.setWrapText(true) ;
//        titledatestyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        titledatestyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        titledatestyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        titledatestyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);  
       
        //title样式  
        HSSFCellStyle titledatestyle3 = workbook.createCellStyle();  
        titledatestyle3.setWrapText(true) ;
        

        
              
        // 定义列的样式  
        HSSFCellStyle items_style = workbook.createCellStyle();  
        items_style.setAlignment((short) HSSFCellStyle.ALIGN_CENTER); // 设置对其方式  
        items_style.setFont(celltbnamefont);  
        items_style.setWrapText(false); // 设置自动换行  
        items_style.setFillForegroundColor(HSSFColor.ROSE.index);// 设置背景色  
        items_style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        items_style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框  
        items_style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框  
        items_style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框  
        items_style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框  
          
        HSSFCellStyle row_style = workbook.createCellStyle();  
        titledatestyle.setAlignment((short) HSSFCellStyle.ALIGN_CENTER_SELECTION);  
        titledatestyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);  
          
       
          
          
        int rowIndex = 0;  

          
        HSSFSheet sheet = workbook.createSheet(sheetName); // 创建工作区  
        //sheet.setDefaultRowHeightInPoints(100);  
        //sheet.setDefaultRowHeight((short)100);  
          
          
      
          
        //将list 数据 打印到表格中  
        HSSFCell cell;  
          
  
        HSSFRow row =sheet.createRow((short) (rowIndex));  
        // 加入 标题  
        for (int i = 0; i < titles.length; i++) {  
            cell = row.createCell(i, Cell.CELL_TYPE_STRING); // 设置 列类型  

            sheet.setColumnWidth(i, 5500);  
            cell.setCellValue(titles[i]);  
            cell.setCellStyle(items_style);  
        }  
       
      
        rowIndex=0;
        for(Map<String, Object> map:rowlist)
        {
        	
            HSSFRow dataRow = sheet.createRow((short) (++rowIndex));   
            dataRow.setRowStyle(row_style);

              for(int i=0;i<columnKey.length;i++)
              {
                  cell = dataRow.createCell(i, Cell.CELL_TYPE_STRING);
                  cell.setCellValue((map.get(columnKey[i])==null?"":map.get(columnKey[i])).toString());//map.get(columnKey[i])
                  cell.setCellStyle(titledatestyle3);
              }
  
        }

   
        return workbook;  
    }  
	
	
	
	/** 
     * 获得path的后缀名 
     * @param path 
     * @return 
     */  
    public static String getPostfix(String path){  
        if(path==null || EMPTY.equals(path.trim())){  
            return EMPTY;  
        }  
        if(path.contains(POINT)){  
            return path.substring(path.lastIndexOf(POINT)+1,path.length());  
        }  
        return EMPTY;  
    }  
    
    
    /** 
     * 单元格格式 
     * @param hssfCell 
     * @return 
     */  
    @SuppressWarnings({ "static-access", "deprecation" })  
    public static String getHValue(Cell hssfCell){  
         if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {  
             return String.valueOf(hssfCell.getBooleanCellValue());  
         } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {  
             String cellValue = "";  
             if(HSSFDateUtil.isCellDateFormatted(hssfCell)){                  
                 Date date = HSSFDateUtil.getJavaDate(hssfCell.getNumericCellValue());  
                 cellValue = sdf.format(date);  
             }else{  
                 DecimalFormat df = new DecimalFormat("#.##");  
                 cellValue = df.format(hssfCell.getNumericCellValue());  
                 String strArr = cellValue.substring(cellValue.lastIndexOf(POINT)+1,cellValue.length());  
                 if(strArr.equals("00")){  
                     cellValue = cellValue.substring(0, cellValue.lastIndexOf(POINT));  
                 }    
             }  
             return cellValue;  
         } else {  
            return String.valueOf(hssfCell.getStringCellValue());  
         }  
    }  
    /** 
     * 单元格格式 
     * @param xssfCell 
     * @return 
     */  
    public static String getXValue(Cell xssfCell){  
         if (xssfCell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {  
             return String.valueOf(xssfCell.getBooleanCellValue());  
         } else if (xssfCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {  
             String cellValue = "";  
             if(XSSFDateUtil.isCellDateFormatted(xssfCell)){  
                 Date date = XSSFDateUtil.getJavaDate(xssfCell.getNumericCellValue());  
                 cellValue = sdf.format(date);  
             }else{  
                 DecimalFormat df = new DecimalFormat("#.##");  
                 cellValue = df.format(xssfCell.getNumericCellValue());  
                 String strArr = cellValue.substring(cellValue.lastIndexOf(POINT)+1,cellValue.length());  
                 if(strArr.equals("00")){  
                     cellValue = cellValue.substring(0, cellValue.lastIndexOf(POINT));  
                 }    
             }  
             return cellValue;  
         } else {  
            return String.valueOf(xssfCell.getStringCellValue());  
         }  
    }     
	
    /** 
     * 自定义xssf日期工具类 
     * @author lp 
     * 
     */  
    static class XSSFDateUtil extends DateUtil{  
        protected static int absoluteDay(Calendar cal, boolean use1904windowing) {    
            return DateUtil.absoluteDay(cal, use1904windowing);    
        }   
    }



    //Object转Map
    public static Map<String, Object> getObjectToMap(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(obj);
            if (value == null) {
                value = "";
            }
            map.put(fieldName, value);
        }
        return map;
    }
}
