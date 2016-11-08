import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Created by andy on 11/3/2016.
 */
public class L4233AMad {

    // ====================================================
    //                    SHELL SORT
    // ====================================================

    public static <AnyType extends Comparable<? super AnyType>> void shellsort(AnyType[] a) {
        // todo: make gapLength set gap sequence?
        for (int gap = a.length / 2; gap > 0;
             gap = gap == 2 ? 1 : (int) (gap / 2.2))
            for (int i = gap; i < a.length; i++) {
                AnyType tmp = a[i];
                int j = i;

                for (; j >= gap && tmp.compareTo(a[j - gap]) < 0; j -= gap)
                    a[j] = a[j - gap];
                a[j] = tmp;
            }
    }

    // ====================================================
    //                    HEAP SORT
    // ====================================================

    public static <AnyType extends Comparable<? super AnyType>> void heapsort(AnyType[] a) {
        for (int i = a.length / 2 - 1; i >= 0; i--)  /* buildHeap */
            percDown(a, i, a.length);
        for (int i = a.length - 1; i > 0; i--) {
            swapReferences(a, 0, i);            /* deleteMax */
            percDown(a, 0, i);
        }
    }

    private static int leftChild(int i) {
        return 2 * i + 1;
    }

    private static <AnyType extends Comparable<? super AnyType>> void percDown(AnyType[] a, int i, int n) {
        int child;
        AnyType tmp;

        for (tmp = a[i]; leftChild(i) < n; i = child) {
            child = leftChild(i);
            if (child != n - 1 && a[child].compareTo(a[child + 1]) < 0)
                child++;
            if (tmp.compareTo(a[child]) < 0)
                a[i] = a[child];
            else
                break;
        }
        a[i] = tmp;
    }
    // ====================================================
    //                     MERGE SORT
    // ====================================================

    public static <AnyType extends Comparable<? super AnyType>> void mergeSort(AnyType[] a) {
        AnyType[] tmpArray = (AnyType[]) new Comparable[a.length];
        mergeSort(a, tmpArray, 0, a.length - 1);
    }

