package com.epi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class interestCalculator {
    // @include


    public double gainCalculator(double n, double amount, int year){
        double totalAmount=amount;

        for (int i=0; i< year;i++){
            totalAmount = amount + totalAmount + (totalAmount * (n / 100) * (0.7));// 30% tax calculation and assuming you add same amount every year
        }


        return totalAmount;
    }
    public double atrInterest(double interest, double amount, int months){

        return amount*(interest/1200)/(1-(1/Math.pow((1+(interest/1200)), months)))*months;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Please select what do you want to do....\n");
        System.out.println("-----1) for gain calculation.....");
        System.out.println("-----2) for Atr interest calculation.....");
        int option = Integer.parseInt(reader.readLine());

        switch (option) {
        case 1:
            System.out.println("Enter percentage gain...");
            int n = Integer.parseInt(reader.readLine());
            System.out.println("Enter amount...");
            int amount = Integer.parseInt(reader.readLine());
            System.out.println("Enter number of years...");
            int year = Integer.parseInt(reader.readLine());
            System.out.println("The result is...." + new interestCalculator().gainCalculator(n, amount, year));
            break;
        case 2:
            System.out.println("Enter percentage gain...");
            int nn = Integer.parseInt(reader.readLine());
            System.out.println("Enter amount...");
            int ammount = Integer.parseInt(reader.readLine());
            System.out.println("Enter number of months...");
            int yyear = Integer.parseInt(reader.readLine());
            System.out.println("The result is..." + new interestCalculator().atrInterest(nn, ammount, yyear));
        default:
            break;
        }


    }

}