package ar.com.intelimanagement.service.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class MapperUtil {

	public static byte[] ObjectToBlob(Object o){	
	 ObjectMapper mapper = new ObjectMapper();
        try {
        	return mapper.writeValueAsString(o).getBytes("utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
     }
         
}
