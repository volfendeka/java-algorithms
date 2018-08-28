import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.Random;

public class Main {

    public static String X;
    public static String Y;
    public static int counter8;
    public static int counter7;

    public static BigInteger karatsuba(String x, String y) {

        if(x.length() < y.length()){
            String k = x;
            X=y;
            Y=k;
        }else{
            X = x;
            Y = y;
        }

        if(X.length() < 3){
            long Xvalue = Long.parseLong(X);
            long Yvalue = Long.parseLong(Y);
            if(Xvalue < 10 && Yvalue < 10){
                long res = Xvalue * Yvalue;

                BigInteger result = BigInteger.valueOf(res);
                return result;
            }
        }

        ballanceLength();

        int halfMax = X.length()/2;

        String a = X.substring(0, halfMax);
        String b = X.substring(halfMax, X.length());
        String c = Y.substring(0, halfMax);
        String d = Y.substring(halfMax, Y.length());

        BigInteger tempA = new BigInteger(a);
        BigInteger tempB = new BigInteger(b);
        BigInteger tempC = new BigInteger(c);
        BigInteger tempD = new BigInteger(d);

        BigInteger ac = karatsuba(a, c);
        BigInteger bd = karatsuba(b, d);
        //long abcd = karatsuba((a+b), (c+d));
        BigInteger abcd = karatsuba((tempA.add(tempB)).toString(), (tempC.add(tempD)).toString());
        BigInteger adbc = abcd.subtract(ac).subtract(bd);

        if(adbc.equals(BigInteger.valueOf(14))){
            counter8++;
        }

        if(adbc.equals(BigInteger.valueOf(9))){
            counter7++;
        }

        long kof1 = (long)Math.pow(10, (2*halfMax));
        long kof2 = (long)Math.pow(10, halfMax);

        BigInteger formulaResult = ((ac.multiply(BigInteger.valueOf(kof1))).add((adbc.multiply(BigInteger.valueOf(kof2)))).add(bd));

        return formulaResult;

    }

    public static void main(String[] args) {

        String res = "71332574014261268360454523927286";

        String a = "3957322621234423";
        String b = "7748313756335578";

        BigInteger c = karatsuba(a, b);

        System.out.println(counter8);
        System.out.println(counter7);
        System.out.println(c);

        BigInteger l = new BigInteger(a);
        BigInteger g = new BigInteger(b);
        System.out.println(l.multiply(g));
    }

    public static String addZeroInFront( String inputString, int zerosAmount){
        for (int i = 0; i < zerosAmount; i++){
            inputString = "0" + inputString;
        }
        return inputString;
    }

    public static void ballanceLength(){
        if(X.length() % 2 > 0){
            X = addZeroInFront(X, 1);
        }

        int diff = X.length() - Y.length();
        if(diff > 0){
            Y = addZeroInFront(Y, diff);
        }
    }
}