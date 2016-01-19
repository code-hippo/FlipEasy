package com.example.tarunkota.appv2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tarun.kota on 06/06/15.
 */
public class Node
{
    String Name;
    Node parent;
    List<Node> children;

    public Node()
    {
        children = new ArrayList<>();
    }
}

