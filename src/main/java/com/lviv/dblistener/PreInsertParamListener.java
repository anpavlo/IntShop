package com.lviv.dblistener;

import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.springframework.stereotype.Component;

import com.lviv.model.Param;
@Component
public class PreInsertParamListener implements PreInsertEventListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2326424305625198371L;

	@Override
	public boolean onPreInsert(PreInsertEvent event) {
		if (event.getEntity() instanceof Param){
			Param param = (Param) event.getEntity();
			System.out.println("PreInsert:   "+param.toString() );		
		}
		return false;
	}

}
