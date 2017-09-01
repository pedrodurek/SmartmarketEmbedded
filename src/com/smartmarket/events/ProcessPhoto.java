package com.smartmarket.events;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Base64;

import org.apache.log4j.Logger;

import com.owlike.genson.Genson;
import com.smartmarket.display.LCD;
import com.smartmarket.request.PhotoDTO;
import com.smartmarket.request.ProductDTO;
import com.smartmarket.request.RequestSender;
import com.smartmarket.utils.XMLConfig;
import com.smartmarket.utils.XMLType;

public class ProcessPhoto extends Task implements Runnable {
	
	private static Logger log =  Logger.getLogger(TakePhoto.class.getName());
	
	public ProcessPhoto(int intervalTime) {
		super(intervalTime);
	}

	public void run() {
		
		LCD lcd = null;
		try {
			lcd = new LCD(0x27);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		RequestSender<PhotoDTO> request = new RequestSender<>();
		while (true) {
			
			if (System.currentTimeMillis() > (getLastRunTime()+getIntervalTime()*1000)) {
				
				File file = new File(XMLConfig.getConfig(XMLType.PendentPhotosPath)+"photo1.jpg");
				if (file.exists()) {
					
					PhotoDTO photoDto = new PhotoDTO();
					try {
						
						log.info("Processing Photo!");
						byte[] photo = Files.readAllBytes(Paths.get(file.getPath()));
						photoDto.setPhoto(new String(Base64.getEncoder().encode(photo), "UTF-8"));
						
					} catch (IOException e) {
						e.printStackTrace();
					} 
					ProductDTO productDto = new Genson().deserialize(request.sendRequest(photoDto, XMLConfig.getConfig(XMLType.ProcessPhotoURL)), ProductDTO.class);
					if (productDto != null) {
						file.delete();
					}
					lcd.clear();
					lcd.writeProduct(productDto.getName());
					lcd.writePrice(productDto.getPrice());
//					lcd.write(productDto.getName(), 0, Position.Left);
//					lcd.write(String.valueOf(productDto.getPrice()), 1, Position.Left);
					log.info(productDto.getName());
					DecimalFormat df = new DecimalFormat("0.00");
					log.info(df.format(productDto.getPrice()));
					
					
				}
				updateLastRunTime();
				
			}
			
		}
		
	}

}
