package org.transinfo.cacis.action.init;

import javax.servlet.ServletException;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;
import org.transinfo.cacis.util.AdminParamsLoad;

public class StartupInit implements PlugIn {

	@Override
	public void destroy() {
	}

	@Override
	public void init(ActionServlet actionservlet, ModuleConfig moduleconfig)
			throws ServletException {
		
		/*System.out.println("\n\nat start of server1\n\n");
		System.out.println("\n\nat start of server2\n\n");
		System.out.println("\n\nat start of server3\n\n");
		System.out.println("\n\nat start of server4\n\n");
		
		DAOFactory.loadInstance(1);
		
		System.out.println("\n\nat start of server5\n\n");
		System.out.println("\n\nat start of server6\n\n");*/
		
		try {
			System.out.println("before load properties");
			AdminParamsLoad.loadProperties();
			System.out.println("After load properties");
			System.out.println("before HSM init");
			AdminParamsLoad.initHSM();
			System.out.println("after HSM init");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
