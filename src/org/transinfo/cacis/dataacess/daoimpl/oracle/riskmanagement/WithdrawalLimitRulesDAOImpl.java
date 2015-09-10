package org.transinfo.cacis.dataacess.daoimpl.oracle.riskmanagement;

import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.riskmanagement.WithdrawalLimitRulesDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.debug.DebugWriter;
import org.transinfo.cacis.dto.riskmanagement.WithdrawalLimitRulesDto;
import org.transinfo.cacis.dto.riskmanagement.WithdrawalLimitRulesSearchDto;

public class WithdrawalLimitRulesDAOImpl extends BaseDAOImpl implements
		WithdrawalLimitRulesDAO {

	public Collection search(WithdrawalLimitRulesSearchDto objSearchDto,
			int pageNo) throws TPlusException {

		Collection objSearchCollection = null;
		StringBuffer sbf = new StringBuffer();
		try {

			sbf.append("select af.ruleId, af.tranxCode, to_char(af.lastUpdatedDate,'dd-MM-yyyy'), ");
			sbf.append("af.lastUpdatedBy from WithdrawalLimitRulesDto af ");
			if (objSearchDto.getRuleId() > 0) {
				sbf.append("where af.ruleId =" + objSearchDto.getRuleId()
						+ " ");
			}
			if (objSearchDto.getTranxType() != null
					&& !objSearchDto.getTranxType().equals("")) {
				if (objSearchDto.getRuleId() > 0) {
					sbf.append("and af.tranxCode = '"
							+ objSearchDto.getTranxType() + "'");
				} else {
					sbf.append("where af.tranxCode = '"
							+ objSearchDto.getTranxType() + "'");
				}
			}

			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception e) {
			System.out
					.println("Error while retrieving the WithdrawalLimitRules Search Info"
							+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the WithdrawalLimitRules Search Info"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}

	public WithdrawalLimitRulesDto getWithdrawalLimitRules(String ruleId)
			throws TPlusException {
		WithdrawalLimitRulesDto apd = null;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			System.out.println(ruleId);
			// session.get give exception of class cast where rule id is integer
			// so List list = qry.list(); is used for this screen.
			// apd = (WithdrawalLimitRulesDto)
			// session.get(WithdrawalLimitRulesDto.class, ruleId);
			Query qry = session
					.createQuery("from WithdrawalLimitRulesDto where ruleId="
							+ ruleId + " ");
			List list = qry.list();
			apd = (WithdrawalLimitRulesDto) list.get(0);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

			System.out.println("Error in getWithdrawalLimitRules method" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the getWithdrawalLimitRules" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return apd;

	}

	public boolean add(WithdrawalLimitRulesDto objWithdLimRulDto)
			throws TPlusException {
		boolean bolExecute = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			session.save(objWithdLimRulDto);
			session.flush();
			bolExecute = true;
			tx.commit();
			System.out
					.println("WithdrawalLimitRulesDto object persisted to the database.");

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Exception in WithdrawalLimitRulesDAOImpl saveMethod"
							+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while saving data in WithdrawalLimitRulesDAOImpl"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return bolExecute;

	}

	public boolean update(WithdrawalLimitRulesDto objWithdLimRulDto)
			throws TPlusException {
		boolean bolExecute = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			session.update(objWithdLimRulDto);
			session.flush();
			tx.commit();
			System.out
					.println("WithdrawalLimitRulesDto object updated to the database.");
			bolExecute = true;
		}

		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error while updating WithdrawalLimitRules data "
							+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while updating WithdrawalLimitRules data" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return bolExecute;
	}

	public boolean delete(WithdrawalLimitRulesDto objWithdLimRulDto)
			throws TPlusException {
		boolean bolExecute = false;
		Transaction tx = null;

		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			session.delete(objWithdLimRulDto);
			tx.commit();
			bolExecute = true;
			System.out.println("WithdrawalLimitRules successfully deleted.");
		}
		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error while deleting WithdrawalLimitRules " + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while deleting WithdrawalLimitRules" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return bolExecute;
	}

	public int checkExistrecord(WithdrawalLimitRulesDto objDto) throws TPlusException {
		if (DebugWriter.boolDebugEnabled)
			DebugWriter.write("WITHDRAWALLIMITRULESIMPL:checkExistrecord");

		int res = 0;
		try {
			Session session = HibernetFactory.currentSession();
			System.out.println(objDto.getRuleId());
			Query qry = session.createQuery("select count(*) from WithdrawalLimitRulesDto prod where prod.ruleId=:ruleid");
			qry.setLong("ruleid", objDto.getRuleId());
			List list = qry.list();
			res = ((Integer) list.get(0)).intValue();
			System.out.println(res);
			System.out.println("After WithdrawalLimitRulesDAOImpl checkExistrecord()");
		}
		catch (Exception e) {
			System.out.println("6" + e);
		}
		finally {
			HibernetFactory.closeSession();
		}
		return res;
	}

	public static void main(String s[]) throws Exception {
		WithdrawalLimitRulesSearchDto objSearchDto = new WithdrawalLimitRulesSearchDto();
		WithdrawalLimitRulesDto objDto = new WithdrawalLimitRulesDto();
		WithdrawalLimitRulesDAOImpl objDAOImpl = new WithdrawalLimitRulesDAOImpl();
		Collection objTestCollection = null;
		boolean flag = false;
		int test = 0;
		try {
			//objDto.setRuleId(456789123);
			objSearchDto.setRuleId(456789123);
			//objDto = objDAOImpl.getWithdrawalLimitRules("456789123");
			//flag = objDAOImpl.delete(objDto);
			//test = objDAOImpl.checkExistrecord(objDto);
			objTestCollection = objDAOImpl.search(objSearchDto, 0);
			System.out.println("objTestCollection => "+objTestCollection.size());
			//System.out.println("flag => "+flag);
			//System.out.println("test => "+test);
			System.out.println("objDto => "+objDto.getRuleId());
			System.out.println("...3...");
		} catch (Exception e) {
			System.out.println("Error WithdrawalLimitRules: " + e);
			throw e;
		}
	}
	
}
