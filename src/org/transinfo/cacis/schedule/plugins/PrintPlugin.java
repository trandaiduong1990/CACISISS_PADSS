package org.transinfo.cacis.schedule.plugins;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.transinfo.cacis.schedule.jobs.SchedulerJob;

import com.sun.webkit.graphics.WCGraphicsContext;
import com.sun.webkit.plugin.Plugin;
import com.sun.webkit.plugin.PluginListener;

public class PrintPlugin implements PlugIn {

	public void activate(Object arg0, PluginListener arg1) {

	}

	@Override
	public void destroy() {

	}

	@Override
	public void init(ActionServlet arg0, ModuleConfig arg1)
			throws ServletException {
		JobDetail job = JobBuilder.newJob(SchedulerJob.class)
				.withIdentity("anyJobName", "group1").build();
	 
			try {
	 
				Trigger trigger = TriggerBuilder
					.newTrigger()
					.withIdentity("anyTriggerName", "group1")
					.withSchedule(
						CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
					.build();
	 
				Scheduler scheduler = new StdSchedulerFactory().getScheduler();
				scheduler.start();
				scheduler.scheduleJob(job, trigger);
	 
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
	}
	
}
