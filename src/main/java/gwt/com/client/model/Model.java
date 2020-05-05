package gwt.com.client.model;


import java.util.Random;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.shared.SimpleEventBus;

import gwt.com.client.controller.GWTEvent;

public class Model {
	private int[] numbers;

	private boolean ascOrder=false;

	private static final int MAX_RANDOM_NUMBER_VALUE = 1000;

	public void setNumbers(int count) {
		boolean thirteenOrLess = false;
		numbers = new int[count];
		Random rand = new Random();
		for (int i = 0; i < count; i++) {
			numbers[i] = rand.nextInt(MAX_RANDOM_NUMBER_VALUE) + 1;
			if (!thirteenOrLess && numbers[i] <= 30) {
				thirteenOrLess = true;
			}
		}
		if (!thirteenOrLess) {
			if (count > 1) {
				numbers[rand.nextInt(numbers.length - 1)] = rand.nextInt(29) + 1;
			} else {
				numbers[0] = rand.nextInt(29) + 1;
			}
		}
	}

	public int getColumns() {
		double column = Math.floorDiv(numbers.length - 1, 10) + 1;
		return (int) Math.ceil(column);
	}

	public int getColumns(int size) {
		double column = Math.floorDiv(size - 1, 10) + 1;
		return (int) Math.ceil(column);
	}

	public int getRow(int idx) {
		
		return  idx - 10 * (getColumns(idx)-1);
	}

	public void sortArray(SimpleEventBus bus) {
		quickSort(numbers, 0, numbers.length - 1, ascOrder,bus);
		ascOrder=!ascOrder;
	}

	public  void quickSort(int arr[], int begin, int end, boolean ascOrder,SimpleEventBus bus) {
		if (begin < end) {
			int partitionIndex = partition(arr, begin, end, ascOrder);
			quickSort(arr, begin, partitionIndex - 1, ascOrder,bus);
			sendToRepaint(arr,bus);
			quickSort(arr, partitionIndex + 1, end, ascOrder,bus);
			sendToRepaint(arr,bus);
		}
	}

	private int partition(int arr[], int begin, int end, boolean ascOrder) {
		int pivot = arr[end];
		int i = (begin - 1);
		boolean needSwap;

		for (int j = begin; j < end; j++) {
			needSwap = false;
			if ((ascOrder && arr[j] <= pivot) || (!ascOrder && arr[j] >= pivot)) {
				needSwap = true;
			}
			if (needSwap) {
				i++;
				int swapTemp = arr[i];
				arr[i] = arr[j];
				arr[j] = swapTemp;
			}
		}

		int swapTemp = arr[i + 1];
		arr[i + 1] = arr[end];
		arr[end] = swapTemp;
		return i + 1;
	}

	private  void sendToRepaint(int[] arr,SimpleEventBus bus) {
		int [] tmp = new int[arr.length];
		System.arraycopy(arr, 0, tmp, 0, arr.length);
		bus.fireEvent(new GWTEvent(tmp));
	}

	public int getNumbersSize() {

		return numbers.length;
	}

	public int[] getNumbers() {
		return numbers;
	}
}
