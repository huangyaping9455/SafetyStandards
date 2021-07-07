/**
 * Copyright (C), 2015-2020
 * FileName: Md5
 * Author:   呵呵哒
 * Date:     2020/11/16 14:48
 * Description:
 */
package org.springblade.common.tool;

import java.security.MessageDigest;
/**
 * @创建人 hyp
 * @创建时间 2020/11/16
 * @描述
 */

public class Md5 {
	private static char[] _$1 = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

	public Md5() {
	}

	public Md5(char[] var1) {
		_$1 = var1;
	}

	public static final String MD5(String var0) {
		return MD5((String)var0, 0);
	}

	public static final String MD5(String var0, int var1) {
		try {
			byte[] var2 = var0.getBytes("UTF-8");
			MessageDigest var3 = MessageDigest.getInstance("MD5");
			var3.update(var2);
			byte[] var4 = var3.digest();
			int var5 = var4.length;
			char[] var6 = new char[var5 * 2];
			int var7 = 0;

			for(int var8 = 0; var8 < var5; ++var8) {
				byte var9 = var4[var8];
				var6[var7++] = _$1[var9 >>> 4 & 15];
				var6[var7++] = _$1[var9 & 15];
			}

			String var11 = new String(var6);
			if (var11.length() > 8 + var1) {
				var11 = var11.substring(8, 8 + var1).toUpperCase();
				return var11;
			} else {
				return var11;
			}
		} catch (Exception var10) {
			return null;
		}
	}

	public static final String MD5(byte[] var0) {
		return MD5((byte[])var0, 0);
	}

	public static final String MD5(byte[] var0, int var1) {
		try {
			MessageDigest var2 = MessageDigest.getInstance("MD5");
			var2.update(var0);
			byte[] var3 = var2.digest();
			int var4 = var3.length;
			char[] var5 = new char[var4 * 2];
			int var6 = 0;

			for(int var7 = 0; var7 < var4; ++var7) {
				byte var8 = var3[var7];
				var5[var6++] = _$1[var8 >>> 4 & 15];
				var5[var6++] = _$1[var8 & 15];
			}

			String var10 = new String(var5);
			if (var10.length() > 8 + var1) {
				var10 = var10.substring(8, 8 + var1).toUpperCase();
				return var10;
			} else {
				return var10;
			}
		} catch (Exception var9) {
			return null;
		}
	}
}

