/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:cdpf_v1
 * Module Name:core
 */
package mose.core.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * what: 验证各种字符串格式是否符合正则表达式
 *
 * @author mose created on 2017年11月3日
 */
public class RegexUtil {

	/**
	 * 手机号校验规则
	 */
	public static final String MOBILE_REG = "^0?(13[0-9]|15[012356789]|17[013678]|18[0-9]|14[57])[0-9]{8}$";

	/**
	 * 金额校验规则
	 */
	public static final String MONEY_REG = "^([1-9][\\d]{0,8}|0)(\\.[\\d]{1,2})?$";
	/**
	 * 邮箱校验规则
	 */
	public static final String EMAIL_REG = "^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+";
	/**
	 * 身份证号
	 */
	public static final String IDCARD_REG = "^[1-9][0-9]{5}(19[0-9]{2}|200[0-9]|201[0-9]|202[0-9]|203[0-9])(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])[0-9]{3}[0-9xX]$";
	/**
	 * 座机号
	 */
	public static final String TELEPHONE_REG = "^0\\d{2,3}-\\d{7,8}(-\\d{1,6})?$";
	/**
	 * 邮政编码
	 */
	public static final String POSTAL_REG = "^[0-9][0-9]{5}$";
	/**
	 * qq号
	 */
	public static final String QQ_REG = "^\\d{5,10}$";
	/**
	 * 包含非法字符
	 */
	public static final String ILLEGAL_CHARACTER_REG = ".[^<>&/|'\\@#\\$%\\^&\\*]+";
	/**
	 * 年龄
	 */
	public static final String AGE_REG = "^(?:0|[1-9][0-9]?|120)$";
	/**
	 * IP
	 */
	public static final String IP_REG = "(((\\d{1,2})|(1\\d{2})|(2[0-4]\\d)|(25[0-5]))\\.){3}((\\d{1,2})|(1\\d{2})|(2[0-4]\\d)|(25[0-5]))";
	/**
	 * 非负整数
	 */
	public static final String NON_NEGATIVE_INTEGERS_REG = "^(0|[1-9]\\d*)$";
	/**
	 * 全英文
	 */
	public static final String ENGLISTHES_REG = "^[a-zA-Z]+$";
	/**
	 * 数字
	 */
	public static final String NUMBERS_REG = "^[0-9]+$";
	/**
	 * 全中文
	 */
	public static final String CHINESE_REG = "^[\u4E00-\u9FA5]+$";
	/**
	 * 中英混合
	 */
	public static final String CHINESE_ENGLISH_REG = "^[\u4E00-\u9FA5A-Za-z]+$";
	/**
	 * 学号
	 */
	public static final String STNO_REG = "^[1-9]\\d{3}(\\d){8}+$";
	/**
	 * 部门编码
	 */
	public static final String DEPARTMENT_CODE_REG="^[a-zA-Z][a-zA-Z0-9]*$";
	/**
	 * what: 验证字符串是否符合手机号格式
	 *
	 * @param mobile 待验证的字符串
	 *
	 * @return boolean true表示符合格式/false表示不符合格式
	 *
	 * @author mose created on 2017年11月3日
	 */
	public static boolean validateMobile(String mobile) {
		boolean result = false;
		Pattern pattern = Pattern.compile(MOBILE_REG);
		Matcher matcher = pattern.matcher(mobile);
		result = matcher.matches();
		return result;
	}

	/**
	 * what: 验证字符串是否符合邮箱格式
	 *
	 * @param email 待验证的字符串
	 *
	 * @return boolean true表示符合格式/false表示不符合格式
	 *
	 * @author mose created on 2017年11月3日
	 */
	public static boolean validateEmail(String email) {
		boolean result = false;
		Pattern pattern = Pattern.compile(EMAIL_REG);
		Matcher matcher = pattern.matcher(email);
		result = matcher.matches();
		return result;
	}

	/**
	 * what: 验证字符串是否符合身份证号格式
	 *
	 * @param idCard 待验证的字符串
	 *
	 * @return boolean true表示符合格式/false表示不符合格式
	 *
	 * @author mose created on 2017年11月3日
	 */
	public static boolean validateIdCard(String idCard) {
		boolean result = false;
		Pattern pattern = Pattern.compile(IDCARD_REG);
		Matcher matcher = pattern.matcher(idCard);
		result = matcher.matches();
		return result;
	}

