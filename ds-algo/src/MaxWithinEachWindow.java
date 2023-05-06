package com.ds.demo;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class MaxWithinEachWindow
{
    public static void main (String[] a) {
        int []input = {4, 6, 5, 2, 4, 7};
        int windowSize = 3;

        slidingWindowMax(input, windowSize);
    }
    private static void slidingWindowMax (int[] a,
                                         int windowSize)
    {
        if (a == null || a.length == 0 || windowSize <= 0) {
            return;
        }
        QueueWithMax q = new QueueWithMax();
        try {
            // add first k elements
            for (int i = 0; i < windowSize; i++) {
                q.enqueue(a[i]);
            }
            System.out.println(q.findMax());

            // add the rest one by one
            for (int i = windowSize; i < a.length; i++) {
                q.dequeue();
                q.enqueue(a[i]);
                System.out.println(q.findMax());
            }
        }
        catch (QueueEmptyException e) {
            // should not happen
            e.printStackTrace();
        }
    }

    public static class QueueWithMax
    {
        Queue<Integer> main;
        Deque<Integer> max;

        public QueueWithMax ()
        {
            main = new LinkedList<>();
            max = new LinkedList<>();
        }

        public void enqueue (int item)
        {
            main.add(item);
            while (!max.isEmpty() && max.getLast() < item) {
                max.removeLast();
            }
            max.add(item);
        }

        public void dequeue () throws QueueEmptyException
        {
            if (main.isEmpty()) {
                throw new QueueEmptyException();
            }
            int item = main.remove();
            if (max.getFirst() == item) {
                max.remove();
            }
        }

        public int findMax () throws QueueEmptyException
        {
            if (max.isEmpty()) {
                throw new QueueEmptyException();
            }
            return max.getFirst();
        }
    }

    /*
     * Helper code, ask the interviewer if they want you to implement.
     */
    public static class QueueEmptyException extends Exception
    {
        public QueueEmptyException ()
        {
        }
    }
}
