package hu.bme.mit.train.controller;


import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import hu.bme.mit.train.interfaces.TrainController;

public class TrainControllerImpl implements TrainController {

	private int step = 0;
	private int referenceSpeed = 0;
	private int speedLimit = 0;
	private Table<Integer, Integer, Integer> tachograph;

	@Override
	public void followSpeed() {
		if (referenceSpeed + step< 0) {
			referenceSpeed = 0;
		} else {
			referenceSpeed += step;
		}

		enforceSpeedLimit();
	}

	@Override
	public int getReferenceSpeed() {
		return referenceSpeed;
	}

	@Override
	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		enforceSpeedLimit();
		
	}

	private void enforceSpeedLimit() {
		if (referenceSpeed > speedLimit) {
			referenceSpeed = speedLimit;
		}
	}

	@Override
	public void setJoystickPosition(int joystickPosition) {
		this.step = joystickPosition;		
	}
	
	public void makeatachograph() {
		tachograph = HashBasedTable.create();
		for (int rid = 0;rid < 3;rid++){
			for (int cid = 0;cid < 3;cid++){
				tachograph.put(rid,cid,10);
			}
		}
	}
	public int gettachographelementnum() {
		return tachograph.size();
	}

}
