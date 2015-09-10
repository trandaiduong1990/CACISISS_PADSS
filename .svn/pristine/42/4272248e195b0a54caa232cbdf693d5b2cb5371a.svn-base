package org.transinfo.cacis.dataacess.dao.applicationforms;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.applicationforms.CardGradeFormDto;
import org.transinfo.cacis.dto.applicationforms.CardGradeFormSearchDto;

public interface CardGradeFormDAO extends BaseDAO
{

    //methods for CardGradeForm and for getting CustomerServiceData Bean
	 public Collection search(CardGradeFormDto objDto)throws TPlusException;
   	 public boolean add(CardGradeFormDto objDto) throws TPlusException;
	//methods for CardGradeFormProcess
	 public Collection processSearch(CardGradeFormSearchDto objDto,int pageNo)throws TPlusException;
	 public CardGradeFormDto getCardGradeFormDto(String applicationId) throws TPlusException;
	 public boolean update(CardGradeFormDto objDto) throws TPlusException;
	 public boolean accept(CardGradeFormDto objDto) throws TPlusException;
     public boolean reject(CardGradeFormDto objDto) throws TPlusException;
    
      //for checking Existing records 
     public int checkExistrecord(Object commobj)throws TPlusException;
     
     
     
}