	/**
	 * what: 验证字符串是否符合座机号格式
	 *
	 * @param telephone 待验证的字符串
	 *
	 * @return boolean true表示符合格式/false表示不符合格式
	 *
	 * @author mose created on 2017年11月3日
	 */
	public static boolean validateTelephone(String telephone) {
		boolean result = false;
		Pattern pattern = Pattern.compile(TELEPHONE_REG);
		Matcher matcher = pattern.matcher(telephone);
		result = matcher.matches();
		return result;
	}

	/**
	 * what: 验证字符串是否符合邮政编码格式
	 *
	 * @param postalcode 待验证的字符串
	 *
	 * @return boolean true表示符合格式/false表示不符合格式
	 *
	 * @author mose created on 2017年11月3日
	 */
	public static boolean validatePostalcode(String postalcode) {
		boolean result = false;
		Pattern pattern = Pattern.compile(POSTAL_REG);
		Matcher matcher = pattern.matcher(postalcode);
		result = matcher.matches();
		return result;
	}

	/**
	 * what: 验证字符串是否符合QQ格式
	 *
	 * @param qq 待验证的字符串
	 *
	 * @return boolean true表示符合格式/false表示不符合格式
	 *
	 * @author mose created on 2017年11月3日
	 */
	public static boolean validateQQ(String qq) {
		boolean result = false;
		Pattern pattern = Pattern.compile(QQ_REG);
		Matcher matcher = pattern.matcher(qq);
		result = matcher.matches();
		return result;
	}

	/**
	 * what: 验证字符串是否包含非法字符格式
	 *
	 * @param illegalcharacter 待验证的字符串
	 *
	 * @return boolean true表示符合格式/false表示不符合格式
	 *
	 * @author mose created on 2017年11月3日
	 */
	public static boolean validateIllegalCharacter(String illegalcharacter) {
		boolean result = false;
		Pattern pattern = Pattern.compile(ILLEGAL_CHARACTER_REG);
		Matcher matcher = pattern.matcher(illegalcharacter);
		result = matcher.matches();
		return result;
	}

	/**
	 * what: 验证字符串是否符合年龄格式
	 *
	 * @param age 待验证的字符串
	 *
	 * @return boolean true表示符合格式/false表示不符合格式
	 *
	 * @author mose created on 2017年11月3日
	 */
	public static boolean validateAge(String age) {
		boolean result = false;
		Pattern pattern = Pattern.compile(AGE_REG);
		Matcher matcher = pattern.matcher(age);
		result = matcher.matches();
		return result;
	}

	/**
	 * what: 验证字符串是否符合IP格式
	 *
	 * @param ip 待验证的字符串
	 *
	 * @return boolean true表示符合格式/false表示不符合格式
	 *
	 * @author mose created on 2017年11月3日
	 */
	public static boolean validateIp(String ip) {
		boolean result = false;
		Pattern pattern = Pattern.compile(IP_REG);
		Matcher matcher = pattern.matcher(ip);
		result = matcher.matches();
		return result;
	}

	/**
	 * what: 验证字符串是否符合非负整数格式
	 *
	 * @param nonnegativeIntegers 待验证的字符串
	 *
	 * @return boolean true表示符合格式/false表示不符合格式
	 *
	 * @author mose created on 2017年11月3日
	 */
	public static boolean validateNonnegativeIntegers(String nonnegativeIntegers) {
		boolean result = false;
		Pattern pattern = Pattern.compile(NON_NEGATIVE_INTEGERS_REG);
		Matcher matcher = pattern.matcher(nonnegativeIntegers);
		result = matcher.matches();
		return result;
	}

	/**
	 * what: 验证字符串是否符合全英文格式
	 *
	 * @param englishes 待验证的字符串
	 *
	 * @return boolean true表示符合格式/false表示不符合格式
	 *
	 * @author mose created on 2017年11月3日
	 */
	public static boolean validateEnglishes(String englishes) {
		boolean result = false;
		Pattern pattern = Pattern.compile(ENGLISTHES_REG);
		Matcher matcher = pattern.matcher(englishes);
		result = matcher.matches();
		return result;
	}

	/**
	 * what: 验证字符串是否符合全英文格式（可以指定长度）
	 *
	 * @param englishes 待验证的字符串
	 * @param minLength 验证字符串的最小长度
	 * @param maxLength 验证字符串的最大长度
	 *
	 * @return boolean true表示符合格式/false表示不符合格式
	 *
	 * @author mose created on 2017年11月3日
	 */
	public static boolean validateEnglishes(String englishes, Integer minLength, Integer maxLength) {
		boolean result = false;
		Pattern pattern = Pattern.compile("^[a-zA-Z]{" + minLength + "," + maxLength + "}$");
		Matcher matcher = pattern.matcher(englishes);
		result = matcher.matches();
		return result;
	}

