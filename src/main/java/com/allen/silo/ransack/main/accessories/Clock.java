package com.allen.silo.ransack.main.accessories;

import com.allen.silo.ransack.utils.Constants;

public class Clock {

	private int quantums;
	
	public Clock(){
		this.quantums = 0;
	}
	
	public Clock(int quantums){
		this.quantums = quantums;
	}
	
	public void tick(){
		this.quantums += Constants.QUANTUM;
	}
	
	public int getSecs(){
		return this.quantums % 60;
	}
	
	public int getMins(){
		return this.quantums / 60;
	}

	public int getHours(){
		return this.quantums / 3600;
	}

	public int getDays(){
		return this.quantums / 86400;
	}

	public int getQuantums() {
		return this.quantums;
	}
}

/*
 * 
 * from UTIL import const

class Ticker():
    
    def __init__(self):
        self.count = 0
        self.timeRate = const.timeRate
    
    def tick(self, ticks):
        self.count = self.count + (self.timeRate * ticks)
    
    def getTicks(self):
        return self.count
    
    def getSecs(self):
        return self.count % 60
    
    def getMins(self):
        return self.count / 60
    
    def getHours(self):
        return self.count / 3600
    
    def getDays(self):
        return self.count / 86400
    
    def getCount(self):
        return self.count
    
    def setTimeRate(self, tr):
        self.timeRate = tr
        */
