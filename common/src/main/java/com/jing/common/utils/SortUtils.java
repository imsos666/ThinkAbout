package com.jing.common.utils;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Created by linux-sever-build5 on 10/20/16.
 */
public class SortUtils {

    public static final int ASC = 0;
    public static final int DES = 1;

    public static void insertSort(Comparable[] comparable){
        int len = comparable.length;
        Comparable temp;
        int i, j;
        for(i = 1; i < len; i++){
            temp = comparable[i];
            for(j = i -1; j >= 0; j--){
                if(comparable[j].compareTo(temp) > 0){
                    comparable[j + 1] = comparable[j];
                }else{
                    break;
                }
            }
            comparable[j + 1] = temp;
        }
    }

    public static void insertSort(List<Comparable> comparable, int order){
        int len = comparable.size();
        Comparable temp;
        int i, j;
        for(i = 1; i < len; i++){
            temp = comparable.get(i);
            for(j = i -1; j >= 0; j--){
                if(compareDirection(order, comparable.get(j), temp)){
                    Collections.swap(comparable,j + 1, j);
                }else{
                    break;
                }
            }
            comparable.set(j + 1, temp);
        }
    }

    public static void shellSort(Comparable[] comparable){
        double d = comparable.length;
        int d1 = (int) Math.ceil(d / 2);
        Comparable temp;
        int i,j;
        while (true){
            for(i = d1; i < comparable.length; i += d1){
                temp = comparable[i];
                for(j = i -d1; j >= 0; j -= d1){
                    if(comparable[j].compareTo(temp) > 0){
                        comparable[j + d1] = comparable[j];
                    }else {
                        break;
                    }
                }
                comparable[j + d1] = temp;
                d1 /= 2;
                if(d1 == 1)
                    return;
            }
        }
    }

    public static void shellSort(List<Comparable> comparable, int order){
        double d;
        int d1 = comparable.size();
        Comparable temp;
        int i,j;
        while (true){
            d = Math.ceil(d1 / 2);
            d1 = (int) d;
            for(i = d1; i < comparable.size(); i += d1){
                temp = comparable.get(i);
                for(j = i -d1; j >= 0; j -= d1){
                    if(compareDirection(order, comparable.get(j), temp)){
                        Collections.swap(comparable, j + d1,j);
                    }else {
                        break;
                    }
                }
                comparable.set(j + d1, temp);
            }
            if(d1 == 1)
                break;
        }
    }

    public static void selectSort(List<Comparable> comparable, int order){
        for(int i = 0; i < comparable.size(); i++){
            for(int j = i + 1; j < comparable.size(); j++){
                if(compareDirection(order, comparable.get(i),comparable.get(j))){
                    Collections.swap(comparable,i, j);
                }
            }
        }
    }

    private static void bulidheap(List<Comparable> comparable, int lastIndex, int order){
        int parentIndex = lastIndex;
        int leftIndex = -1, rightIndex = -1;
        while (lastIndex > 0){
            parentIndex = (lastIndex - 1) / 2;
            leftIndex = parentIndex * 2;
            rightIndex = leftIndex + 1;
            if(leftIndex <= lastIndex){
                if(compareDirection(order,comparable.get(leftIndex),comparable.get(parentIndex))){
                    Collections.swap(comparable, leftIndex, parentIndex);
                }
            }
            if(rightIndex <= lastIndex){
                if(compareDirection(order,comparable.get(rightIndex),comparable.get(parentIndex))){
                    Collections.swap(comparable, rightIndex, parentIndex);
                }
            }
            lastIndex -= 2;
        }
        Log.d("jingbin","parentIndex-->" + comparable);
    }

    public static void  heapSort(List<Comparable> comparable, int order){
        int len = comparable.size();
        for(int i = len - 1; i > 0; i--){
            bulidheap(comparable, i, order);
            Collections.swap(comparable,0, i);
        }
    }

    public static void bubbleSort(List<Comparable> comparable, int order){
        int len = comparable.size();
        for(int i = 0; i < len; i++){
            for(int j = 0; j < len - i - 1; j++){
                if(compareDirection(order, comparable.get(j), comparable.get(j + 1))){
                    Collections.swap(comparable, j, j+1);
                }
            }
        }
    }

    public static void quickSort(List<Comparable> comparable,int start, int end, int order){
        int i = start;
        int j = end;
        if(i >= j)
            return;
        Comparable temp = comparable.get(start);
        while (i < j){
            while (i < j && compareDirection(order, comparable.get(j), temp))
                j--;
            comparable.set(i, comparable.get(j));
            while (i < j && compareDirection(order, temp, comparable.get(i)))
                i++;
            comparable.set(j, comparable.get(i));
        }
        comparable.set(i, temp);
        Log.d("jingbin", "quickSort "+ i + " : " + comparable);
        quickSort(comparable,start, i - 1, order);
        quickSort(comparable, i + 1, end, order);
    }


    private static boolean compareDirection(int order,Comparable comparable, Comparable comparableTo){
        switch (order){
            case DES:
                return comparable.compareTo(comparableTo) <= 0;
            case ASC:
            default:
                return comparable.compareTo(comparableTo) >= 0;
        }
    }

}
