/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.p3.huffman;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author davidvalladarez
 */
public class Huffman {
    //	private static String value;
    private int table[] = new int[0x7f]; // Almacena la frecuencia de cada caracter
    private Nodo nodos[]; // La cola de Prioridades o el arbol final generado
    private int longitudTable = 0; // El verdadero tamaño de la tabla que almacena los caracteres
    private Arbol arbol; // Almacena el arbol
    private int longitudNodos = 0;// Todos los incrementos y decrementos se harán a este valor
    private HuffmanTransversor hC; //The Class Responsible for Decoding the Huffman Tree
    private String tabla[][]; // La tabla de la interfaz gráfica

    public Huffman(File archivo) throws IOException
    {
        tablaFrecuencias(archivo);
        arrayNodos();
        Nodo x = crearArbol();
        hC = new HuffmanTransversor(x, archivo);
        setTabla(x);
    }

    public void tablaFrecuencias(File archivo) throws IOException
    {
        FileWriter fw = new FileWriter(new File("original.txt"));
        FileReader fr = new FileReader(archivo);
        int caracter = fr.read();

        while(caracter != -1)
        {
            //System.out.println((char)caracter);
            table[caracter]++;
            caracter = fr.read();
            fw.write(Integer.toBinaryString(caracter));
        }
        fr.close();
        fw.close();
    }

    public int getAscii(char substringValue)
    {
            return substringValue&0x7f;
    }

    public void arrayNodos()    
    {
        int counter = 0; // Cuenta el número de caracteres que tiene el texto
        for(int i = 0; i < table.length; i++)
        {
            if(table[i]>0)
                counter++;
        }

        longitudTable =  counter;
        counter = 0;    
        nodos = new Nodo[longitudTable];
        tabla = new String[longitudTable][4];

        for(int i = 0; i < 127; i++)
        {
            if(table[i] != 0)
            {
                nodos[counter] = new Nodo(table[i], (char)i, null, null);
                tabla[counter][0] = String.valueOf((char) i);
                tabla[counter][1] = String.valueOf(table[i]);
                tabla[counter][2] = String.valueOf(Integer.toBinaryString(i));
                //System.out.println((char) i);
                counter++;
            }
        }
        longitudNodos = nodos.length;

        //quicksort(0, longitudNodos-1);
        //for (int i=0;i<longitudNodos;i++)
        //    System.out.println(nodos[i].frecuencia);
        sort();

    }

    public Nodo crearArbol()
    {
       for(int i = 1; i < longitudNodos; i++)
       {
           try
           {
               if(nodos[1].frecuencia >= nodos[0].frecuencia)
               {
                   arbol = new Arbol(nodos[0], nodos[i]);
                   nodos[0] = arbol;
                   moveItems(i, longitudNodos);
                   longitudNodos--; 
                   i--;
                   sort();
               }
               else
               {
                   if(i+1 < longitudNodos)
                   {
                        arbol = new Arbol(nodos[i], nodos[i+1]);
                        nodos[1] = arbol;
                        moveItems(i+1, longitudNodos);
                        sort();
                        longitudNodos--;
                        i--;
                   }
                   else
                   {
                       nodos[1] = nodos[i];
                       nodos[0] = new Arbol(nodos[0], nodos[1]);
                   }
               }
           }
           catch(Exception e)
           {
             //I dare this program to crash...hahaha
           }
        }
        return nodos[0];
    }

    private void moveItems(int index, int length)
    {  
        try {
            for(int i = index; i < length; i++)
                nodos[i] = nodos[i+1];
        } catch (Exception e) {
            // nada
        }
    }

    private void sort()
    {
        Nodo temp;
        for(int i = longitudNodos-1; i > 1; i--)
        {
            for(int j = 0; j < i; j++)
            {
                if(nodos[j].frecuencia > nodos[j+1].frecuencia)
                {
                    temp = nodos[j+1];
                    nodos[j+1] = nodos[j];
                    nodos[j] = temp;
                }

                if(nodos[j].frecuencia == nodos[j+1].frecuencia && nodos[j].izquierda != null)
                {
                    temp = nodos[j+1];
                    nodos[j+1] = nodos[j];
                    nodos[j] = temp;
                }
            }
        }
    }

    public void quicksort(int izq, int der) {

        Nodo pivote = nodos[izq];
        int i = izq;
        int j = der;
        Nodo aux;

        while(i<j){
            while(nodos[i].frecuencia <= pivote.frecuencia && i<j) i++;
            while(nodos[j].frecuencia > pivote.frecuencia) j--;
            if ( i < j ) {                    
                aux = nodos[i];
                nodos[i] = nodos[j];
                nodos[j] = aux;
            }
        }
        nodos[izq] = nodos[j];
        nodos[j] = pivote;
        if( izq < j-1 )
           quicksort(izq, j-1); 
        if( j+1 < der )
           quicksort(j+1, der);
    }

    public void setTabla(Nodo nodoRaiz) {

        for (int i=0; i<tabla.length; i++) {
            tabla [i][3] = hC.search(nodoRaiz, "", tabla[i][0].charAt(0));   
        }
    }

    public String[][] getTabla() {
        return tabla;
    }

    public HuffmanTransversor gethC() {
        return hC;
    }
    
    public double factorDeCompresion(){
        File original = new File("original.txt");
        File comprimido = new File("comprimido");
        return  (((double)original.length() / (double)comprimido.length()) * 100.0) - 100 ;
    }
    
    public double razonDeCompresion(){
        File original = new File("original.txt");
        File comprimido = new File("comprimido");
        
        return 100 - (((double)comprimido.length() / (double)original.length()) * 100);
    }
}
