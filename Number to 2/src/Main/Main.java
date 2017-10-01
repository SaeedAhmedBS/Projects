package Main;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//Move to function

public class Main {

    public static void main(String[] args) {
        //System.out.println("Hello Java");
        Scanner in = new Scanner(System.in);
        long t , a = 0 , b = 0;
        t = in.nextLong();
        //t = 1149233707l;
        //long q = 201149233707l;
        long x = 1 , y = 1 ;
        long m = (long) (Math.sqrt(t)* 2);
        for (long i = 1 ; i < m; i++)
        {
            for (long j = m ; j >= i ; j-- )
            {
                if (i*j == t)
                {
                   x = i ; y = j;
                   a++;
                }
                b++;
                if (i*j < t)
                    break;
               
            }
        }
        System.out.println("The Numbers is x = " + x + " and y = " + y + " to get " + t + " code = " + a + " code 2 = " + b);
    }
    
}
