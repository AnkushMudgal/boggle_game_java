package handler;

import handler.Boggle;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Test {


    public static void main(String[] args) throws FileNotFoundException {

        Boggle b = new Boggle();
        BufferedReader streamReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader filereader = new BufferedReader(new FileReader("D:/puzzle.txt"));

        b.getDictionary(filereader);
        b.getPuzzle(streamReader);
        System.out.println(b.print());
        b.solve();
        b.print();
    }

}