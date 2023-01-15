
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {
    final static int smallSizeArray = 50;
    final static int bigSizeArray = 1_000_000;

    public static void main(String[] args) {

        int [] arraySmal = fillArray(smallSizeArray);
        int [] arrayBig = fillArray(bigSizeArray);


        // однопоточный расчет, small array
        long lStartTime1 = System.currentTimeMillis();
        singleThreadCounting(arraySmal);
        long lEndTime1 = System.currentTimeMillis();
        long output1 = lEndTime1 - lStartTime1;
        System.out.println("Однопоточный подсчет, маленький массив, время расчета: " + output1+ " мс.");

        // однопоточный расчет, big array
        long lStartTime2 = System.currentTimeMillis();
        singleThreadCounting(arrayBig);
        long lEndTime2 = System.currentTimeMillis();
        long output2 = lEndTime2 - lStartTime2;
        System.out.println("Однопоточный подсчет, большой массив, время расчета: " + output2+ " мс.");

        // многопоточный расчет, small array
        long lStartTime3 = System.currentTimeMillis();
        multyTgreadCounting(arraySmal);
        long lEndTime3 = System.currentTimeMillis();
        long output3 = lEndTime3 - lStartTime3;
        System.out.println("Многопоточный подсчет, маленький массив, время расчета: " + output3+ " мс.");

        // многопоточный расчет, big array
        long lStartTime4 = System.currentTimeMillis();
        multyTgreadCounting(arraySmal);
        long lEndTime4 = System.currentTimeMillis();
        long output4 = lEndTime4 - lStartTime4;
        System.out.println("Многопоточный подсчет, большой массив, время расчета: " + output4+ " мс.");

    }

    public static void singleThreadCounting(int[] array){
        ArraySum arraySum = new ArraySum(array);
        int sumElements = arraySum.compute();
        int arithmeticMean = sumElements/array.length;
        System.out.println("Сумма элементов массива: "+sumElements);
        System.out.println("Среднее арифметическое элементов массива: "+arithmeticMean);
    }
    public static void multyTgreadCounting(int[] array){
        ArraySum arraySum = new ArraySum(array);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int sumElements = forkJoinPool.invoke(arraySum);
        int arithmeticMean = sumElements/array.length;
        System.out.println("Сумма элементов массива: "+sumElements);
        System.out.println("Среднее арифметическое элементов массива: "+arithmeticMean);
    }

    public static int[] fillArray(int sizeArray){
        int [] array=new int[sizeArray];
        Random random = new Random();
        for (int i = 0; i < sizeArray; i++) {
            array[i] = random.nextInt(100);

        }
        return array;
    }
}
