import java.util.ArrayList;

/**
 * Given a singly linked list
 *
 *     L: L0 → L1 → … → Ln-1 → Ln,
 * reorder it to:
 *
 *     L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …
 * You must do this in-place without altering the nodes’ values.
 *
 * For example,
 *
 * Given {1,2,3,4}, reorder it to {1,4,2,3}.
 */

/**
 * https://www.interviewbit.com/problems/reorder-list/
 */


/**
 * Here we use the two pointer approach to get to the mid point of the list. Then we reverse the second half of the list and use the two lists to get tge  result.
 * Eg:- A = 1 -> 2 -> 3 -> 4 -> 5
 *      mid point = 3
 *      tempA = 1 -> 2 -> 3
 *      tempB = 4 -> 5 , this list is reversed => 5 -> 4
 *      Result: 1 -> 5 -> 2 -> 4 -> 3
 *
 */
public class ReorderList {
    static class ListNode {
      public int val;
      public ListNode next;

      ListNode(int x) { val = x; next = null; }

        @Override
        public String toString() {
            ArrayList<Integer> arr = new ArrayList<>();
            ListNode listNode= this;
            while (listNode != null) {
                arr.add(listNode.val);
                listNode = listNode.next;
            }
            return arr.toString();
        }
   }

   public static ListNode reverseList (ListNode B) {
        ListNode current, next, prev;
        current = B;
        prev = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        B = prev;
        return B;
   }

   public static  void mergeList (ListNode A, ListNode B) {
        while (A != null && B != null) {
            ListNode tempA = A.next;
            ListNode tempB = B.next;
            A.next = B;
            B.next = tempA;
            A = tempA;
            B = tempB;
        }
   }

   public static ListNode reorderList (ListNode A) {
        ListNode head = A;
        ListNode first = A;
        ListNode second = A;
        while (second != null && second.next!= null) {
            first = first.next;
            second = second.next.next;
        }
        ListNode B = first.next;
        B = reverseList(B);
        first.next = null;
        mergeList(head, B);
        return A;
   }

    public static void main (String[] args) {
        ListNode A = new ListNode(1);
        ListNode B = new ListNode(2);
        ListNode C = new ListNode(3);
        ListNode D = new ListNode(4);
        ListNode E = new ListNode(5);
        A.next = B;
        B.next = C;
        C.next = D;
        D.next = E;
        System.out.println(A);
        System.out.println(reorderList(A));
    }
}
