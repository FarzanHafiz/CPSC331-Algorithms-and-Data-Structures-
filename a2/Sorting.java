import java.util.*;

public class Sorting {

	public static void main(String args[]) {
		Scanner reader = new Scanner(System.in);
		// read size of input
		int n = reader.nextInt();
		int[] numbers = new int[n];
		// read numbers
		for(int i = 0; i < n; i++)
			numbers[i] = reader.nextInt();

		AbstractSort sorting;
		// run sorting algorithms
		sorting = new InsertionSort();
		run(sorting, numbers);
		
		sorting = new HeapSort();
		run(sorting, numbers);
		
		sorting = new QuickSort();
		run(sorting, numbers);
		
		sorting = new QuickSortImproved();
		run(sorting, numbers);
	}
	
	private static void run(AbstractSort sorting, int[] numbers) {
		int[] temp = Arrays.copyOf(numbers, numbers.length);
		long startTime = System.currentTimeMillis();
		sorting.sort(temp);
		long endTime = System.currentTimeMillis();
		System.err.println("Duration: " + (endTime-startTime) + " ms");
		System.out.println(Arrays.toString(temp));
		int[] temp2 = Arrays.copyOf(numbers, numbers.length);
		Arrays.sort(temp2);
		if(Arrays.toString(temp).equals(Arrays.toString(temp2)))
			System.err.println("Correct");
		else
			System.err.println("Wrong");
	}
}

interface AbstractSort {
	public void sort(int[] numbers);
}

class InsertionSort implements AbstractSort {
	public void sort(int[] numbers) {								
		// TODO implement insertion sort
		int i, j, n, tmp;												//declaring variables where i, j is loop control and n is size of array.
		n = numbers.length;												//assigning array length to n.
		for(i = 1; i < n; i++){										//for loop start.
			j = i;
			while((j > 0) && (numbers[j] < numbers[j-1])){				//while next element is bigger than current element, swap them.	
				tmp = numbers[j];										//implementing swap method.
				numbers[j] = numbers[j-1];
				numbers[j-1] = tmp;		
				j--;					
			}															//end while
		}																//end for
	}																	//end sort function
}

class HeapSort implements AbstractSort {
	private static int n;
	public void sort(int[] numbers) {
		// TODO implement heap sort
		createHeap(numbers);
		for(int i = n; i > 0; i--){
			swap(numbers, 0, i);
			n--;
			maxHeap(numbers, 0);
		}
	}
	public static void createHeap(int[] numbers){						//function to create a heap from the array.
		n = numbers.length-1;
		for(int i = n/2; i >= 0; i--){
			maxHeap(numbers, i);										//maxHeap call.
		}
	}
	public static void maxHeap(int[] numbers, int i){					//function for sorting the heap.
		int left, right, max;
		left = 2*i;
		right = 2*i+1;
		max = i;
		if((left <= n) && (numbers[left] > numbers[i])){
			max = left;
		}
		if((right <= n) && (numbers[right] > numbers[max])){
			max = right;
		}
		if(max != i){
			swap (numbers, i, max);
			maxHeap(numbers, max);										//recursive call.
		}
	}
	public static void swap(int[] numbers, int i, int j){				//swap function for swapping two elemnets in a array.
		int tmp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = tmp;
	}
}

class QuickSort implements AbstractSort {
	public void sort(int[] numbers) {
		// TODO implement quick sort
		int low, high;
		low = 0;
		high = numbers.length-1;
		quickSort(numbers, low, high);									
	}
	public int DPartition(int[] numbers, int low, int high){			//function for partitioning.
		int p, i, j, tmp;												//declaring variables.
		p = numbers[high];												//assigning the pivot value.
		i = low;
		j = high - 1;
		while(i <= j){													
			while((i <= j) && (numbers[i] <= p)){						//sweep rightward until an element larger than the pivot is found.
				i++;
			}
			while((j >= i) && (numbers[j] >= p)){						//sweep leftward until an element smaller the pivot is found.
				j--;
			}
			if(i < j){
				tmp = numbers[i];
				numbers[i] = numbers[j];
				numbers[j] = tmp;
			}		
		}																//put the pivot in the final place.
		tmp = numbers[i];
		numbers[i] = numbers[high];
		numbers[high] = tmp;
		return i;
	}
	public void quickSort(int[] numbers, int low, int high){
		int q;
		if(low < high){
			q = DPartition(numbers, low, high);
			quickSort(numbers, low, q-1);
			quickSort(numbers, q+1, high);
		}
	}
}

class QuickSortImproved implements AbstractSort {
	public void sort(int[] numbers) {
		// TODO implement improved version of quick sort
	}
}

