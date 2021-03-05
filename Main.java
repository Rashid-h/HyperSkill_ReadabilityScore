package readability;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static int characters;
    static int words;
    static int sentences;
    static int syllables;
    static int polysyllables;
    static int letters;

    public static void main(String[] args) throws FileNotFoundException {
                Scanner scanner = new Scanner(new File(args[0]));
                Scanner sc = new Scanner(System.in);
                String text = scanner.nextLine();
                System.out.println("The text is:\n" + text + "\n");
                wordProcessing(text);
                System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all):");
                switch (sc.nextLine()) {
                    case "ARI":
                        System.out.println(automatedReadabilityIndex());
                        break;
                    case "FK":
                        System.out.println(fleschKincaidReadability());
                        break;
                    case "SMOG":
                        System.out.println(smogGrade());;
                        break;
                    case "CL":
                        System.out.println(colemanLiauIndex());
                        break;
                    case "all":
                        System.out.println(automatedReadabilityIndex());
                        System.out.println(fleschKincaidReadability());
                        System.out.println(smogGrade());
                        System.out.println(colemanLiauIndex());
                        break;
                }
                sc.close();
                scanner.close();
    }

    public static void wordProcessing(String t) {
        characters = t.replaceAll("\\s","").split("").length;
        words = t.split("\\s").length;
        sentences = t.split("[.?!]").length;
        letters = t.replaceAll("\\W","").split("").length;
        syllables = 0;
        polysyllables = 0;
        String[] word = t.replaceAll("you", "a").replaceAll("[aeiouyAEIOUY]{2}", "a").replaceAll("[e][,.?!\\s]", " ").replaceAll("[,.?!]", " ").split("[\\s]");
        for (int i = 0; i < word.length; i++) {
            int x = 0;
            String temp = word[i];
            System.out.println(temp + "--------");

               Pattern patternS1 = Pattern.compile("[aeiouyAEIOUY]");
               Pattern patternS2 = Pattern.compile("\\b[qwrtplkjhgfdszxcvbnmMNBVCXZSDFGHJKLPTRW]{2}\\b");
               Matcher matcherS1 = patternS1.matcher(temp);
               Matcher matcherS2 = patternS2.matcher(temp);
               while (matcherS1.find()) {
                   syllables++;
                   x++;
               }
                while (matcherS2.find()) {
                syllables++;
                }
                if (x > 2) {
                    polysyllables++;
                }



        }



        System.out.println("Words: " + words + "\n" +
                "Sentences: " + sentences + "\n" +
                "Characters: " + characters + "\n" +
                "Syllables: " + syllables + "\n" +
                "Polysyllables: " + polysyllables);
    }

    public static String automatedReadabilityIndex() {
        double score =  4.71 * characters / words + 0.5 * words / sentences - 21.43;
        String s = String.format("%.2f",score);

        return "Automated Readability Index: " + s + " (about " + years(score) + "-year-olds).";
    }

    public static String fleschKincaidReadability() {
        double score = 0.39 * words / sentences + 11.8 * syllables / words - 15.59;
        String s = String.format("%.2f",score);

        return "Flesch–Kincaid readability tests: " + s + " (about " + years(score) + "-year-olds).";
    }

    public static String smogGrade() {
        double score = 1.043 * Math.sqrt((double) polysyllables * 30 / sentences) + 3.1291;
        String s = String.format("%.2f",score);


        return "Simple Measure of Gobbledygook: " + s + " (about " + years(score) + "-year-olds).";
    }

    public static String colemanLiauIndex() {
        double l = (double) letters / words * 100;
        double s = (double) sentences / words * 100;
        double score = 0.0588 * l - 0.296 * s - 15.8;
        String scr = String.format("%.2f",score);
        return "Coleman–Liau index: " + scr + " (about " + years(score) + "-year-olds).";
    }

    public static String years(double score) {
        String years = "";
        switch ((int) score) {
            case 1:
                years = "6";
                break;
            case 2:
                years = "7";
                break;
            case 3:
                years = "9";
                break;
            case 4:
                years = "10";
                break;
            case 5:
                years = "11";
                break;
            case 6:
                years = "12";
                break;
            case 7:
                years = "13";
                break;
            case 8:
                years = "14";
                break;
            case 9:
                years = "15";
                break;
            case 10:
                years = "16";
                break;
            case 11:
                years = "17";
                break;
            case 12:
                years = "18";
                break;
            case 13:
                years = "24";
                break;
            case 14:
                years = "24+";
                break;
        }
        return years;

    }


}
