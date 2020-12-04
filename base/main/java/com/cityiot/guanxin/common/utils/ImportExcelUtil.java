package com.cityiot.guanxin.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ImportExcelUtil {
	private final static String excel2003L = ".xls"; // 2003- 版本的excel
	private final static String excel2007U = ".xlsx"; // 2007+ 版本的excel

	/**
	 * 描述：获取IO流中的数据，组装成List<List<Object>>对象
	 * 
	 * @param in,fileName
	 * @return
	 * @throws IOException
	 */
	public List<List<Object>> getListByExcel(InputStream in, String fileName) throws Exception {
		List<List<Object>> list = null;
		// 创建Excel工作薄
		Workbook work = this.getWorkbook(in, fileName);
		if (null == work) {
			throw new Exception("创建Excel工作薄为空！");
		}
		Sheet sheet = null;
		Row row = null;
		Cell cell = null;

		list = new ArrayList<List<Object>>();
		// 遍历Excel中所有的sheet
		for (int i = 0; i < work.getNumberOfSheets(); i++) {
			sheet = work.getSheetAt(i);
			if (sheet == null) {
				continue;
			}

			// 遍历当前sheet中的所有行
			for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
				row = sheet.getRow(j);
				if (row == null || row.getFirstCellNum() == j) {
					continue;
				}
				// 遍历所有的列
				List<Object> li = new ArrayList<Object>();
				for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
					cell = row.getCell(y);
					li.add(this.getCellValue(cell));
				}
				list.add(li);
			}
		}
		return list;
	}

	/**
	 * 描述：根据文件后缀，自适应上传文件的版本
	 * 
	 * @param inStr,fileName
	 * @return
	 * @throws Exception
	 */
	public Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
		Workbook wb = null;
		String fileType = fileName.substring(fileName.lastIndexOf("."));
		if (excel2003L.equals(fileType)) {
			wb = new HSSFWorkbook(inStr); // 2003-
		} else if (excel2007U.equals(fileType)) {
			wb = new XSSFWorkbook(inStr); // 2007+
		} else {
			wb = new HSSFWorkbook(inStr);
		}
		return wb;
	}

	/**
	 * 描述：对表格中数值进行格式化
	 * 
	 * @param cell
	 * @return
	 */
	public Object getCellValue(Cell cell) {
		Object value = null;
		DecimalFormat df = new DecimalFormat("0"); // 格式化number String字符
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd"); // 日期格式化
		SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss"); // 日期格式化
		DecimalFormat df2 = new DecimalFormat("0.00"); // 格式化数字
		try {
			if (null != cell) {
				switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:// 文本
						value = cell.getRichStringCellValue().getString().trim();
						break;
					case Cell.CELL_TYPE_NUMERIC:// 数值
						String cellFormat = cell.getCellStyle().getDataFormatString();

						if (cellFormat.contains("m/d/yy")) { // 日期
							value = sdf.format(cell.getDateCellValue());
						}
						else if (cellFormat.contains("h:mm:ss")) { // 时间
							value = stf.format(cell.getDateCellValue());
						}
						else {
							cell.setCellType(Cell.CELL_TYPE_STRING);
							value = String.valueOf(cell.getRichStringCellValue().getString());
						}
						break;
					case Cell.CELL_TYPE_BOOLEAN:// 布尔
						value = cell.getBooleanCellValue();
						break;
					case Cell.CELL_TYPE_BLANK: // 空白
						value = "";
						break;
					case Cell.CELL_TYPE_FORMULA:// 公式
						// value = cell.getRichStringCellValue().toString().trim();
						try {
							value = cell.getStringCellValue().trim();
						} catch (IllegalStateException e) {
							value = cell.getNumericCellValue();
						}
						break;
					case Cell.CELL_TYPE_ERROR:
						value = null;
						break;
					default:
						value = cell.getRichStringCellValue().toString().trim();
						break;
				}
			} else {
				value = "";
			}
		} catch (Exception e) {
			System.out.println("excel数据出错了！");
		}

		return value;
	}

	/**
	 * 
	 * @param in
	 * @param originalFilename
	 * @return
	 */
	public Map<String, Object> getMapByExcel(InputStream in, String originalFilename, boolean skipFirst)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		// 创建Excel工作薄
		Workbook work = this.getWorkbook(in, originalFilename);
		if (null == work) {
			throw new Exception("创建Excel工作薄为空！");
		}
		Sheet sheet = null;
		Row row = null;
		Cell cell = null;

		List<List<Object>> list = new ArrayList<List<Object>>();
		// 遍历Excel中所有的sheet
		// for (int i = 0; i < work.getNumberOfSheets(); i++) {
		sheet = work.getSheetAt(0);
		int cellNum = 0;
		boolean isInit = true;
		// 遍历当前sheet中的所有行
		for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
			row = sheet.getRow(j);

			// 跳过第一行
			if (skipFirst) {
				if (row == null || row.getFirstCellNum() == j) {
					continue;
				}
			}

			// 遍历所有的列
			List<Object> li = new ArrayList<Object>();
			if (isInit) {
				isInit = false;
				// 根据第一行列数读取数据
				cellNum = row.getLastCellNum();
			}
			for (int y = row.getFirstCellNum(); y < cellNum; y++) {
				cell = row.getCell(y);
				Object cellValue = this.getCellValue(cell);
				if (cellValue == null) {
					throw new Exception("第" + (j + 1) + "行，第" + (y + 1) + "列数据错误，请检查！");
				}
				li.add(cellValue);
			}
			list.add(li);
		}
		// }

		resultMap.put("list", list);
		return resultMap;
	}
}
