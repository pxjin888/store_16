package cn.btttech.util;

import java.util.UUID;


public class UuidGenerator{

	public static String generate(){
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		String[] subStr = str.split("-");
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < subStr.length; i++) {
			buff.append(subStr[i].toString());
		}
		return buff.toString();
	}

}
