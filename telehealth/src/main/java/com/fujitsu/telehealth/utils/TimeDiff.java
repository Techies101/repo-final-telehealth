package com.fujitsu.telehealth.utils;
import java.util.Date;

public class TimeDiff {
	public static void main(String args[]) {
		Date reminderTime = new Date(System.currentTimeMillis() - 100000000);
		Date actualTime = new Date(System.currentTimeMillis() - 500000000);
		long diffMilli = reminderTime.getTime() - actualTime.getTime();
		
		System.out.println(diffMilli);
		System.out.println();
	}
}