	/**
	 * what: 验证字符串是否符合全数字格式
	 *
	 * @param numbers 待验证的字符串
	 *
	 * @return boolean true表示符合格式/false表示不符合格式
	 *
	 * @author mose created on 2017年11月3日
	 */
	public static boolean validateNumbers(String numbers) {
		boolean result = false;
		Pattern pattern = Pattern.compile(NUMBERS_REG);
		Matcher matcher = pattern.matcher(numbers);
		result = matcher.matches();
		return result;
	}

	/**
	 * what: 验证字符串是否符合全数字格式（可指定长度）
	 *
	 * @param numbers 待验证的字符串
	 * @param minLength 验证字符串的最小长度
	 * @param maxLength 验证字符串的最大长度
	 *
	 * @return boolean true表示符合格式/false表示不符合格式
	 *
	 * @author mose created on 2017年11月3日
	 */
	public static boolean validateNumbers(String numbers, Integer minLength, Integer maxLength) {
		boolean result = false;
		Pattern pattern = Pattern.compile("^[0-9]{" + minLength + "," + maxLength + "}$");
		Matcher matcher = pattern.matcher(numbers);
		result = matcher.matches();
		return result;
	}

	/**
	 * what: 验证字符串是否符合全中文格式
	 *
	 * @param chinese 待验证的字符串
	 *
	 * @return boolean true表示符合格式/false表示不符合格式
	 *
	 * @author mose created on 2017年11月3日
	 */
	public static boolean validateChinese(String chinese) {
		boolean result = false;
		Pattern pattern = Pattern.compile(CHINESE_REG);
		Matcher matcher = pattern.matcher(chinese);
		result = matcher.matches();
		return result;
	}

	/**
	 * what: 验证字符串是否符合全中文格式（可指定长度）
	 *
	 * @param chinese 待验证的字符串
	 * @param minLength 验证字符串的最小长度
	 * @param maxLength 验证字符串的最大长度
	 *
	 * @return boolean true表示符合格式/false表示不符合格式
	 *
	 * @author mose created on 2017年11月3日
	 */
	public static boolean validateChinese(String chinese, Integer minLength, Integer maxLength) {
		boolean result = false;
		Pattern pattern = Pattern.compile("^[\u4E00-\u9FA5]{" + minLength + "," + maxLength + "}$");
		Matcher matcher = pattern.matcher(chinese);
		result = matcher.matches();
		return result;
	}

	/**
	 * what: 验证字符串是否符合账号或者用户名格式
	 *
	 * @param name 待验证的字符串
	 *
	 * @return boolean true表示符合格式/false表示不符合格式
	 *
	 * @author mose created on 2017年11月3日
	 */
	public static boolean validateName(String name) {
		boolean result = false;
		Pattern pattern = Pattern.compile("^\\w+$");
		Matcher matcher = pattern.matcher(name);
		result = matcher.matches();
		return result;
	}

	/**
	 * what: 验证字符串是否符合账号或者用户名格式（可限制位数）
	 *
	 * @param name 待验证的字符串
	 * @param minLength 验证字符串的最小长度
	 * @param maxLength 验证字符串的最大长度
	 *
	 * @return boolean true表示符合格式/false表示不符合格式
	 *
	 * @author mose created on 2017年11月3日
	 */
	public static boolean validateName(String name, Integer minLength, Integer maxLength) {
		boolean result = false;
		Pattern pattern = Pattern.compile("^\\w{" + minLength + "," + maxLength + "}$");
		Matcher matcher = pattern.matcher(name);
		result = matcher.matches();
		return result;
	}

	/**
	 * 
	 * what:验证字符串是否符合中英文混排格式
	 * 
	 * @param regexStr 待验证的字符串
	 * @return boolean true表示符合格式/false表示不符合格式
	 *
	 * @author mose created on 2017年11月9日
	 */
	public static boolean validateChineseAndEnglish(String regexStr) {
		boolean result = false;
		Pattern pattern = Pattern.compile(CHINESE_ENGLISH_REG);
		Matcher matcher = pattern.matcher(regexStr);
		result = matcher.matches();
		return result;
	}

	/**
	 * 
	 * what: 验证是否符合学号的规则
	 * 
	 * @param no
	 * @return
	 *
	 * @author mose created on 2017年11月10日
	 */
	public static boolean validateStno(String no) {
		boolean result = false;
		Pattern pattern = Pattern.compile(STNO_REG);
		Matcher matcher = pattern.matcher(no);
		result = matcher.matches();
		return result;
	}
}
