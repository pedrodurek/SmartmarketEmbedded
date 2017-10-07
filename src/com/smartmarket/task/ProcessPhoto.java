package com.smartmarket.task;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import org.apache.log4j.Logger;

import com.owlike.genson.Genson;
import com.smartmarket.display.LCD;
import com.smartmarket.request.PhotoDTO;
import com.smartmarket.request.ProductDTO;
import com.smartmarket.request.RequestSender;
import com.smartmarket.utils.XMLConfig;
import com.smartmarket.utils.XMLType;


public class ProcessPhoto extends Task {
	
	private static Logger log =  Logger.getLogger(ProcessPhoto.class.getName());
	
	private ProcessPhoto nextTask;
	private int indexImage;
	private LCD lcd;
	
	public ProcessPhoto(int address, int indexImage, int intervalTime) {
		
		super(intervalTime);
		this.indexImage = indexImage+1;
		try {
			
			this.lcd = new LCD(address);
			lcd.init();
			
		} catch (Exception e) {
			log.error("Failed to start the LCD display "+address, e);
		}
		
	}
	
	@Override
	public void runTask() {
		
		File file = new File(XMLConfig.getConfig(XMLType.PendentPhotosPath)+"image"+indexImage+".jpg");
		if (file.exists()) { // Check photo availability in order to process it
			
			PhotoDTO photoDto = new PhotoDTO();
			try {
				
				log.info("Processing Photo!");
				byte[] photo = Files.readAllBytes(Paths.get(file.getPath()));
				photoDto.setPhoto(new String(Base64.getEncoder().encode(photo), "UTF-8"));
				
			} catch (IOException e) {
				log.error("Error reading photo", e);
			} 
			RequestSender<PhotoDTO> request = new RequestSender<>();
			ProductDTO productDto = new Genson().deserialize(request.sendRequest(photoDto, XMLConfig.getConfig(XMLType.ProcessPhotoURL)), ProductDTO.class);
			lcd.clear();
			if (productDto != null) {
				
				log.info("Updating display...");
				file.delete();
				if (productDto.getName() != null && productDto.getName().length() > 0) {
					lcd.writeProduct(productDto.getName());
				}
				if (productDto.getPrice() >= 0.01) {
					lcd.writePrice(productDto.getPrice());
				}
				
			}
			
		}
		
	}

	public ProcessPhoto getNextTask() {
		return nextTask;
	}

	public void setNextTask(ProcessPhoto nextTask) {
		this.nextTask = nextTask;
	}
	
}