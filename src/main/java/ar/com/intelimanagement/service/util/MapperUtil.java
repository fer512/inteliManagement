package ar.com.intelimanagement.service.util;

import java.sql.Blob;

import javax.sql.rowset.serial.SerialBlob;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class MapperUtil {

	public static Blob ObjectToBlob(Object o){	
	 ObjectMapper mapper = new ObjectMapper();
        try {
        	byte[] b = mapper.writeValueAsString(o).getBytes("utf-8");
        	return new SerialBlob(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
     }
         
}
