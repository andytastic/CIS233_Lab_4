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
        for (int gap = a.length / 2; gap > 0; gap = gap / 2 )
            for (int i = gap; i < a.length; i++) {
                AnyType tmp = a[i];
                int j = i;

                for (; j >= gap && tmp.compareTo(a[j - gap]) < 0; j -= gap)
                    a[j] = a[j - gap];
                a[j] = tmp;
            }
    }

    // ====================================================
    //                    Gonnet
    // ====================================================

    public static <AnyType extends Comparable<? super AnyType>> void gonnet( AnyType [] a )
    {
        for ( int gap = a.length / 2; gap > 0; gap = gap == 2 ? 1 : (int) ( gap / 2.2 ) )
            for (int i = gap; i < a.length; i++) {
                AnyType tmp = a[i];
                int j = i;

                for (; j >= gap && tmp.compareTo(a[j - gap]) < 0; j -= gap)
                    a[j] = a[j - gap];
                a[j] = tmp;
            }
    }

    // ====================================================
    //                    Hibbard
    // ====================================================

    public static <AnyType extends Comparable<? super AnyType>> void hibbard( AnyType [] a )
    {
        int count = 1, gap = 1;
        while( gap < a.length )
        {
            gap = (int) Math.pow( 2, count ) - 1;
            count++;
        }

        for ( ; gap > 0; gap = gap == 2 ? 1 : gap / 2)
            for (int i = gap; i < a.length; i++) {
                AnyType tmp = a[i];
                int j = i;

                for (; j >= gap && tmp.compareTo(a[j - gap]) < 0; j -= gap)
                    a[j] = a[j - gap];
                a[j] = tmp;
            }
    }

    // ====================================================
    //                    Sedgewick
    // ====================================================

    public static <AnyType extends Comparable<? super AnyType>> void sedgewick( AnyType [] a )
    {
        int [] sedArray = {0, 1, 5, 19, 41, 109, 209, 505, 929, 2161, 3905, 8929, 16001, 36289,
                64769, 146305, 260609, 587521, 1045505, 2352689, 4188161, 9427969, 16764929 };

        int count = 0, gap = 0;

        while( gap < a.length )
        {
            gap = sedArray[count];
            count++;
        }

        for ( ; gap > 0; gap = sedArray[ count = count - 1] )
        {
            for (int i = gap; i < a.length; i++) {
                AnyType tmp = a[i];
                int j = i;

                for (; j >= gap && tmp.compareTo(a[j - gap]) < 0; j -= gap)
                    a[j] = a[j - gap];
                a[j] = tmp;
            }
        }
    }

    // ====================================================
    //                    Knuth
    // ====================================================

    public static <AnyType extends Comparable<? super AnyType>> void knuth( AnyType [] a )
    {
        int n = a.length / 3;

        int gap = 1;
        while( gap < n )
            gap = (3 * gap) + 1;

        for( ; gap >= 1; gap = gap/3 )
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
        double average = 0;

        for( int i = 1; i <= RUNS; i++ )
        {
            resetArray( masterArr, copyArr );
            if( sort == "Shellsort" )
            {
                begin = System.nanoTime();
                shellsort( copyArr );
                end = (double) ( System.nanoTime() - begin ) / SECOND_FACTOR;
            }
            else if ( sort == "Quicksort" )
            {
                begin = System.nanoTime();
                quicksort( copyArr );
                end = (double) ( System.nanoTime() - begin ) / SECOND_FACTOR;
            }
            else if ( sort == "Gonnet" )
            {
                begin = System.nanoTime();
                gonnet( copyArr );
                end = (double) ( System.nanoTime() - begin ) / SECOND_FACTOR;
            }
            else if ( sort == "Hibbard" )
            {
                begin = System.nanoTime();
                hibbard( copyArr );
                end = (double) ( System.nanoTime() - begin ) / SECOND_FACTOR;
            }
            else if ( sort == "Sedgewick" )
            {
                begin = System.nanoTime();
                sedgewick( copyArr );
                end = (double) ( System.nanoTime() - begin ) / SECOND_FACTOR;
            }
            else if ( sort == "Knuth" )
            {
                begin = System.nanoTime();
                knuth( copyArr );
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
            average += end;
        }
        average /= RUNS;
        w.append(average + ",");
    }

    public static void sort( String sort, FileWriter w ) throws IOException
    {
        System.out.println("============= " + sort.toUpperCase() + " =============");
        w.append(sort + ",");
        System.out.println("----- Array 1: 500,000 items -----");
        sort( masterArr1, copyArr1, sort, w );
        System.out.println("----- Array 2: 1,000,000 items -----");
        sort( masterArr2, copyArr2, sort, w );
        System.out.println("----- Array 3: 5,000,000 items -----");
        sort( masterArr3, copyArr3, sort, w );
        System.out.println("----- Array 4: 10,000,000 items -----");
        sort( masterArr4, copyArr4, sort, w );
        w.append("\n");
    }

    private static long SECOND_FACTOR = 1000000000;
    private static int MAX_VALUE = 1000;
    private static int RUNS = 5;

    private static Integer[] masterArr1 = new Integer[ 500000 ];
    private static Integer[] masterArr2 = new Integer[ 1000000 ];
    private static Integer[] masterArr3 = new Integer[ 5000000 ];
    private static Integer[] masterArr4 = new Integer[ 10000000 ];

    private static Integer[] copyArr1 = new Integer [ masterArr1.length ];
    private static Integer[] copyArr2 = new Integer [ masterArr2.length ];
    private static Integer[] copyArr3 = new Integer [ masterArr3.length ];
    private static Integer[] copyArr4 = new Integer [ masterArr4.length ];

    public static void main( String [] args ) throws IOException {

        FileWriter w = new FileWriter( new File( "data.csv" ), true );

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
        System.out.print("arr3 popluated... ");

        for (int i = 0; i < masterArr4.length; i++) {
            masterArr4[i] = new Integer(r.nextInt(MAX_VALUE));
        }
        System.out.println("arr4 popluated... ");

        System.out.println("Population completed.");

        w.append(",\"500,000\",\"1,000,000\",\"5,000,000\",\"10,000,000\"\n");

        sort( "Shellsort", w );
        sort( "Hibbard", w );
        sort( "Knuth", w );
        sort( "Sedgewick", w );
        sort( "Gonnet", w );
        sort( "Heapsort", w );
        sort( "Mergesort", w );
        sort( "Quicksort", w );

        w.close();
    }
}
