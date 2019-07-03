import javax.print.DocFlavor;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLSyntaxErrorException;
import java.util.*;

public class part2Test {
    public static void main(String[] args) throws Exception {
        try {
            /*
             * Reading from file.
             */
            ArrayList<String> list = new ArrayList<String>();
            File file = new File("part2Test.txt");
            BufferedReader reader = null;
            reader = new BufferedReader(new FileReader(file));
            String text;
            String[] val = new String[100];


            /*
             * Variable names and values ​​are read into a separate array when the file is read. (val)
             * Expressions read into a separate array when the file is read. (list)
             */
            myStack_P2 e = new myStack_P2(100);
            EvaluateString test = new EvaluateString(100);
            int var = 0;
            while ((text = reader.readLine()) != null) {
                if (text.contains("=")) {
                    for (String deneme : text.split(" = ")) {
                        val[var++] = deneme;
                    }
                }
                else if (text == "") {

                }
                else {
                    if (!(test.isBalanced(text))) {
                        throw new Exception("Parantheses Not Balanced");
                    }
                    for (String value : text.split(" ")) {  // get list of integer
                        if (!value.equals("")) {          // ignore space
                            if (value.equals("sin(") || (value.equals("cos(") || (value.equals("abs(")))) {
                                list.add(value);  // add to list
                                list.add("(");
                            } else {
                                list.add(value);
                            }
                        }
                    }
                }
            }
            /*
             * The values ​​read from the file in Arraylist are transferred to arr String array.
             */
            String[] arr = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                String temp = list.get(i);
                arr[i] = temp;
            }

            /*
            * Infix operation before the change to postfix.
            */
            System.out.print("Infix  ---->\t: ");
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i]);
            }
            System.out.print("\t Replaced instead of sin( = '!(', cos( = '?(', abs( = '#(' on Postfix");
            System.out.println();

            /*
             * 'Sin' is placed in the '!' sign (instead of 'sin( = !(' for operation).
             * 'Cos' is placed in the '?' sign (instead of 'cos( = ?(' for operation).
             * 'Abs' is placed in the '#' sign (instead of 'abs( = #(' for operation).
             */
            String outputLine[] = null;
            outputLine = arr;
            for (int i = 0; i < arr.length; i++) {
                if (outputLine[i].equals("sin(")) {
                    outputLine[i] = outputLine[i].replaceAll("sin", "!");
                } else if (outputLine[i].equals("cos(")) {
                    outputLine[i] = outputLine[i].replaceAll("cos", "?");
                } else if (outputLine[i].equals("abs(")) {
                    outputLine[i] = outputLine[i].replaceAll("abs", "#");
                }
            }

            /*
             * Evaluate string to postfix then calculate its value.
             */
            EvaluateString x = new EvaluateString(outputLine.length + 1);
            x.InfixToPostfix(outputLine, val, outputLine.length);
            double sum = x.EvaluateResult();
            System.out.printf("Result \t\t\t: %.2f", sum);



        }catch (Exception e){
            System.err.println(e);
        }
    }
}
