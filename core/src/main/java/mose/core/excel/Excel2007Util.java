package mose.core.excel;

import mose.core.string.StringUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * what: excel工具，读取2007文件格式
 *
 * @author mose created on 2017年11月3日
 */
public class Excel2007Util {
	/**
	 * 
	 * what:  读取excel表头字符串数组
	 * 
	 * @param fileName 文件地址
	 * @param titleIndex excel文件头的行数,默认从第一行开始，对应excel应该是titleIndex-1
	 * @return String[] 返回表头字符串数组
	 *
	 * @author mose created on 2017年10月30日
	 */
	public static String[] readExcelTitle(String fileName, int titleIndex) {
		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;
		InputStream is = null;
		String[] title = null;
		try {
			is = new FileInputStream(fileName);
			wb = WorkbookFactory.create(is);
			sheet = wb.getSheetAt(0);
			row = sheet.getRow(titleIndex - 1);
			// 判断是否为空文件
			if (row == null) {
				return title;
			}
			// 标题总列数
			int colNum = row.getPhysicalNumberOfCells();
			title = new String[colNum];
			for (int i = 0; i < colNum; i++) {
				title[i] = getCellFormatValue(row.getCell(i));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return title;
	}

	/**
	 * 
	 * what:  读取excel表头字符串数组
	 * 
	 * @param is 文件流传入参数
	 * @param titleIndex excel文件头的行数,默认从第一行开始，对应excel应该是titleIndex-1
	 * @return String[] 返回表头字符串数组
	 *
	 * @author mose created on 2017年10月30日
	 */
	public static String[] readExcelTitle(InputStream is, int titleIndex) {
		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;
		String[] title = null;
		try {
			wb = WorkbookFactory.create(is);
			sheet = wb.getSheetAt(0);
			row = sheet.getRow(titleIndex - 1);
			// 标题总列数
			int colNum = row.getPhysicalNumberOfCells();
			title = new String[colNum];
			for (int i = 0; i < colNum; i++) {
				title[i] = getCellFormatValue(row.getCell(i));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return title;
	}

	/**
	 * 
	 * what:  读取excel表头字符串数组
	 * 
	 * @param file 前台获取的文件对象
	 * @param titleIndex excel文件头的行数,默认从第一行开始，对应excel应该是titleIndex-1
	 * @return String[] 返回表头字符串数组
	 *
	 * @author mose created on 2017年10月30日
	 */
	public static String[] readExcelTitle(MultipartFile file, int titleIndex) {
		try {
			InputStream fileInputStream = file.getInputStream();
			return readExcelTitle(fileInputStream, titleIndex);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 
	 * what:  读取Excel数据内容
	 * 
	 * @param fileName 文件地址
	 * @param dataIndex 为数据行的起始行数
	 * @return List 包含单元格数据内容的List对象
	 *
	 * @author mose created on 2017年10月30日
	 */
	public static List<String[]> readExcelContent(String fileName, int dataIndex) {
		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;
		InputStream is = null;
		List<String[]> list = new ArrayList<String[]>();
		try {
			is = new FileInputStream(fileName);
			wb = WorkbookFactory.create(is);
			sheet = wb.getSheetAt(0);
			int rowNum = sheet.getLastRowNum();// 得到总行数
			row = sheet.getRow(0);
			int colNum = row.getPhysicalNumberOfCells(); // 正文内容应该从第二行开始,第一行为表头的标题
			for (int i = dataIndex - 1; i <= rowNum + dataIndex - 1; i++) {
				row = sheet.getRow(i);
				if (row != null) {
					String[] values = new String[colNum];
					for (int j = 0; j < colNum; j++) {
						values[j] = getCellFormatValue(row.getCell(j)).trim();
					}
					list.add(values);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return list;
	}

	/**
	 * 
	 * what:    读取Excel数据内容
	 * 
	 * @param is 文件的输入流
	 * @param dataIndex 为数据行的起始行数
	 * @return List 包含单元格数据内容的List对象
	 *
	 * @author mose created on 2017年10月30日
	 */
	public static List<String[]> readExcelContent(InputStream is, int dataIndex) {
		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;
		List<String[]> list = new ArrayList<String[]>();
		try {
			wb = WorkbookFactory.create(is);
			sheet = wb.getSheetAt(0);
			int rowNum = sheet.getLastRowNum();// 得到总行数
			row = sheet.getRow(0);
			int colNum = row.getPhysicalNumberOfCells(); // 正文内容应该从第二行开始,第一行为表头的标题
			for (int i = dataIndex - 1; i <= rowNum + dataIndex - 1; i++) {
				row = sheet.getRow(i);
				if (row != null) {
					String[] values = new String[colNum];
					for (int j = 0; j < colNum; j++) {
						values[j] = getCellFormatValue(row.getCell(j)).trim();
						if ("".equals(values[j].trim())) {
						}
					}
					list.add(values);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return list;
	}

	/**
	 * 
	 * what:    读取Excel数据内容 
	 * 
	 * @param file 前台获取的文件对象
	 * @param dataIndex 为数据行的起始行数
	 * @return List 包含单元格数据内容的List对象
	 *
	 * @author mose created on 2017年10月30日
	 */
	public static List<String[]> readExcelContent(MultipartFile file, int dataIndex) {
		try {
			InputStream fileInputStream = file.getInputStream();
			return readExcelContent(fileInputStream, dataIndex);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static String getCellFormatValue(Cell cell) {
		String cellvalue = "";
		if (cell != null) {
			// 判断当前Cell的Type
			switch (cell.getCellType()) { // 如果当前Cell的Type为NUMERIC
			case Cell.CELL_TYPE_BLANK:
				cellvalue = "";
				break;
			case Cell.CELL_TYPE_NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					cellvalue = sdf.format(DateUtil.getJavaDate(cell.getNumericCellValue())).toString();
				} else {
					double val = cell.getNumericCellValue();
					if (String.valueOf(val).toUpperCase().indexOf('E') > -1) {
						cellvalue = String.valueOf(new DecimalFormat("#.######").format(val));
					} else {
						cellvalue = StringUtil.formatNumber(String.valueOf(val));
					}
				}
				break;
			case Cell.CELL_TYPE_STRING:
				// cell.setCellType(Cell.CELL_TYPE_STRING);
				cellvalue = cell.getRichStringCellValue().toString();
				break;
			default:
				cellvalue = "";// 默认的Cell值
			}
		}
		return cellvalue;
	}

	/**
	 * 
	 * what:  写excel
	 * 
	 * @param data 数据数组
	 * @param fileName 文件名
	 * @param response
	 *
	 * @author mose created on 2017年11月3日
	 */
	public static void writeExcel(String[][] data, String fileName, HttpServletResponse response) {
		// FileOutputStream out = null;
		OutputStream os = null;
		try {

			// / out = new FileOutputStream(new File("D://result.xlsx"));
			Workbook wb = new SXSSFWorkbook(500);

			Sheet writeSheet = wb.createSheet();
			Font fontTitle = wb.createFont();
			fontTitle.setFontHeightInPoints((short) 12);
			fontTitle.setFontName("宋体");
			fontTitle.setBoldweight(Font.BOLDWEIGHT_BOLD);
			fontTitle.setCharSet(Font.DEFAULT_CHARSET);
			CellStyle cellTitleStyle = wb.createCellStyle();

			cellTitleStyle.setFont(fontTitle);
			cellTitleStyle.setBorderBottom((short) 1);
			cellTitleStyle.setBorderLeft((short) 1);
			cellTitleStyle.setBorderRight((short) 1);
			cellTitleStyle.setBorderTop((short) 1);
			cellTitleStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
			cellTitleStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
			Font fontData = wb.createFont();
			fontData.setFontHeightInPoints((short) 10);
			fontData.setFontName("宋体");
			CellStyle cellDataStyle = wb.createCellStyle();
			cellDataStyle.setFont(fontData);
			cellDataStyle.setBorderBottom((short) 1);
			cellDataStyle.setBorderLeft((short) 1);
			cellDataStyle.setBorderRight((short) 1);
			cellDataStyle.setBorderTop((short) 1);
			cellDataStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
			cellDataStyle.setFillForegroundColor(HSSFColor.WHITE.index);
			// 创建一个DataFormat对象
			DataFormat format = wb.createDataFormat();
			// 这样才能真正的控制单元格格式，@就是指文本型，具体格式的定义还是参考上面的原文吧
			cellDataStyle.setDataFormat(format.getFormat("@"));

			// 写表头
			Row writeRow = writeSheet.createRow(0);
			for (int i = 0; i < data[0].length; i++) {
				Cell cell = writeRow.createCell(i);

				cell.setCellValue(data[0][i]);
				cell.setCellStyle(cellTitleStyle);
			}
			// 写数据内容
			for (int i = 1; i < data.length; i++) {
				Row dataRow = writeSheet.createRow(i);
				for (int j = 0; j < data[i].length; j++) {
					Cell cell = dataRow.createCell(j);
					cell.setCellValue(data[i][j]);
					cell.setCellStyle(cellDataStyle);
				}
			}

			response.setContentType("application/msexcel");
			response.reset();
			response.setHeader("content-disposition", "attachment; filename=" + new String(fileName.getBytes("GBK"), "ISO-8859-1") + ".xlsx");
			System.setProperty("org.apache.poi.util.POILogger", "org.apache.poi.util.POILogger");
			os = response.getOutputStream();
			wb.write(os);
			os.flush();
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (os != null) {
					os.close();
					os = null;
				}
				response.flushBuffer();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
