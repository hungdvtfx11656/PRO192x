/**
 * GradeStudent
 * Assignment 2 - PRO192x
 * Hungdvt - FX11656
 * GradeStudent1 bám theo yêu cầu đề bài có các hàm
 * begin() midTerm() finalTerm() homework() report()
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class GradeStudent1 {
    static Scanner console = new Scanner(System.in);

    public static void main(String[] args) {
        begin();
        double midTerm = midTerm(console);
        double finalTerm = finalTerm(console);
        double homework = homework(console);
        report(midTerm, finalTerm, homework);
    }

    // MAIN FUNCTIONS

    /**
     * Short describe about application
     */
    public static void begin() {
        System.out.println("This program reads exam/homework scores and reports your overall course grade...");
        System.out.println();
    }

    /**
     * Prompt user input and caculate Midterm score
     * @param console user input
     * @return double Midterm score
     */
    public static double midTerm(Scanner console) {
        System.out.print("Midterm: \nWeight (0-100)? ");
        int midWeight = intRange(console, 0, 100);
        double midScore = termScore(midWeight, console);
        return midScore;
    }

    /**
     * Prompt user input and caculate Finalterm score
     * @param console user input
     * @return double Finalterm score
     */
    public static double finalTerm(Scanner console) {
        System.out.print("Finalterm: \nWeight (0-100)? ");
        int finalWeight = intRange(console, 0, 100);
        double finalScore = termScore(finalWeight, console);
        return finalScore;
    }

    /**
     * Prompt user input and caculate Homework score
     * @param console user input
     * @return double Homework score
     */
    public static double homework(Scanner console) {
        System.out.print("Homework: \nWeight (0-100)? ");
        int homeworkWeight = intRange(console, 0, 100);
        double homeworkScore = homeworkScore(homeworkWeight, console);
        return homeworkScore;
    }

    /**
     * Caculate and report result
     * @param midScore passed result from midTerm
     * @param finalScore passed result from finalTerm
     * @param homeworkScore passed result from homeWork
     */
    public static void report(double midScore, double finalScore, double homeworkScore) {
        double overall = midScore + finalScore + homeworkScore;
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


    // SUB FUNCTIONS

    /**
     * Caculate term score
     * @param weight term weight
     * @param console user input for each value
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
     * Caculate homework score
     * @param weight term weight
     * @param console user input for each value
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
     * Display number with 1 digit after decimal
     * @param value
     * @return formated value for displaying
     */
    public static double display1(double value) {
        return Math.round(value * 10.0) / 10.0;
    }

    /**
     * Display number with 0 digit after decimal
     * @param value
     * @return formated value for displaying
     */
    public static int display0(double value) {
        return (int) value;
    }

    /**
     * Limit the maximum for a passed number
     * @param value
     * @param max
     * @return self value or limited value
     */
    public static double maxCasting(double value, double max) {
        return (value > max) ? max : value;
    }

    /**
     * Validate integer input number between specific range
     * @param console user input
     * @param min
     * @param max
     * @return validated integer
     */
    public static int intRange(Scanner console, int min, int max) {
        try {
            int value = console.nextInt();
            if (value < min || value > max) {
                System.out.print("Error: value must between " + min + " and " + max + ". Try again: ");
                return intRange(console, min, max);
            } else {
                return value;
            }
        } catch (InputMismatchException e) {
            console.next();
            System.out.print("Error: wrong input. Try again: ");
            return intRange(console, min, max);
        }
    }

}