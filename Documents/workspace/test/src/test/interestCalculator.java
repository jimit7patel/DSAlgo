package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class interestCalculator {
    // @include


    public double gainCalculator(double n, double initialamount, double incrementalamount, int year) {
        double totalAmount = initialamount - incrementalamount;

        for (int i=0; i< year;i++){
            totalAmount = incrementalamount + totalAmount + (totalAmount * (n / 100));
            // tax calculation and assuming you add same amount every year
            // totalAmount = totalAmount + (totalAmount * (n / 100)); // no tax
        }


        return totalAmount;
    }

    public double WeeklyGainCalculator(double n, double amount, int weeks) {
        double totalAmount = amount;

        for (int i = 0; i < weeks; i++) {
            // totalAmount = amount + totalAmount + (totalAmount * (n / 100) * (0.7));// 30%
            // tax calculation and assuming you add same amount every year
            totalAmount = totalAmount + (totalAmount * (n / 100)); // no tax
        }

        return totalAmount;
    }

    public double WeeklyGainWithoutCompoundCalculator(double n, double amount, int weeks) {
        double totalAmount = amount;
        for (int i = 0; i < weeks; i++) {
            // totalAmount = amount + totalAmount + (totalAmount * (n / 100) * (0.7));// 30%
            // tax calculation and assuming you add same amount every year
            totalAmount += (amount * (n / 100)); // no tax

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
        System.out.println("-----3) for Weekly Gain calculation.....");
        System.out.println("-----4) for Weekly Gain Without Compound Calculator.....");
        int option = Integer.parseInt(reader.readLine());

        switch (option) {
        case 1:
            System.out.println("Enter percentage gain...");
            int n = Integer.parseInt(reader.readLine());
            System.out.println("Enter initial amount...");
            int amount = Integer.parseInt(reader.readLine());
            System.out.println("Enter incrmental amount...");
            int ammount = Integer.parseInt(reader.readLine());
            System.out.println("Enter number of years...");
            int year = Integer.parseInt(reader.readLine());
            System.out.println("The result is...." + new interestCalculator().gainCalculator(n, amount, ammount, year));
            break;
        case 2:
            System.out.println("Enter percentage gain...");
            int nn = Integer.parseInt(reader.readLine());
            System.out.println("Enter amount...");
            ammount = Integer.parseInt(reader.readLine());
            System.out.println("Enter number of months...");
            int yyear = Integer.parseInt(reader.readLine());
            System.out.println("The result is..." + new interestCalculator().atrInterest(nn, ammount, yyear));
        case 3:
            System.out.println("Enter percentage gain...");
            int nnn = Integer.parseInt(reader.readLine());
            System.out.println("Enter amount...");
            int amountt = Integer.parseInt(reader.readLine());
            System.out.println("Enter number of Weeks...");
            int yearr = Integer.parseInt(reader.readLine());
            System.out.println("The result is...." + new interestCalculator().WeeklyGainCalculator(nnn, amountt, yearr));
            break;
        case 4:
            System.out.println("Enter percentage gain...");
            nnn = Integer.parseInt(reader.readLine());
            System.out.println("Enter amount...");
            amountt = Integer.parseInt(reader.readLine());
            System.out.println("Enter number of Weeks...");
            yearr = Integer.parseInt(reader.readLine());
            System.out.println("The result is...." + new interestCalculator().WeeklyGainWithoutCompoundCalculator(nnn, amountt, yearr));
        default:
            break;
        }


    }

}