package handler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class mainUI {



    public static void main(String[] args) throws IOException {

        Instant start = Instant.now();
        Boggle Boggle = new Boggle();

        BufferedReader streamReader = new BufferedReader(new InputStreamReader(System.in));

        BufferedReader filereader = new BufferedReader(new FileReader("src/main/resources/puzzle.txt"));

        System.out.println(Boggle.getDictionary(filereader));
        //System.out.println(boggle.getDictionary(streamReader));

        System.out.println(Boggle.getPuzzle(streamReader));

        System.out.println("Grid:\n" + Boggle.print());

        List<String> g = Boggle.solve();



        System.out.println(g.isEmpty());

        Instant end = Instant.now();
        System.out.println("Duration : " + Duration.between(start, end).toSeconds());

    }
}