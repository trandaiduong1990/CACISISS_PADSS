package org.transinfo.cacis.billing.plugin;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.text.ParseException;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.transinfo.cacis.bgprocess.utils.BgProcessParams;
import org.transinfo.cacis.billing.BillingSystem;
import org.transinfo.cacis.billing.scheduler.SchedulerJob;
import org.transinfo.cacis.constants.ICacisiss;

@SuppressWarnings("unchecked")
public class QuartzPlugin implements PlugIn {
	
	private static final Logger billLog = Logger.getLogger("BillLog");

	@Override
	public void destroy() {
		// null
	}

	@Override
	public void init(ActionServlet servlet, ModuleConfig config)
			throws ServletException {

		BillingSystem task = new BillingSystem();

		// define the job and tie it to our SchedulerJob class
		JobDetail job = newJob(SchedulerJob.class)
						.withIdentity("billingSystem", "cacisiss") // name "myJob", group "group1"
						.build();

		Map dataMap = job.getJobDataMap();
		dataMap.put("schedulerTask", task);

		try {
			// configure the scheduler time, run it every 5 seconds
			CronTrigger trigger = newTrigger()
								.withIdentity("triggerBillingSystem", "cacisiss")
								//.withSchedule(cronSchedule("0/5 * * * * ?")) // every 5 seconds
								//.withSchedule(cronSchedule("0 15 10 * * ?")) // every day at 10.15 am
								//.withSchedule(cronSchedule("0 0 20 * * ?")) // every day at 8 pm
								//.withSchedule(cronSchedule("0 35 0 * * ?")) // every day at 12.35 am
								.withSchedule(cronSchedule(BgProcessParams.getPropertyValue(ICacisiss.IProperty.BG_TIME_BILLING))) // value getting from properties file
								.forJob("billingSystem", "cacisiss")
								.build();

			// schedule it
			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
			scheduler.scheduleJob(job, trigger);

		} catch (ParseException e) {
			e.printStackTrace();
			billLog.error(new Object(), e);
		} catch (SchedulerException e) {
			e.printStackTrace();
			billLog.error(new Object(), e);
		} catch (Exception e) {
			e.printStackTrace();
			billLog.error(new Object(), e);
		}
	}

}
