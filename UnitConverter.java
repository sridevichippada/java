package com.pack;

import java.util.Scanner;

public class UnitConverter {

	public static void main(String[] args) {
		Scanner S = new Scanner(System.in);
		
		System.out.println("unit converter menu:");
		System.out.println("1.length(meters to kilometers)");
		System.out.println("2.weight(grams to kilograms)");
		System.out.println("3.Temperature(celusius to fahrenheit)");
		
		System.out.print("enter choice : ");
		int choice =S.nextInt();
		
		switch(choice) {
		
		case 1: System.out.println("enter value in meters: ");
		double meters =S.nextDouble();
		double kiloMeter= meters/1000;
		System.out.println(meters +" meter is equal to " + kiloMeter + "Kilometers");

		
		case 2:System.out.println("enter value in grams");
		double grams =S.nextDouble();
		double Kilograms = grams /1000;
        System.out.println( grams + " grams is equal to " + Kilograms  + " kilograms ");
	
		
		case 3:System.out.println("enter value in fahrenheit");
		double celusius = S.nextDouble();
        double fahrenheit = (1.8 * celusius) + 32;
        System.out.println( celusius + " celusius is equal to " + fahrenheit + " fahrenheit ");
        
		}
	}
	
}

	

