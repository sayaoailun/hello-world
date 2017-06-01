package com.dc.jersey;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("demo")
public class RestDemo {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GET
	@Path("get")
	public String get() {
		return "hello";
	}
	
	@POST
	@Path("post")
    @Consumes({MediaType.APPLICATION_XML, MediaType.MULTIPART_FORM_DATA}) 
	public String post(@FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail) {
		log.info("result: {}", fileDetail);
		try {
			OutputStream out = new FileOutputStream(new File(fileDetail.getFileName()));
			int i = 0;
			byte[] bytes = new byte[1024];
			while ((i = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, i);
			}
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true + "";
	}
	
	@POST
	@Path("test")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public String registPlugin(@FormDataParam("file") InputStream is,
			@FormDataParam("file") FormDataContentDisposition disposition) {
//		System.out.println("test upload......");
		log.info("result: {}", disposition);
		return "true";
	}

}
