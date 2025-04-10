/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paketSlanje;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Mateja
 */
public class Task implements Serializable{
    
    private int num;
    
    private List<Object> lista;
    
    private int iter;
    
    
    public Task(List<Object> list, int n)
    {
        this.lista = list;
        this.num = n;
        iter = 0;
    }
    
    public int getNum()
    {
        return this.num;
    }
    
    public Object getNext()
    {
        if(iter==lista.size())
            return null;
        
        return lista.get(iter++);
    }
    
}
