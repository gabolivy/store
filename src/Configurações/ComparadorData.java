package Configurações;

import java.util.Comparator;

import Classes.Venda;

public class ComparadorData implements Comparator<Venda>{

    @Override
    public int compare(Venda o1, Venda o2) {
        return o1.getData().compareTo(o2.getData());
    }
    
}