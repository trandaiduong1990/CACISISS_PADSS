package org.transinfo.cacis.test;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.text.ParseException;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.transinfo.cacis.bgprocess.utils.BgProcessParams;
import org.transinfo.cacis.constants.ICacisiss;

@SuppressWarnings("unchecked")
public class QuartzPlugin implements PlugIn {

	@Override
	public void destroy() {
		// null
	}

	@Override
	public void init(ActionServlet servlet, ModuleConfig config)
			throws ServletException {

		SchedularTask task = new SchedularTask();

		// define the job and tie it to our HelloJob class
		JobDetail job = newJob(SchedularJob.class)
						.withIdentity("myJob", "group1") // name "myJob", group "group1"
						.build();

		Map dataMap = job.getJobDataMap();
		dataMap.put("schedulerTask", task);

		try {
			// configure the scheduler time, run it every 5 seconds
			CronTrigger trigger = newTrigger()
								.withIdentity("triggerMyJob", "group1")
								//.withSchedule(cronSchedule("0/5 * * * * ?")) // every 5 seconds
								//.withSchedule(cronSchedule("0 0 10 * * ?")) // every day at 10 am
								//.withSchedule(cronSchedule("0 0 22 * * ?")) // every day at 10 pm
								//.withSchedule(cronSchedule("0 35 0 * * ?")) // every day at 10 pm
								.withSchedule(cronSchedule(BgProcessParams.getPropertyValue(ICacisiss.IProperty.BG_TIME_TEST))) // value getting from properties file
								.forJob("myJob", "group1")
								.build();

			// schedule it
			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
			scheduler.scheduleJob(job, trigger);

		} catch (ParseException e) {
			e.printStackTrace();
		} catch (SchedulerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
