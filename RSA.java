///**
// * Q30.1) For RSA, suppose p and q are given, and of course, e is given.
// * Recall that 𝑒𝑑=1mod𝜙(𝑛) where 𝜙(𝑛)=(𝑝−1)(𝑞−1),
// * and by definition of "mod", 𝑒𝑑=𝑘𝜙(𝑛)+1.
// * Since for RSA, gcd(𝑒,𝜙(𝑛))=1,
// * we get 𝑒𝑑+𝑚𝜙(𝑛)=gcd(𝑒,𝜙(𝑛)) where m = -k.
// * Therefore, if we know p, q, and e, it's not hard to find d and m using the extended Euclidean algorithm.
// * So, write a program that takes p, q, and e for RSA as inputs,
// * and output the smallest possible d ( > 0) that can be used as a private key using the extended Euclidean algorithm.
// *
// * @author: Thi Ngoc Diem Vu
// * student ID: 014932645
// * Course: CMPE166
// * School: San Jose State University
// */


import java.util.Scanner;

public class RSA {

    public static int gcd(int n1, int n2) {
        int temp;
        while (true) {
            temp = n1 % n2;
            if (temp == 0) {
                return n2;
            }
            n1 = n2;
            n2 = temp;
        }

    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        // input for p, q, e
        System.out.print("p = ");
        int p = s.nextInt();
        System.out.print("q = ");
        int q = s.nextInt();
        System.out.print("e = ");
        int e = s.nextInt();
        // calculate N
        int N = p * q;
        // 𝜙(𝑛) = (𝑝−1)(𝑞−1)
        int PHI = (p - 1) * (q - 1);
        double count;
        while (e < PHI) {
            count = gcd(e, PHI);
            if (count == 1)
                break;
            else
                e++;
        }
        double d_k, d, ms, c, m;
        d_k = (double) 1 / e;
        d = d_k % PHI;
        ms = 12;
        c = Math.pow(ms, e);
        m = Math.pow(c, d);
        c = c % N;
        m = m % N;


        // output the smallest possible d ( > 0) using the extended Euclidean algorithm
        System.out.println("the smallest possible d ( > 0) is: " + d);
        System.out.println("Original message: " + ms);
        System.out.println("Encypted message: " + c);
        System.out.println("Decrypted message: " + m);
    }
}
