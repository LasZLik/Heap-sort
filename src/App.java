public class App {
    public static void main(String[] args) throws Exception {
        int[] array = { 45, 12, 33, 10, 7, 19, 28, 5, 17, 2 ,200, 100, 50, 75, 25, 150 };
        GUI gui = new GUI(array);
        gui.setVisible(true);
        heapSort(array, gui);
        System.out.println("Sorted array:");
        for (int num : array) {
            System.out.print(num + " ");
        }
    }

    public static void heapSort(int[] arr, GUI gui) throws InterruptedException {
        int n = arr.length;
        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i, gui);
        }
        // One by one extract an element from heap
        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            gui.setArray(arr, i);
            Thread.sleep(400);
            heapify(arr, i, 0, gui);
        }
        gui.setArray(arr, -1);
    }

    public static void heapify(int[] arr, int n, int i, GUI gui) throws InterruptedException {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        if (l < n && arr[l] > arr[largest])
            largest = l;
        if (r < n && arr[r] > arr[largest])
            largest = r;
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            gui.setArray(arr, largest);
            Thread.sleep(400);
            heapify(arr, n, largest, gui);
        }
    }
}
