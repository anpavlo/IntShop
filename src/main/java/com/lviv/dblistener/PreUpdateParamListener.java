package com.lviv.dblistener;

import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;

import com.lviv.model.Param;

public class PreUpdateParamListener implements PreUpdateEventListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8844348165383568751L;

	@Override
	public boolean onPreUpdate(PreUpdateEvent event) {
		
		if (event.getEntity() instanceof Param){
			Param param = (Param) event.getEntity();
			System.out.println("PreUpdate:   "+param.toString() );		
		}

		return false;
	}

}
