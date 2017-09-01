package com.smartmarket.display;

import java.text.DecimalFormat;

import com.pi4j.component.lcd.LCDTextAlignment;
import com.pi4j.component.lcd.impl.I2CLcdDisplay;
import com.pi4j.io.i2c.I2CBus;

public class LCD extends I2CLcdDisplay {
	
	public LCD(int i2cAdress) throws Exception {
		super(2, 16, I2CBus.BUS_1, i2cAdress, 3, 0, 1, 2, 7, 6, 5, 4);
		// TODO Auto-generated constructor stub
	}
	
	public void init() {

        clear();
        write(0, "Smartmarket", LCDTextAlignment.ALIGN_CENTER);
        write(1, "Inicializando...", LCDTextAlignment.ALIGN_CENTER);
		
	}
	
	public void writeProduct(String product) {
		write(0, product, LCDTextAlignment.ALIGN_CENTER);
	}
	
	public void writePrice(double price) {
		
		DecimalFormat df = new DecimalFormat("0.00");
		write(1, "R$ "+df.format(price), LCDTextAlignment.ALIGN_CENTER);
		
	}

}
