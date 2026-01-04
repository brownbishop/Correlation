package org.correlation;

import java.util.ArrayList;

import static org.correlation.Pearson.compute;

public class Main {
    static void main() throws Pearson.DifferentSize, Pearson.EmptyVectors {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        IO.println(String.format("Hello and welcome!"));

        ArrayList<Double> list = new ArrayList<Double>();
        ArrayList<Double> list2 = new ArrayList<Double>();
        for (int i = 0; i < 100; i++) {
            list.add(Double.valueOf(i));
            list2.add(Double.valueOf(1000 - i));
        }

        IO.println(list);
        IO.println(list2);

        double correlation = compute(list, list2);
        IO.print("Pearson: ");
        IO.println(correlation);
        double correlation2 = SpearmansRank.compute(list, list2);

        IO.print("Spearman's Rank: ");
        IO.println(correlation2);
    }
}
