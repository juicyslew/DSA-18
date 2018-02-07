import java.util.*;

import static java.lang.StrictMath.floor;
import static java.lang.StrictMath.floorMod;

public class Problems {

    public static class Node {
        int val;
        Node next;

        Node(int d) {
            this.val = d;
            next = null;
        }
    }

    public static List<Integer> removeKDigits(int[] A, int k) {
        // For now, return a List that's correct size, but contains only 0s
        LinkedList<Integer> l = new LinkedList<>();
        Stack<Integer> stck = new Stack<>();
        int rmnum = 0;
        for (int i = 0; i < A.length; i++){
            while(rmnum < k && !stck.empty() && A[i] < stck.peek()){
                stck.pop();
                rmnum += 1;
            }
            stck.push(A[i]);
        }

        while (stck.size() > A.length - k){
            stck.pop();
        }
        while (!stck.empty()){
            l.addFirst(stck.pop());
        }
        return l;
    }

    public static boolean isPalindrome(Node n) {
        if (n == null || n.next == null){
            return true;
        }
        Node point = n;
        int size = 1;
        while(point.next != null){
            point = point.next;
            size++;
        }

        point = n;
        Node tmpf = n.next;
        Node tmpb = null;
        int i = 0;
        while (i < floor(size/2)){
            System.out.println("point: " + point.val);
            point.next = tmpb;
            tmpb = point;
            point = tmpf;
            tmpf = tmpf.next;
            i++;
        }
        Node head;
        if (size % 2 == 0) {
            head = point;
        }else{
            head = point.next;
        }
        point = tmpb;

        /*Node tmpb2 = tmpb.next;
        while(true){
            System.out.println("wow");
            point.next = tmpb;
            point = tmpf;
            if (point == null){
                break;
            }
            tmpf = tmpf.next;
            tmpb.next = tmpf;
            tmpb = tmpb2;
            tmpb2 = tmpb2.next;
        }*/
        //tmpf = head.next;
        //System.out.println(head);
        while (head != null){
            System.out.println(head.val + " == " + point.val);
            if(head.val != point.val){
                return false;
            }
            head = head.next;
            point = point.next;
        }
        return true;
    }

    public static String infixToPostfix(String s) {
        String[] pieces = s.split(" ");
        StringBuilder resbuild = new StringBuilder();
        Stack<String> ops = new Stack<>();
        int opsmax = 1; //if no parens set opsmax to 1, if parens set opsmax to 0;
        //Queue<String> nums = new Queue<>();
        for (String piece: pieces){
            System.out.println(piece);
            if (piece.matches("[0-9]*")){
                //nums.add(piece);
                resbuild.append(piece);
                resbuild.append(" ");
            }else if(piece.matches("[+*/\\-]")){
                if (ops.size() == opsmax){
                    resbuild.append(ops.pop());
                    resbuild.append(" ");
                }
                ops.push(piece);
            }else if(piece.matches("\\(")){
                opsmax++;
            }else if(piece.matches("\\)")){
                opsmax--;
                resbuild.append(ops.pop());
                resbuild.append(" ");
            }
        }
        while (!ops.empty()){
            resbuild.append(ops.pop());
            resbuild.append(" ");
        }
        resbuild.deleteCharAt(resbuild.length()-1);
        String res = resbuild.toString();

        return res;
    }

}
