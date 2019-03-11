package com.hjkj.job;

import java.text.ParseException;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import com.aspire.boc.util.ResourceManager;

public class InitQuartzJob {

	private static ResourceManager rm = ResourceManager.getInstance();

	public InitQuartzJob() {}
	
	public void initJob() {

		System.out.println("=================================始初始化定时任务");
		SchedulerFactory schedFact = new StdSchedulerFactory();
		try {
			Scheduler Sched = schedFact.getScheduler();
			String[] jobs = Sched.getJobNames(Scheduler.DEFAULT_GROUP);
			Trigger trigger = null;
			if (jobs.length == 0) {
				
				
				
				
//				JobDetail shuttleBusAlertJob = new JobDetail("shuttleBusAlertJob", Scheduler.DEFAULT_GROUP, ShuttleBusAlertJob.class);
//				
//				Trigger shuttleBusTriger = null;
//				if (!"-".equals(rm.getValue("Timer.shuttleBusTriger"))) {
//					shuttleBusTriger = new CronTrigger(
//							"shuttleBusAlertJob",
//							Scheduler.DEFAULT_GROUP,
//							rm.getValue("Timer.shuttleBusTriger"));
//				}
//				
//				if (shuttleBusTriger != null) {
//					Sched.scheduleJob(shuttleBusAlertJob, shuttleBusTriger);
//				}
//				
			}
			
			Sched.start();
		} catch (SchedulerException e) {
			Logger.getLogger(getClass()).error(
					"InitQuartzJob.main SchedulerException:" + e.getMessage(),
					e);
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(
					"InitQuartzJob.main Exception:" + e.getMessage(), e);
		}
	
	}
}
