package com.demo.accelerators;

import com.demo.objectRepository.CommonObjects;
import com.demo.objectRepository.MobileObjects;
import com.demo.objectRepository.WebObjects;

public class ObjectFactory {
	
		   //use getShape method to get object of type shape 
		   public CommonObjects getObject(String view){
		      if(view == null){
		         return null;
		      }		
		      if(view.equalsIgnoreCase("MOBILE")){
		         return new MobileObjects();
		      } else if(view.equalsIgnoreCase("WEB")){
		         return new WebObjects();
		      }
		      return null;
		   }
		}
