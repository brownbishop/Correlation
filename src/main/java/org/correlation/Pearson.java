package org.correlation;
import java.lang.Exception;
import java.util.ArrayList;

import static java.lang.Math.*;

public class Pearson {
    // Pearson correlation coefficient (Pearson’s rho) between two equal-length containers in one numerically stable, single-pass sweep
    // It implements rho = Cov(X,Y) / sqrt(Var(X) * Var(Y)), where Cov(X,Y) and Var(·) are the (population-style) sums of pairwise deviations.
    // Because numerator and denominator share the same scaling, no explicit division by n is needed in the ratio.
    // Internally it uses the stable Welford-style updates (single-pass, numerically stable) to maintain:
    // mu_u, mu_v : running means of u and v
    // Qu, Qv : running second-moment accumulators (sum of squared deviations, M2)
    // cov : running cross-deviation accumulator (sum of (u - mean_u)*(v - mean_v))

    public static class DifferentSize extends Exception {
        public DifferentSize() {
            super("Vectors must be of the same size");
        }
    }

    public static class EmptyVectors extends Exception {
        public EmptyVectors() {
            super("Vectors cannot be empty");
        }
    }
    public static double compute(ArrayList<Double> u, ArrayList<Double> v) throws DifferentSize, EmptyVectors {
        if (u.size() != v.size())
            throw new DifferentSize();

        if (u.isEmpty())
            throw new EmptyVectors();

        double cov = 0;
        double mu_u = u.getFirst();
        double mu_v = v.getFirst();
        double Qu = 0;
        double Qv = 0;

        for(int i = 1; i < u.size(); ++i)
        {
            double u_tmp = u.get(i) - mu_u;
            double v_tmp = v.get(i) - mu_v;
            Qu = Qu + (i*u_tmp*u_tmp)/(i+1);
            Qv = Qv + (i*v_tmp*v_tmp)/(i+1);
            cov += i*u_tmp*v_tmp/(i+1);
            mu_u = mu_u + u_tmp/(i+1);
            mu_v = mu_v + v_tmp/(i+1);
        }

        if (Qu == 0 || Qv == 0)
            return 0;

        double rho = cov / sqrt(Qv);
        return clamp(rho, -1, 1);
    }

}