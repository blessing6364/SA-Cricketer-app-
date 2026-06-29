package com.mycompany.sacricketerapp;

public class SACricketerApp {

    public static void main(String[] args) {

        String[] batsmen = {"Jacques Kallis", "Hashim Amla", "AB de Villiers"};
        String[] stadiums = {"Kingsmead", "St Georges", "Wanderers"};

        int[][] runs = {
            {5000, 3500, 6200},
            {3800, 3700, 5000},
            {4200, 3900, 5200}
        };

        int[] totalBatsman = calculateBatsmanTotals(runs);
        int[] totalStadium = calculateStadiumTotals(runs);

        String bestStadium = findBestStadium(stadiums, totalStadium);

        printReport(batsmen, stadiums, runs, totalStadium, bestStadium);
    }

    public static int[] calculateBatsmanTotals(int[][] runs) {
        int[] totals = new int[runs.length];

        for (int i = 0; i < runs.length; i++) {
            for (int j = 0; j < runs[i].length; j++) {
                totals[i] += runs[i][j];
            }
        }
        return totals;
    }

    public static int[] calculateStadiumTotals(int[][] runs) {
        int[] totals = new int[runs[0].length];

        for (int j = 0; j < runs[0].length; j++) {
            for (int i = 0; i < runs.length; i++) {
                totals[j] += runs[i][j];
            }
        }
        return totals;
    }

    public static String findBestStadium(String[] stadiums, int[] totals) {
        int max = totals[0];
        String best = stadiums[0];

        for (int i = 1; i < totals.length; i++) {
            if (totals[i] > max) {
                max = totals[i];
                best = stadiums[i];
            }
        }
        return best;
    }

    public static void printReport(String[] batsmen, String[] stadiums, int[][] runs,
                                   int[] stadiumTotals, String bestStadium) {

        System.out.println("======================================");
        System.out.println("        RUNS SCORED REPORT");
        System.out.println("======================================\n");

        for (int i = 0; i < batsmen.length; i++) {
            System.out.println(batsmen[i] + " performance:");

            for (int j = 0; j < stadiums.length; j++) {
                System.out.println("  - " + stadiums[j] + ": " + runs[i][j]);
            }
            System.out.println();
        }

        System.out.println("======================================");
        System.out.println("TOTAL RUNS PER STADIUM");
        System.out.println("======================================");

        for (int j = 0; j < stadiums.length; j++) {
            System.out.println(stadiums[j] + ": " + stadiumTotals[j]);
        }

        System.out.println("\nBEST PERFORMING STADIUM: " + bestStadium);
        System.out.println("======================================");
    }
}
