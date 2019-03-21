package cn.ce.service.openapi.base.util;

import java.util.Random;

/**
 *
 * @Title: ShortUrl.java
 * @Description
 * @version V1.0
 * @author yang.yu@300.cn
 * @date dat2017年10月23日 time上午10:37:33
 *
 **/
public class ShortUrlUtil {

	public static void main(String[] args) {
		
		System.out.println("短链接:" + ShortUrlUtil.returnShorUrl("www.baidu.com"));// 随机取一个作为短链

	}

	public static String returnShorUrl(String longUrl) {

		String[] resust = ShortText(longUrl);
		for (int i = 0; i < resust.length; i++) {
			print(resust[i]);
		}
		Random random = new Random();
		int j = random.nextInt(4);// 产成4以内随机数
		return resust[j].toString();
	}

	public static String[] ShortText(String string) {
		String key = "OPEN-PLATFORM"; // 自定义生成MD5加密字符串前的混合KEY
		String[] chars = new String[] { // 要使用生成URL的字符
				"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u",
				"v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F",
				"G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

		String hex = Encript.md5(key + string);
		int hexLen = hex.length();
		int subHexLen = hexLen / 8;
		String[] ShortStr = new String[4];

		for (int i = 0; i < subHexLen; i++) {
			String outChars = "";
			int j = i + 1;
			String subHex = hex.substring(i * 8, j * 8);
			long idx = Long.valueOf("3FFFFFFF", 16) & Long.valueOf(subHex, 16);

			for (int k = 0; k < 6; k++) {
				int index = (int) (Long.valueOf("0000003D", 16) & idx);
				outChars += chars[index];
				idx = idx >> 5;
			}
			ShortStr[i] = outChars;
		}

		return ShortStr;
	}

	private static void print(Object messagr) {
		System.out.println(messagr);
	}
}
