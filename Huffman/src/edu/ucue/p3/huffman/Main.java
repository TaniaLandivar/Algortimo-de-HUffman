/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.p3.huffman;

import edu.ucue.p3.GUI.AppHuffman;
import java.util.Scanner;

/**
 *
 * @author davidvalladarez
 */
public class Main {
    //static Huffman huffman;
    //private static Scanner input = new Scanner(System.in);
    //private static String value;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        AppHuffman huffman = new AppHuffman();
        huffman.setVisible(true);
       /*System.out.print("Enter String: ");
       value = input.nextLine();
       System.out.println("This is the value you entered: "+value);
       huffman = new Huffman(value);
       System.out.println("The bit representation of the String you entered is: "+huffman.hC.finalBitPattern); */ 
    }
    
}
