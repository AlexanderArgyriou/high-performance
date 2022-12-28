package org.example;

import static org.example.Consts.SIZE;

public class SharedResult {
    private int[] arr;

    public synchronized void mergeASortedArr(int[] otherArr) {
        if ( arr == null ) {
            arr = otherArr;
        }
        int i = 0, j = 0, k = 0;
        int[] result = new int[arr.length + otherArr.length];

        while ( i < arr.length && j < otherArr.length ) {
            if ( arr[i] < otherArr[j] ) {
                result[k++] = arr[i++];
            }
            else {
                result[k++] = otherArr[j++];
            }
        }

        while ( i < arr.length ) {
            result[k++] = arr[i++];
        }
        while ( j < otherArr.length ) {
            result[k++] = otherArr[j++];
        }

        setArr( result );
    }

    public int[] getArr() {
        return arr;
    }

    public void setArr(int[] arr) {
        this.arr = arr;
    }
}
