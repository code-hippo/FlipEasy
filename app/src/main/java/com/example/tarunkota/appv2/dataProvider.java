package com.example.tarunkota.appv2;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tarun.kota on 06/06/15.
 */
public class dataProvider{

     List<Node> allNodes;



    public dataProvider()
    {

    }

    public  Node getNode(String s)
    {
        for(int i=0;i<allNodes.size();i++)
        {
            if(s.equals(allNodes.get(i).Name)) {
                return allNodes.get(i);
            }
        }

        return null;
    }

    public  Node getParent(String s)
    {
        for(int i=0;i<allNodes.size();i++)
        {
           if(s.equals(allNodes.get(i).Name)) {
               return allNodes.get(i).parent;
           }
        }
        return null;
    }

    public void addChildren(String p, List<String> c)
    {
        Node P = getNode(p);
//        if(P!=null)
//        {
//            Toast.makeText(getApplicationContext(), "msg msg", Toast.LENGTH_SHORT).show();
//
//        }
        Log.i("yo", "yo2");

        for(int i = 0;i<c.size();i++)
        {
            Node l = new Node();
            l.Name = c.get(i);

            l.parent=P;
           P.children.add(l);
          allNodes.add(l);
        }
    }

    public  void addRoot(String s)
    {
        Node r = new Node();
        r.parent = null;
        r.Name = s;
        allNodes = new ArrayList<>();
        allNodes.add(r);
    }

    public  List<Node> getChildren(String S)
    {
        Node p = getNode(S);
        return p.children;
    }


}
