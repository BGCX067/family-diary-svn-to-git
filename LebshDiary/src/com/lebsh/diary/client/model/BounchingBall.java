package com.lebsh.diary.client.model;

public class BounchingBall {

	private final static double pulseCoefficient = .87d; // energy loss Coefficient as result of Bouncing the ground
	private final static double acceleration = -9.8d; // nature gravity near earth surface in  m/s/s
	public final static double accelerationSize = 9.8d; // nature gravity absolute
	private double initialVelocity;// initial lunching velocity vector
	
	public BounchingBall(float initialVelocity){
		this.initialVelocity = initialVelocity;
	}
	
	
	/**
	 * 
	 * @param time
	 * @return the location according the time taking in consideration the energy loss (pulseCoefficient) in each bunching 
	 * to get the Top use: time = initialVelocity/accelerationSize
	 */
	public double getLocation(double time){
		double intervalIndex=0;
		double timeElapsedEndInterval=0;
		double timeElapsedStartInterval=0;
		while(timeElapsedEndInterval<= time){
			timeElapsedStartInterval = timeElapsedEndInterval;
			timeElapsedEndInterval += 2*Math.pow(pulseCoefficient, intervalIndex)*initialVelocity/accelerationSize;
			intervalIndex++;
		}
		double timeFromLastBounch = time-timeElapsedStartInterval;
		double currentVelocity =  Math.pow(pulseCoefficient, intervalIndex-1)*initialVelocity;
		double location = currentVelocity*timeFromLastBounch + acceleration*timeFromLastBounch*timeFromLastBounch/2;
		
		return location;
	}
	
	
	
	

	
	public static void main(String[] args) {
		BounchingBall ball = new BounchingBall(15);
		for(double t = 15/accelerationSize;t<30;t+=.01){
			System.out.print(ball.getLocation(t)+" - "+t+" \n");
		}
		System.out.print(ball.getLocation(15/acceleration)+" - TOP \n");
	}
	
	
}
