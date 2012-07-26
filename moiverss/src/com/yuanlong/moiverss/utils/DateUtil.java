package com.yuanlong.moiverss.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class DateUtil {
	private static final SimpleDateFormat ALL_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd");

	private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat(
			"HH:mm:ss");
	public static final String DateFormat_yyyyMMdd = "yyyyMMdd";
	public static final String DATEFORMAT_YYYY_MM_DD = "yyyy-MM-dd";
	public static final String DATEFORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String DATEFORMAT_YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String START_TIME = "START_TIME";
	public static final String END_TIME = "END_TIME";

	public static String getTimeFlag() {
		return String.valueOf(new Date().getTime());
	}

	public static String format(Date date, String format) {
		if (date == null)
			return null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		} catch (Exception e) {
		}
		return null;
	}

	public static String formatDate(Date date) {
		if (date == null)
			return null;
		try {
			return DATE_FORMAT.format(date);
		} catch (Exception e) {
		}
		return null;
	}

	public static String formatTime(Date date) {
		if (date == null)
			return null;
		try {
			return TIME_FORMAT.format(date);
		} catch (Exception e) {
		}
		return null;
	}

	public static String formatAll(Date date) {
		if (date == null)
			return null;
		try {
			return ALL_FORMAT.format(date);
		} catch (Exception e) {
		}
		return null;
	}

	public static Date parse(String str, String format) {
		if ((str == null) || (str.length() == 0))
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(str);
		} catch (Exception e) {
		}
		return null;
	}

	public static Date parseAll(String str) {
		return parse(str, "yyyy-MM-dd HH:mm:ss");
	}

	public static Date getNowDate() {
		return getDate(new Date());
	}

	public static Date getDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(11, 0);
		calendar.set(12, 0);
		calendar.set(13, 0);
		calendar.set(14, 0);
		return calendar.getTime();
	}

	public static boolean isSameDate(Date sourceDate,Date targetDate){
		Calendar calendarS = Calendar.getInstance();
		calendarS.setTime(sourceDate);
		Calendar calendarT = Calendar.getInstance();
		calendarT.setTime(targetDate);
		
		return (calendarS.get(Calendar.YEAR)==calendarT.get(Calendar.YEAR))&&
				(calendarS.get(Calendar.MONTH)==calendarT.get(Calendar.MONTH))&&
				(calendarS.get(Calendar.DAY_OF_MONTH)==calendarT.get(Calendar.DAY_OF_MONTH));
				
		
	}
	
	public static Date get8Date(){
		Calendar calendar= Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
		calendar.set(11, 0);
		calendar.set(12, 0);
		calendar.set(13, 0);
		calendar.set(14, 0);
		return calendar.getTime();
	}
	public static boolean isAfter(Date sourceDate,Date targetDate){
		Calendar calendarS = Calendar.getInstance();
		calendarS.setTime(sourceDate);
		Calendar calendarT = Calendar.getInstance();
		calendarT.setTime(targetDate);
		
		return (calendarS.get(Calendar.YEAR)>=calendarT.get(Calendar.YEAR))&&
				(calendarS.get(Calendar.MONTH)>=calendarT.get(Calendar.MONTH))&&
				(calendarS.get(Calendar.DAY_OF_MONTH)>=calendarT.get(Calendar.DAY_OF_MONTH));
				
	}
	
	public static Date getTargetDate(Date date, int dayNumber) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(11, 0);
		calendar.set(12, 0);
		calendar.set(13, 0);
		calendar.set(14, 0);
		calendar.add(5, dayNumber);
		return calendar.getTime();
	}

	public static Date getAfterDate(int dayNumber) {
		return getTargetDate(new Date(), dayNumber);
	}

	public static Date getAfterDate(Date date, int dayNumber) {
		return getTargetDate(date, dayNumber);
	}

	public static Date getBeforeDate(int dayNumber) {
		return getTargetDate(new Date(), -dayNumber);
	}

	public static Date getBeforeDate(Date date, int dayNumber) {
		return getTargetDate(date, -dayNumber);
	}

	public static Date add(Date date, int field, int value) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(field, value);
		return c.getTime();
	}

	public static Date getAddMin(Date date, int min) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(12, min);
		return c.getTime();
	}

	public static Date getAddSecond(Date date, int second) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(13, second);
		return c.getTime();
	}

	public static Date getClosingTime(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(11, 15);
		c.set(12, 0);
		c.set(13, 0);
		return c.getTime();
	}

	public static Date getClosedTime(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(11, 15);
		c.set(12, 30);
		c.set(13, 0);
		return c.getTime();
	}

	public static long getOpenTimeToNow(Date nowDateTime) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(nowDateTime);
		c1.set(11, 9);
		c1.set(12, 30);
		c1.set(13, 0);

		Calendar c2 = Calendar.getInstance();
		c2.setTime(nowDateTime);
		c2.set(11, 11);
		c2.set(12, 30);
		c2.set(13, 0);

		Calendar c3 = Calendar.getInstance();
		c3.setTime(nowDateTime);
		c3.set(11, 13);
		c3.set(12, 0);
		c3.set(13, 0);

		Calendar c4 = Calendar.getInstance();
		c4.setTime(nowDateTime);
		c4.set(11, 15);
		c4.set(12, 0);
		c4.set(13, 0);

		long min = 0L;

		if (nowDateTime.after(c4.getTime()))
			min = 240L;
		else if (nowDateTime.after(c3.getTime())) {
			min = 120L + (nowDateTime.getTime() - c3.getTime().getTime()) / 60000L;
		} else if (nowDateTime.after(c2.getTime()))
			min = 120L;
		else if (nowDateTime.after(c1.getTime())) {
			min = (nowDateTime.getTime() - c1.getTime().getTime()) / 60000L;
		}

		return min;
	}

	public static Date DateAndTime(Date date, Date time) {
		Calendar d = Calendar.getInstance();
		d.setTime(date);
		Calendar t = Calendar.getInstance();
		t.setTime(time);

		t.set(1, d.get(1));
		t.set(2, d.get(2));
		t.set(5, d.get(5));
		return t.getTime();
	}

	public static int extract(Date date, int field) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);

		return c.get(field);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<Map<String, Date>> parseTimeRegion(String timeRegion)
			throws ParseException {
		List result = new ArrayList();
		String[] tl = timeRegion.split(",");
		for (String t : tl) {
			String[] sel = t.split("-");
			Map resultM = new HashMap();
			if ((sel != null) && (sel.length == 2)) {
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
				Date dStart = sdf.parse(sel[0]);
				Date dEnd = sdf.parse(sel[1]);
				resultM.put("START_TIME", dStart);
				resultM.put("END_TIME", dEnd);
			}
			result.add(resultM);
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	public static boolean isInTimeRegion(List<Map<String, Date>> timeRegion,
			Date time) {
		boolean result = false;
		Date nowdate = new Date();
		for (Map m : timeRegion) {
			if ((DateAndTime(nowdate, (Date) m.get("START_TIME")).getTime() <= DateAndTime(
					nowdate, time).getTime())
					&& (DateAndTime(nowdate, time).getTime() <= DateAndTime(
							nowdate, (Date) m.get("END_TIME")).getTime())) {
				result = true;
			}
		}
		return result;
	}
}
