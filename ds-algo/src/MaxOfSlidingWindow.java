import java.util.ArrayDeque;

/**
 Maximum of Sliding Window: Given an array A and an integer K, find the maximum element in
 each sliding window of size K.

 For example,
 if A = [4,6,5,2,4,7] and K = 3, windows are as follows:
 [ 4,6,5 ,2,4,7] : Max = 6
 [4, 6,5,2 ,4,7] : Max = 6
 [4,6, 5,2,4 ,7] : Max = 5
 [4,6,5, 2,4,7 ] : Max = 7

 Output: 6, 6, 5, 7
 */
public class MaxOfSlidingWindow {

    ArrayDeque<Integer> doubleEndedQueue;

    public MaxOfSlidingWindow(int k) {
        doubleEndedQueue = new ArrayDeque<>(k);
    }

    public void printMinOfSlidingWindow (int[] arr, int k) {
        for (int i = 0; i < arr.length; i++) {
            // Taking out elements if it is out of the window
            if (!doubleEndedQueue.isEmpty() && doubleEndedQueue.peek() < i-k+1)
                doubleEndedQueue.poll();

            // We always add from the back of the doubleEnded Queue - so if the element is less than the element coming in the queue
            // We just remove that element (This is to maintain the decreasing order in the doubly ended queue - like a stack pile).
            while (!doubleEndedQueue.isEmpty() && arr[doubleEndedQueue.peekLast()] > arr[i])
                doubleEndedQueue.pollLast();

            // adding the element to double ended queue.
            doubleEndedQueue.offer(i);

            // We start printing after the window size is reached.
            if (i-k+1 >= 0)
                System.out.print(arr[doubleEndedQueue.peek()]+ " ");
        }
    }


    public void printMaxOfSlidingWindow (int[] arr, int k) {
        for (int i = 0; i < arr.length; i++) {
            // Taking out elements if it is out of the window
            if (!doubleEndedQueue.isEmpty() && doubleEndedQueue.peek() < i-k+1)
                doubleEndedQueue.poll();

            // We always add from the back of the doubleEnded Queue - so if the element is less than the element coming in the queue
            // We just remove that element (This is to maintain the decreasing order in the doubly ended queue - like a stack pile).
            while (!doubleEndedQueue.isEmpty() && arr[doubleEndedQueue.peekLast()] < arr[i])
                doubleEndedQueue.pollLast();

            // adding the element to double ended queue.
            doubleEndedQueue.offer(i);

            // We start printing after the window size is reached.
            if (i-k+1 >= 0)
                System.out.print(arr[doubleEndedQueue.peek()]+ " ");
        }
    }

    public static void main (String[] args) {
        int[] arr = {4, 6, 5, 2, 4, 7};
        int k = 3;
        MaxOfSlidingWindow maxOfSlidingWindow2 = new MaxOfSlidingWindow(k);
        maxOfSlidingWindow2.printMaxOfSlidingWindow(arr, k);
        maxOfSlidingWindow2.printMinOfSlidingWindow(arr, k);
    }
}
