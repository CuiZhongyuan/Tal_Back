package com.taltools.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class Node {
    private int value;        //节点的值
    private Node node;        //此节点，数据类型为Node
    private Node left;        //此节点的左子节点，数据类型为Node
    private Node right;       //此节点的右子节点，数据类型为Node
    public Node(int value) {
        this.value=value;
        this.left=null;
        this.right=null;
    }

}
