package mose.core.validate;

import java.util.List;

/**
 * 校验格式
 * 
 * @author mose
 * @date 2017年10月11日
 */
public class ValidateUtil {
	

	/**
	 * 校验字符串为空结果
	 * 
	 * @param row
	 * @param field
	 * @param fieldValue
	 */
	public static String retValidateEmpty(int row, String field, String fieldValue) {
		return "第" + row + "行," + field + "【" + fieldValue + "】不能为空<br/>";
	}


	/**
	 * 校验字符串
	 * 
	 * @param row
	 * @param field
	 * @param fieldValue
	 */
	public static String retValidateLength(int row, String field, String fieldValue) {
		return "第" + row + "行," + field + "【" + fieldValue + "】超长<br/>";
	}
	/**
	 * 校验字符串
	 * 
	 * @param row
	 * @param field
	 * @param fieldValue
	 */
	public static String retValidateLength2(int row, String field, String fieldValue) {
		return "第" + row + "行," + field + "【" + fieldValue + "】过短<br/>";
	}
	/**
	 * 校验字符串名称不一致
	 * 
	 * @param row
	 * @param field
	 * @param fieldValue
	 */
	public static String retValiisSex(int row, String field, String fieldValue) {
		return "第" + row + "行," + field + "【" + fieldValue + "】应为“男、女”<br/>";
	}	

	/**
	 * 校验字符为非数字
	 * 
	 * @param row
	 * @param field
	 * @param fieldValue
	 */
	public static String retValidateNotNumber(int row, String field, String fieldValue) {
		return "第" + row + "行," + field + "【" + fieldValue + "】非合法数字<br/>";
	}
	
	/**
	 * 校验信息是否存在
	 * 
	 * @param row
	 * @param field
	 * @param fieldValue
	 */
	public static String retValidateNotDw(int row, String field, String fieldValue) {
		return "第" + row + "行," + field + "【" + fieldValue + "】不存在<br/>";
	}

	/**
	 * 校验数据过大
	 * 
	 * @param row
	 * @param field
	 * @param fieldValue
	 */
	public static String retValidateMaxValue(int row, String field, String fieldValue) {
		return "第" + row + "行," + field + "【" + fieldValue + "】数据过大<br/>";
	}


	/**
	 * 校验信息唯一性
	 * 
	 * @param row
	 * @param field
	 * @param fieldValue
	 */
	public static String retValidateNotOnly(int row, String field, String fieldValue) {
		return "第" + row + "行," + field + "【" + fieldValue + "】已存在，不可重复添加<br/>";
	}



	/**
	 * 校验输入数据格式是否正确
	 * 
	 * @param row
	 * @param field
	 * @param fieldValue
	 */
	public static String retValidateNotFormat(int row, String field, String fieldValue) {
		return "第" + row + "行," + field + "【" + fieldValue + "】格式不正确<br/>";
	}

	/**
	 * 校验信息唯一性
	 * 
	 * @param row
	 * @param field
	 * @param fieldValue
	 */
	public static String retValidateNotOnly2(int row, String field, String fieldValue) {
		return "第" + row + "行," + field + "【" + fieldValue + "】文件中存在重复数据，请修改<br/>";
	}

	
	/**
	 * 校验Excel中数据的唯一
	 * @param list
	 */
	public static StringBuffer excelOnly(List<String[]> list,StringBuffer sb,String chmc){
		boolean flag=false;
		String ch = "";
		int j = 1;
		int k = 1;
		for (String[] str : list) {
			int count = 0;
			k++;
			String ch2 = str[1];
			for (String[] str1 : list) {
				String ch1 = str1[1];
				if(ch2.equals(ch1)){
					count++;
				}
			}
			if(count>=2){
				ch = ch2;
				j = k;
				flag = true;
				if(flag){
					sb.append(ValidateUtil.retValidateNotOnly2(j, chmc, ch));
					return sb;
				}
			}
		}
		return sb;
	}
	
	/**
	 * 校验学院专业之间字符间隔
	 * 
	 * @param row
	 * @param field
	 * @param fieldValue
	 */
	public static String retValisStationNotFormat(int row, String field, String fieldValue) {
		return "第" + row + "行," + field + "【" + fieldValue + "】格式不正确，应为“学院--专业”<br/>";
	}


}
