package org.correlation;

import java.util.ArrayList;


public class SpearmansRank {
    public static double compute(ArrayList<Double> u, ArrayList<Double> v) throws Pearson.DifferentSize, Pearson.EmptyVectors {
        var rank_u = rankVector(u);
        var rank_v = rankVector(v);
        return Pearson.compute(rank_u, rank_v);
    }

    public static ArrayList<Double> rankVector(ArrayList<Double> X) {
        int N = X.size();
        var Rank_X = new ArrayList<Double>();

        for (int i = 0; i < N; i++) {
            Rank_X.add(0d);
            int r = 1;
            int s = 1;

            for (int j = 0; j < i; j++) {
                if (X.get(j) > X.get(i))
                    r++;
                if (X.get(j) < X.get(i))
                    s++;
            }

            for (int j = i + 1; j < N; j++) {
                if (X.get(j) > X.get(i))
                    r++;
                if (X.get(j) == X.get(i))
                    s++;
            }
            Rank_X.set(i, (r + (s - 1)*0.5));
        }
        return Rank_X;
    };
}
