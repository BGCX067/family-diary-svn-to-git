package com.lebsh.diary.client.ui.components;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.lebsh.diary.client.AppController;
import com.lebsh.diary.client.model.BounchingBall;

public class BounchingPointerPanel extends Composite{
	private BallTimer ballTimer = new BallTimer();
	private double time = 11/BounchingBall.accelerationSize; //set initial time of Top
	private BounchingBall ballPos = new BounchingBall(11);// set the initial velocity to 11m/s = 39.6 km/h
	private Image ballImage = new Image(AppController.getClientFactory().getImageResource().pointer());
	private AbsolutePanel absolutePanel = new AbsolutePanel();
	 
	
	public BounchingPointerPanel(){
		 absolutePanel.setSize("650px", "16px");
		 absolutePanel.add(ballImage);
		 initWidget(absolutePanel);
//		 absolutePanel.getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
//		 absolutePanel.getElement().getStyle().setBorderWidth(1, Unit.PX);
//		 setSize("100%", "100%");
	}
	
	public void start(){
		ballTimer.scheduleRepeating(25); // animating 40 frames per second
	}
	
	
	
	private class BallTimer extends Timer{

		@Override
		public void run() {
			 absolutePanel.setWidgetPosition(ballImage, 586-(int)(100d*ballPos.getLocation(time)),0 );
			 time+=.025;
			 if(time > 17){
				cancel();
			 }
			
		}
		
	}
}
