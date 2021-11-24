package com.fujitsu.telehealth.utils;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.fujitsu.telehealth.dao.AppPatientImplementation;
import com.fujitsu.telehealth.model.PatientModel;

public class NotifBackgroundTask extends SQLQuery implements Job {

	AppPatientImplementation App = new AppPatientImplementation();
	PatientModel patient = new PatientModel();

	public static void main(String args[]) throws SchedulerException {

		JobDetail job = JobBuilder.newJob(NotifBackgroundTask.class).build();

//		Trigger t1 = TriggerBuilder.newTrigger().withIdentity("AppointmentTrigger").startNow().build();
//		Trigger t2 = TriggerBuilder.newTrigger().withIdentity("CronTrigger").withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *")).build();
		
		Trigger t3 = TriggerBuilder.newTrigger().withIdentity("CronTrigger")
				.withSchedule(SimpleScheduleBuilder
						.simpleSchedule()
						.withIntervalInSeconds(05)
						.repeatForever()).build();
		
		Scheduler sc = StdSchedulerFactory.getDefaultScheduler();

		sc.start();
		sc.scheduleJob(job, t3);
	}

	public static Boolean getTimeDiff(String th_time, String th_date_shcedule) throws ParseException {

		Boolean invokeReminder = false;
		SimpleDateFormat format = new SimpleDateFormat("hh:mm aa");
		SimpleDateFormat df = new SimpleDateFormat("MMMM dd, YYYY");
		String currentTime = format.format(new Date());
		Date d1 = null;
		Date d2 = null;

		try {
			d1 = format.parse(currentTime);
			d2 = format.parse(th_time);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		long diff = d2.getTime() - d1.getTime();
		long actDiff = TimeUnit.MILLISECONDS.toHours(diff);

		invokeReminder = (actDiff <= 2 && actDiff >= 0 && th_date_shcedule.equals(df.format(new Date()))) ? true : false;
		return invokeReminder;
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			if (App.getSchedule(patient.getTh_patientID()) != null) return;
			System.out.println(new Date());
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}
	}

}
