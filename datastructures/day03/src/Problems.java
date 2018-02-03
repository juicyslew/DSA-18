import java.util.*;

import static java.lang.StrictMath.floor;

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
        List<Integer> l = new LinkedList<>();
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
            l.add(0, stck.pop());
        }
        return l;
    }

    public static boolean isPalindrome(Node n) {
        Node point = n;
        int size = 1;
        while(point.next != null){
            point = point.next;
            size++;
        }

        point = n;
        Node tmpf = n.next;
        Node tmpb = null;
        Node head;
        int i = 0;
        while (i < floor(size/2)){
            point.next = tmpb;
            tmpb = point;
            point = tmpf;
            tmpf = tmpf.next;
            i++;
        }
        head = point;

        Node tmpb2 = tmpb.next;
        while(true){
            point.next = tmpb;
            point = tmpf;
            if (point == null){
                break;
            }
            tmpf = tmpf.next;
            tmpb.next = tmpf;
            tmpb = tmpb2;
            tmpb2 = tmpb2.next;
        }
        i = 0;
        tmpf = point.next;
        while (point != null){
            if(point != tmpf){
                return false;
            }
            point = tmpf.next;
            tmpf = point.next;
            i++;
        }
        return true;
    }

    public static String infixToPostfix(String s) {
        String[] pieces = s.split(" ");
        StringBuilder resbuild= new StringBuilder();
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