    private static <AnyType extends Comparable<? super AnyType>> void mergeSort(AnyType[] a, AnyType[] tmpArray,
                                                                                int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(a, tmpArray, left, center);
            mergeSort(a, tmpArray, center + 1, right);
            merge(a, tmpArray, left, center + 1, right);
        }
    }

    private static <AnyType extends Comparable<? super AnyType>> void merge(AnyType[] a, AnyType[] tmpArray,
                                                                            int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;

        // Main loop
        while (leftPos <= leftEnd && rightPos <= rightEnd)
            if (a[leftPos].compareTo(a[rightPos]) <= 0)
                tmpArray[tmpPos++] = a[leftPos++];
            else
                tmpArray[tmpPos++] = a[rightPos++];

        while (leftPos <= leftEnd)    // Copy rest of first half
            tmpArray[tmpPos++] = a[leftPos++];

        while (rightPos <= rightEnd)  // Copy rest of right half
            tmpArray[tmpPos++] = a[rightPos++];

        // Copy tmpArray back
        for (int i = 0; i < numElements; i++, rightEnd--)
            a[rightEnd] = tmpArray[rightEnd];
    }

    // ====================================================
    //                    QUICKSORT
    // ====================================================

    public static <AnyType extends Comparable<? super AnyType>> void quicksort(AnyType[] a) {
        quicksort(a, 0, a.length - 1);
    }

    private static final int CUTOFF = 10;

    private static <AnyType extends Comparable<? super AnyType>> void quicksort(AnyType[] a, int low, int high) {
        if (low + CUTOFF > high)
            insertionSort(a, low, high);
        else {
            // Sort low, middle, high
            int middle = (low + high) / 2;
            if (a[middle].compareTo(a[low]) < 0)
                swapReferences(a, low, middle);
            if (a[high].compareTo(a[low]) < 0)
                swapReferences(a, low, high);
            if (a[high].compareTo(a[middle]) < 0)
                swapReferences(a, middle, high);

            // Place pivot at position high - 1
            swapReferences(a, middle, high - 1);
            AnyType pivot = a[high - 1];

            // Begin partitioning
            int i, j;
            for (i = low, j = high - 1; ; ) {
                while (a[++i].compareTo(pivot) < 0)
                    ;
                while (pivot.compareTo(a[--j]) < 0)
                    ;
                if (i >= j)
                    break;
                swapReferences(a, i, j);
            }

            // Restore pivot
            swapReferences(a, i, high - 1);

            quicksort(a, low, i - 1);    // Sort small elements
            quicksort(a, i + 1, high);   // Sort large elements
        }
    }

    private static <AnyType extends Comparable<? super AnyType>> void insertionSort(AnyType[] a, int low, int high) {
        for (int p = low + 1; p <= high; p++) {
            AnyType tmp = a[p];
            int j;

            for (j = p; j > low && tmp.compareTo(a[j - 1]) < 0; j--)
                a[j] = a[j - 1];
            a[j] = tmp;
        }
    }

    public static final <AnyType> void swapReferences(AnyType[] a, int index1, int index2) {
        AnyType tmp = a[index1];
        a[index1] = a[index2];
        a[index2] = tmp;
    }

    // ====================================================
    //                        MAIN
    // ====================================================


    private static <AnyType extends Comparable<? super AnyType>> void resetArray(AnyType masterArr[], AnyType copyArr[])
    {
        for (int i = 0; i < masterArr.length; i++) {
            copyArr[i] = masterArr[i];
        }
    }

    private static <AnyType extends Comparable<? super AnyType>> void sort(AnyType[] masterArr, AnyType[] copyArr,
                                                                           String sort, FileWriter w ) throws IOException {
        long begin;
        double end = -1;
        StringBuilder sb = new StringBuilder();

        for( int i = 1; i <= RUNS; i++ )
        {
            resetArray( masterArr, copyArr );
            if( sort == "Shellsort" )
            {
                begin = System.nanoTime();
                shellsort( copyArr );
                end = (double) ( System.nanoTime() - begin ) / SECOND_FACTOR;
            }
            else if ( sort == "Mergesort" )
            {
                begin = System.nanoTime();
                mergeSort( copyArr );
                end = (double) ( System.nanoTime() - begin ) / SECOND_FACTOR;
            }
            else if ( sort == "Heapsort" )
            {
                begin = System.nanoTime();
                heapsort( copyArr );
                end = (double) ( System.nanoTime() - begin ) / SECOND_FACTOR;
            }
            System.out.println( "\t" + sort + " run " + i + " time taken: " + end + " seconds");
            if( i == RUNS )
                sb.append( end + "\n");
            else
                sb.append( end + ",");
        }

        w.append( sb );
    }

    private static long SECOND_FACTOR = 1000000000;
    private static int MAX_VALUE = 1000;
    private static int RUNS = 3;

    public static void main( String [] args ) throws IOException {
        /* todo: generate arrays
         * todo: each array should be at least 2x the previous; at least 0.5 seconds to complete sort
         * todo: 3 arrays, make copy each time probably? have to run 3 times each sort
         * todo: write results to a .csv?
         */

        FileWriter w = new FileWriter( new File( "data.csv" ), true );

        Integer[] masterArr1 = new Integer[ 1000000 ];
        Integer[] masterArr2 = new Integer[ 5000000 ];
        Integer[] masterArr3 = new Integer[ 10000000 ];

        Integer[] copyArr1 = new Integer [ masterArr1.length ];
        Integer[] copyArr2 = new Integer [ masterArr2.length ];
        Integer[] copyArr3 = new Integer [ masterArr3.length ];

        Random r = new Random();

        System.out.println("Populating arrays...");
        for (int i = 0; i < masterArr1.length; i++) {
            masterArr1[i] = new Integer(r.nextInt(MAX_VALUE));
        }
        System.out.print("arr1 popluated... ");

        for (int i = 0; i < masterArr2.length; i++) {
            masterArr2[i] = new Integer(r.nextInt(MAX_VALUE));
        }
        System.out.print("arr2 popluated... ");

        for (int i = 0; i < masterArr3.length; i++) {
            masterArr3[i] = new Integer(r.nextInt(MAX_VALUE));
        }
        System.out.println("arr3 popluated... ");

        System.out.println("Population completed.");

        w.append("Shellsort\n");
        System.out.println("----- Array 1: 1,000,000 items -----");
        sort( masterArr1, copyArr1, "Shellsort", w );
        System.out.println("----- Array 2: 5,000,000 items -----");
        sort( masterArr2, copyArr2, "Shellsort", w );
        System.out.println("----- Array 3: 10,000,000 items -----");
        sort( masterArr3, copyArr3, "Shellsort", w );

        w.append("Mergesort\n");
        System.out.println("\n----- Array 1: 1,000,000 items -----");
        sort( masterArr1, copyArr1, "Mergesort", w );
        System.out.println("----- Array 2: 5,000,000 items -----");
        sort( masterArr2, copyArr2, "Mergesort", w );
        System.out.println("----- Array 3: 10,000,000 items -----");
        sort( masterArr3, copyArr3, "Mergesort", w );

        w.append("Heapsort\n");
        System.out.println("\n----- Array 1: 1,000,000 items -----");
        sort( masterArr1, copyArr1, "Heapsort", w );
        System.out.println("----- Array 2: 5,000,000 items -----");
        sort( masterArr2, copyArr2, "Heapsort", w );
        System.out.println("----- Array 3: 10,000,000 items -----");
        sort( masterArr3, copyArr3, "Heapsort", w );

        w.close();
    }
}
