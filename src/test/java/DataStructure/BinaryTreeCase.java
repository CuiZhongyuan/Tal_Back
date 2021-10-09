package DataStructure;

import com.taltools.entity.Node;
import org.junit.Test;

import java.util.LinkedList;

import java.util.Queue;

public class BinaryTreeCase {
    @Test
    public void caseTest(){
        //初始化一个数组
        Integer[] arr = new Integer[7];
        for (int i=0;i<arr.length;i++){
            arr[i]=1+i;
        }
        //数组构造成二叉树
        Node node = InitTree(arr);
        System.out.println("前序遍历结果：");
        //前序遍历
        preOrderTraverse(node);
        System.out.println();
        System.out.println("中序遍历结果：");
        //中序遍历
        inOrderTraverse(node);
        System.out.println();
        System.out.println("后序遍历结果：");
        //后序遍历
        postOrderTraverse(node);
    }

// 先序遍历
    public static void preOrderTraverse(Node node) {
        if (node == null)
            return;
        System.out.print(node.getValue() + " ");
        preOrderTraverse(node.getLeft());
        preOrderTraverse(node.getRight());
    }

// 中序遍历
    public static void inOrderTraverse(Node node) {
        if (node == null)
            return;
        inOrderTraverse(node.getLeft());
        System.out.print(node.getValue() + " ");
        inOrderTraverse(node.getRight());

    }

// 后序遍历
    public static void postOrderTraverse(Node node) {
        if (node == null)
            return;
        postOrderTraverse(node.getLeft());
        postOrderTraverse(node.getRight());
        System.out.print(node.getValue() + " ");
    }
        /**
         * 调用以构造二叉树，
         * @param vals 数组
         * @return
         */
        public static Node InitTree(Integer[] vals) {
            Node node = new Node(vals[0]);
            Queue<Node> queue = new LinkedList<>();
            int cur = 1;
            queue.offer(node);
            while (queue != null) {
                Node r = queue.poll();
                if (vals[cur] == null) {
                    r.setLeft(null);
                } else {
                    r.setLeft(new Node(vals[cur]));
                    queue.offer(r.getLeft());
                }
                if (++cur >= vals.length) {
                    break;
                }
                if (vals[cur] == null) {
                    r.setRight(null);
                } else {
                    r.setRight(new Node(vals[cur]));
                    queue.offer(r.getRight());
                }
                if (++cur >= vals.length) {
                    break;
                }
            }
            return node;
        }

}
