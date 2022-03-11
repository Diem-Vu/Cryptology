/**
 * @author: Diem Vu
 * SJSU ID: 014932645
 * Course: CMPE166
 */

import java.util.Scanner;

public class A5_1 {

    static int[] StringToIntArray(String string) {
        String[] stringArray = string.split("");
        int[] myArray = new int[string.length()];

        for (int i = 0; i < string.length(); i++) {
            myArray[i] = Integer.parseInt(stringArray[i]);
        }
        return myArray;
    }

    static int maj(int[] x, int[] y, int[] z) {
        return ((x[8] & y[10]) ^ (x[8] & z[10]) ^ (y[10] & z[10]));
    }

    static String ArrayToString(int[] nums) {
        StringBuilder strNum = new StringBuilder();
        for (
                int num : nums) {
            strNum.append(num);
        }
        return strNum.toString();
    }

    public static int[] shiftArray(int[] array) {
        for (int i = (array.length - 1); i > 0; i--) {
            array[i] = array[i - 1];
        }
        return array;
    }

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        // read X, Y, Z
        System.out.print("X: ");
        String X = s.nextLine();
        System.out.print("Y: ");
        String Y = s.nextLine();
        System.out.print("Z: ");
        String Z = s.nextLine();

        // put X, Y, Z  in array of 19 bits, 22 bits, and 23 bits
        int[] arrX = StringToIntArray(X);
        int[] arrY = StringToIntArray(Y);
        int[] arrZ = StringToIntArray(Z);

        // create new arrays
        int[] newArrX = new int[arrX.length];
        int[] newArrY = new int[arrY.length];
        int[] newArrZ = new int[arrZ.length];
        String keystream = "";
        int i, j = 0;

        for (i = 0; i < 32; i++) {
            // calculate maj(X8, Y10, Z10)
            int m = maj(arrX, arrY, arrZ);

            // calculate steps if bitX = m for bitX = x13 ⊕ x16 ⊕ x17 ⊕ x18
            int bitX = arrX[13] ^ arrX[16] ^ arrX[17] ^ arrX[18];
            // calculate steps if bitY = m for bitY = y20 ⊕ y21
            int bitY = arrY[20] ^ arrY[21];
            // calculate steps if bitz = m for bitZ = z7 ⊕ z20 ⊕ z21 ⊕ z22
            int bitZ = arrZ[7] ^ arrZ[20] ^ arrZ[21] ^ arrZ[22];
            // shift right X
            if (arrX[8] == m) {
                newArrX = shiftArray(arrX);
                newArrX[0] = bitX;
            }
            //shift right y
            if (arrY[10] == m) {
                newArrY = shiftArray(arrY);
                newArrY[0] = bitY;
            }
            // shift right Z
            if (arrZ[10] == m) {
                newArrZ = shiftArray(arrZ);
                newArrZ[0] = bitZ;
            }
            //generate the keystream bit Keystream = x18 ⊕ y21 ⊕ z22
            keystream = "" + (newArrX[18] ^ newArrY[21] ^ newArrZ[22]) + keystream;
        }


        // print out the keystream bit
        System.out.println("Keystream bit: " + keystream);

        // print out X, Y, Z after steps
        System.out.println("X = " + ArrayToString(newArrX));
        System.out.println("y = " + ArrayToString(newArrY));
        System.out.println("z = " + ArrayToString(newArrZ));
    }
}

