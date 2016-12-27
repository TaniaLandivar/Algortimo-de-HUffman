/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.p3.huffman;

/**
 *
 * @author davidvalladarez
 */
public class Nodo {
    public int frecuencia;
    public char c;
    public Nodo izquierda;
    public Nodo derecha;
    
    public Nodo(int frecuencia, char c, Nodo izquierda, Nodo derecha)
    {
        this.frecuencia = frecuencia;
        this.c = c;
        this.izquierda = izquierda;
        this.derecha = derecha;
    }
    
    public Nodo() {}
    
    public Nodo agregarNodo(Nodo nodo1, Nodo nodo2)
    {
        if(nodo1.frecuencia < nodo2.frecuencia)
        {
            izquierda = nodo1;
            derecha = nodo2;
        }
        else
        {
            derecha = nodo1;
            izquierda = nodo2;
        }
        frecuencia = nodo1.frecuencia + nodo2.frecuencia;
        
        return this;
    }
}
