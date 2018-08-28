import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.Random;
/*
public class Main {

    private static BigInteger counter [] = new BigInteger [2];

    public static BigInteger karatsuba(BigInteger x, BigInteger y) {

        // cutoff to brute force

        int N = Math.max(x.bitLength(), y.bitLength());

        //if (N <= 1) {
        if (x.compareTo(BigInteger.valueOf(11)) < 0 & y.compareTo(BigInteger.valueOf(11)) < 0) {
            return x.multiply(y);                // optimize this parameter
        }

        // number of bits divided by 2, rounded up
        N = (N / 2) + (N % 2);

        // x = a + 2^N b,   y = c + 2^N d
        BigInteger b = x.shiftRight(N);
        BigInteger a = x.subtract(b.shiftLeft(N));
        BigInteger d = y.shiftRight(N);
        BigInteger c = y.subtract(d.shiftLeft(N));

        // compute sub-expressions
        BigInteger ac    = karatsuba(a, c);
        BigInteger bd    = karatsuba(b, d);
        BigInteger abcd  = karatsuba(a.add(b), c.add(d));

        BigInteger adbc = abcd.subtract(ac).subtract(bd);


        //counter105;
        if(adbc.equals(BigInteger.valueOf(8))){
            counter[0] = BigInteger.valueOf(1);
        }
        //counter72;
        if(adbc.equals(BigInteger.valueOf(7))){
            counter[1] = BigInteger.valueOf(1);
        }

        BigInteger recurs = adbc.shiftLeft(N);

        BigInteger result = ac.add(recurs).add(bd.shiftLeft(2*N));

        return result;
    }

    public static void main(String[] args) {
        long start, stop, elapsed;

        String k = "44269423";
        String z = "49823261";
        String res = "2205647016448403";
        //String k = "1685287499328328297814655639278583667919355849391453456921116729";
        //String z = "7114192848577754587969744626558571536728983167954552999895348492";
        System.out.println(k);
        System.out.println(z);

        BigInteger a = new BigInteger(k);
        BigInteger b = new BigInteger(z);

        BigInteger c = karatsuba(a, b);

        BigInteger d = a.multiply(b);

        System.out.println((c.equals(d)));

        System.out.println(c);

        printArray(counter);

    }

    private static void printArray(BigInteger[] anArray) {
        for (int i = 0; i < anArray.length; i++) {
            if (i > 0) {
                System.out.print(", ");
            }
            System.out.print(" ");
            System.out.print(anArray[i]);
        }
    }
}
*/
public class Main {

    public static String X;
    public static String Y;
    public static int counter105 = 0;
    public static int counter72 = 0;
    public static int counter12 = 0;

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
        abcd = abcd.subtract(ac).subtract(bd);

        if(abcd.equals(BigInteger.valueOf(105))){
            ++counter105;
        }

        if(abcd.equals(BigInteger.valueOf(72))){
            ++counter72;
        }

        if(abcd.equals(BigInteger.valueOf(12))){
            ++counter12;
        }

        BigInteger ten = BigInteger.valueOf(10);

        BigInteger kof1 = ten.pow((2*halfMax));
        BigInteger kof2 = ten.pow(halfMax);

        BigInteger formulaResult = ((ac.multiply(kof1)).add((abcd.multiply(kof2))).add(bd));

        return formulaResult;

    }

    public static void main(String[] args) {

        String res = "71332574014261268360454523927286";

        String a = "1685287499328328297814655639278583667919355849391453456921116729";
        String b = "7114192848577754587969744626558571536728983167954552999895348492";

        BigInteger c = karatsuba(a, b);

        System.out.println(counter105);
        System.out.println(counter72);
        System.out.println(counter12);
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