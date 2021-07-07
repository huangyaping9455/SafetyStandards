/**
 * Copyright (C), 2015-2020
 * FileName: ImageTool
 * Author:   呵呵哒
 * Date:     2020/11/16 14:54
 * Description:
 */
package org.springblade.common.tool;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
/**
 * @创建人 hyp
 * @创建时间 2020/11/16
 * @描述
 */

public class ImageTool {
	public ImageTool() {
	}

	public static String GetImageStr(byte[] var0) {
		ByteArrayInputStream var1 = new ByteArrayInputStream(var0);
		byte[] var2 = null;
		BASE64Encoder var3 = new BASE64Encoder();

		try {
			var2 = new byte[var1.available()];
			var1.read(var2);
			var1.close();
		} catch (Exception var5) {
			var5.printStackTrace();
		}

		return var3.encode(var2);
	}

	public static boolean GenerateImage(String var0, String var1) {
		if (var0 == null) {
			return false;
		} else {
			BASE64Decoder var2 = new BASE64Decoder();

			try {
				byte[] var3 = var2.decodeBuffer(var0);

				for(int var4 = 0; var4 < var3.length; ++var4) {
					if (var3[var4] < 0) {
						var3[var4] = (byte)(var3[var4] + 256);
					}
				}

				FileOutputStream var6 = new FileOutputStream(var1);
				var6.write(var3);
				var6.flush();
				var6.close();
				return true;
			} catch (Exception var5) {
				return false;
			}
		}
	}
}
