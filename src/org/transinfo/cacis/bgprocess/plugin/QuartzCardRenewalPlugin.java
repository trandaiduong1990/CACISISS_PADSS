package org.transinfo.cacis.bgprocess.plugin;

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
import org.transinfo.cacis.bgprocess.CardRenewalProcess;
import org.transinfo.cacis.bgprocess.scheduler.CardRenewalScheduler;
import org.transinfo.cacis.bgprocess.utils.BgProcessParams;
import org.transinfo.cacis.constants.ICacisiss;

@SuppressWarnings("unchecked")
public class QuartzCardRenewalPlugin implements PlugIn {
	
	private static final Logger cardRenewalLog = Logger.getLogger("CardRenewalLog");

	@Override
	public void destroy() {
		// null
	}

	@Override
	public void init(ActionServlet servlet, ModuleConfig config)
			throws ServletException {

		CardRenewalProcess task = new CardRenewalProcess();

		JobDetail job = newJob(CardRenewalScheduler.class)
						.withIdentity("cardRenewalProcess", "cacisiss")
						.build();

		Map dataMap = job.getJobDataMap();
		dataMap.put("cardRenewalTask", task);

		try {
			
			// configure the scheduler time, run it every 5 seconds
			CronTrigger trigger = newTrigger()
									.withIdentity("triggerCardRenewal", "cacisiss")
									//.withSchedule(cronSchedule("0/5 * * * * ?")) // every 5 seconds
									//.withSchedule(cronSchedule("0 15 10 * * ?")) // every day at 10.15 am
									//.withSchedule(cronSchedule("0 0 22 * * ?")) // every day at 10 pm
									//.withSchedule(cronSchedule("0 0 23 L * ?")) // Fire at 11 pm on the last day of every month
									//.withSchedule(cronSchedule("0 35 0 * * ?")) // every day at 12.35 am
									.withSchedule(cronSchedule(BgProcessParams.getPropertyValue(ICacisiss.IProperty.BG_TIME_CARD_RENEWAL))) // value getting from properties file
									.forJob("cardRenewalProcess", "cacisiss")
									.build();

			// schedule it
			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
			scheduler.scheduleJob(job, trigger);

		} catch (ParseException e) {
			cardRenewalLog.error(new Object(), e);
		} catch (SchedulerException e) {
			cardRenewalLog.error(new Object(), e);
		} catch (Exception e) {
			e.printStackTrace();
			cardRenewalLog.error(new Object(), e);
		}

	}

}
