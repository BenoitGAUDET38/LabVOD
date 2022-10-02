package application;

import java.io.Serializable;
import java.math.BigInteger;

public class Bill implements Serializable {
    String movieName;
    float outrageousPrice;

    public Bill(String movieName, float outrageousPrice) {
        this.movieName = movieName;
        this.outrageousPrice = outrageousPrice;
    }
}
