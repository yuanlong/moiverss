package com.yuanlong.moiverss.base;

import java.util.Observable;

import org.apache.velocity.app.VelocityEngine;

import com.yuanlong.moiverss.utils.VelocityUtils;




public class VelocityObservable extends Observable {

	VelocityEngine engine;

	public VelocityObservable() {		
		addObservers();		
	}
	
	public void inject() {
		VelocityEngine engine = (VelocityEngine) AppContext.getSpringCtx().getBean("velocityEngine");
		setEngine(engine);
	}

	private void addObservers() {
		addObserver(VelocityUtils.getInstance());
	}

	public VelocityEngine getEngine() {
		return engine;
	}

	public void setEngine(VelocityEngine engine) {
		this.engine = engine;
		setChanged();
		notifyObservers(engine);
	}
}
