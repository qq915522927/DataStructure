
public class LinkedListPractive {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * 递归实现
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        if(head == null){
            return null;
        }
        if(head.val == val){
            head = removeElements(head.next, val);
        } else{
            head.next = removeElements(head.next, val);
        }
        return head;
    }
}
