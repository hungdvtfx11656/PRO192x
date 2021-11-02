/**
 * GradeStudent
 * Assignment 2 - PRO192x
 * Hungdvt - FX11656
 * GradeStudent2 bám theo yêu cầu nâng cao của đề bài
 * đảm bảo người sử dụng nhập tổng weight luôn bằng 100
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class GradeStudent2 {
    static Scanner console = new Scanner(System.in);
    public static void main(String[] args) {
        int totalWeight = 0;

        // Startup prompt
        System.out.println("This program reads exam/homework scores and reports your overall course grade...");
        System.out.println();

        // Midterm input & caculating
        System.out.println("Midterm: ");
        int midWeight = weightRange(console, totalWeight);
        totalWeight += midWeight;
        double midScore = termScore(midWeight, console);

        // Finalterm input & caculating
        System.out.println("Final: ");
        int finalWeight = weightRange(console, totalWeight);
        totalWeight += finalWeight;
        double finalScore = termScore(finalWeight, console);

        // Homework input & caculating
        System.out.println("Homework: ");
        int homeworkWeight = homeworkWeight(console, totalWeight);
        double homeworkScore = homeworkScore(homeworkWeight, console);

        // Report
        double overall = midScore + finalScore + homeworkScore;
        report(overall);
    }

    /**
     * Caculate term score base on weight
     * @param weight weight of term in percent
     * @param console user input
     * @return weighted score
     */
    public static double termScore(int weight, Scanner console) {
        // Score earned input
        System.out.print("Score earned? ");
        int score = intRange(console, 0, 100);
        // Shift amount input
        System.out.print("Were scores shifted (1=yes, 2=no)? ");
        int shift = intRange(console, 1, 2);
        int shiftAmount;
        if (shift == 1) {
            System.out.print("Shift amount? ");
            shiftAmount = intRange(console, 0, 50);
        } else {
            shiftAmount = 0;
        }
        // Result progress
        double totalPoints = maxCasting(score + shiftAmount, 100) ;
        double weightedScore = totalPoints * weight / 100;
        System.out.println("Total points = " + display0(totalPoints) + " / 100");
        System.out.println("Weighted score = " + display1(weightedScore) + " / " + weight);
        System.out.println("");
        return weightedScore;
    }

    /**
     * Caculate homework score base on weight
     * @param weight weight of homework in percent
     * @param console user input
     * @return weighted score
     */
    public static double homeworkScore(int weight, Scanner console) {
        // Assignment input
        System.out.print("Number of assignments? ");
        int asmCount = intRange(console, 0, 10);
        double asmScoreSum = 0;
        double asmWeightSum = 0;
        for (int i = 1; i <= asmCount; i++) {
            System.out.print("Assignment " + i + " score and max? ");
            int asmScore = intRange(console, 0, 100);
            int asmWeight = intRange(console, asmScore, 100);
            asmScoreSum += asmScore;
            asmWeightSum += asmWeight;
        }
        double asmScoreMax = maxCasting(asmScoreSum, 150);
        // Section input
        System.out.print("How many sections did you attend? ");
        int attend = intRange(console, 0, 10);
        double sectionPoints = attend * 5;
        double sectionMax = maxCasting(sectionPoints, 30);
        // Result progress
        double totalWeight = asmWeightSum + 30;
        double totalPoints = asmScoreMax + sectionMax;
        double weightedScore = totalPoints * weight / totalWeight;
        System.out.println("Section points = " + display0(sectionMax) + " / 30");
        System.out.println("Total points = " + display0(totalPoints) + " / " + display0(totalWeight));
        System.out.println("Weighted score = " + display1(weightedScore) + " / " + weight);
        System.out.println("");
        return weightedScore;
    }

    /**
     * Report result base on overall score
     * @param overall total weighted scores
     */
    public static void report(double overall) {
        double GPA;
        String comment;
        if (overall < 60) {
            GPA = 0;
            comment = "bad";
        } else if (overall < 75) {
            GPA = 0.7;
            comment = "good";
        } else if (overall < 85) {
            GPA = 2.0;
            comment = "great";
        } else {
            GPA = 3.0;
            comment = "excellent";
        }
        System.out.println("Overall percentage = " + display1(overall));
        System.out.println("Your grade will be at least = " + display1(GPA));
        System.out.println("This is " + comment + "!");
    }


    /**
     * Display double number with 1 digit after decimal
     * @param value double number
     * @return double with 1 degit after decimal
     */
    public static double display1(double value) {
        return Math.round(value * 10.0) / 10.0;
    }

    /**
     * Display double number with 0 digit after decimal
     * @param value double number
     * @return double with 0 degit after decimal
     */
    public static int display0(double value) {
        return (int) value;
    }


    /**
     * Set maximum value for double number
     * @param value double number
     * @return maximum value or
     */
    public static double maxCasting(double value, double max) {
        return (value > max) ? max : value;
    }

    /**
     * Validate integer input between range
     * @param console user input
     * @param min maximum input number
     * @param max minimun input number
     * @return validated integer
     */
    public static int intRange(Scanner console, int min, int max) {
        int value;
        try {
            value = console.nextInt();
            if (value < min || value > max) {
                System.out.print("Error: value must between " + min + " and " + max + ". Try again: ");
                return intRange(console, min, max);
            } else {
                return value;
            }
        } catch (InputMismatchException e) {
            console.next();
            System.out.print("Error: wrong input. Try again: ");
        }
        return intRange(console, min, max);
    }

    /**
     * Validate integer input for weight range
     * User can pass value as many times as they want
     * as long as the the total does not exceed 100
     * @param console user input
     * @param total outside variable for counting total weight
     * @return validated integer number
     */
    public static int weightRange(Scanner console, int total) {
        // Set min and max for each weight input
        int min = 0;
        int max = 100;
        System.out.print("Weight (0 - 100)? ");
        int value;
        try {
            value = console.nextInt();
            int remain = 100 - total;
            if (value < min || value > max) {
                System.out.println("Error: value must between " + min + " and " + max + ". Try again.");
                return weightRange(console, total);
            }
            if (value > remain) {
                System.out.println("Error: you have only " + remain + "/100 remaining weight. Try a smaller value.");
                return weightRange(console, total);
            }
            return value;
        } catch (InputMismatchException e) {
            console.next();
            System.out.println("Error: wrong input. Try again.");
        }
        return weightRange(console, total);
    }

    /**
     * Validate integer input for weight range
     * This is the last value of weight then user have to
     * pass the exactly ramaining value
     * @param console user input
     * @param total outside variable for counting total weight
     * @return validate integer number
     */
    public static int homeworkWeight(Scanner console, int total) {
        // User input prompt
        System.out.print("Weight (0 - 100)? ");
        int value;
        try {
            value = console.nextInt();
            int remain = 100 - total;
            if (value != remain) {
                System.out.println("Error: you must enter exactly " + remain + " remaining weight. Try again.");
                homeworkWeight(console, total);
            }
            return remain;
        } catch (InputMismatchException e) {
            console.next();
            System.out.println("Error: wrong input. Try again: ");
        }
        return homeworkWeight(console, total);
    }
}