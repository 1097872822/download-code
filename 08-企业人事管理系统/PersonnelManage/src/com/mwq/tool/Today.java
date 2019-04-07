package com.mwq.tool;

import java.util.Calendar;

public class Today {

	public static final Calendar NOW = Calendar.getInstance();

	public static final int YEAR = NOW.get(Calendar.YEAR);

	public static final int MONTH = NOW.get(Calendar.MONTH) + 1;

	public static final int DAY = NOW.get(Calendar.DAY_OF_MONTH);

	public static final String TODAY_DATE = YEAR + "-" + MONTH + "-" + DAY;

	public String getNowDateAndTime() {
		Calendar now = Calendar.getInstance();
		int hour = now.get(Calendar.HOUR_OF_DAY);
		int minute = now.get(Calendar.MINUTE);
		return TODAY_DATE + " 00:00";
	}

}
