package pg.edu.pl;


import java.io.File;
import java.io.IOException;
import java.lang.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.*;


public class Main{
    
    public static void print(Element m, int depth) throws IOException {

        BasicFileAttributes attr = Files.readAttributes(Paths.get(m.getName()), BasicFileAttributes.class);

        if(attr.isDirectory()) System.out.print("+".repeat(depth));
        else System.out.print("-".repeat(depth));
        System.out.print(m+"\n");
        if(m.getChilds()!=null) {
            for (Element elem : m.getChilds()) {
                print(elem, depth + 1);
            }
        }
    }


    public static int count(Map<Element, Integer> map, Element m){
        int n=0;
        if(m.getChilds()!=null) {
            n+=m.getChilds().size();
            for (Element elem : m.getChilds()) {
                n+=count(map, elem);
            }
        }
        map.put(m, n);
        return n;
    }


    public static void create(Element e, int level, String variation) throws IOException {
        File file = new File(e.getName());

        if(variation.equals("1")) e.setChilds(new HashSet<Element>());
        else if(variation.equals("2")) e.setChilds(new TreeSet<Element>());
        else e.setChilds(new TreeSet<Element>(new ElementComparator()));

        if(file.listFiles()!=null) {
            for (File f : file.listFiles()) {

                BasicFileAttributes attr = Files.readAttributes(Paths.get(String.valueOf(f)), BasicFileAttributes.class);
                Element ele = new Element(String.valueOf(f), new Date(attr.creationTime().toMillis()), level);
                e.getChilds().add(ele);
                create(ele, level+1, variation);
            }
        }
    }
    
    
    public static void main(String[] args) throws IOException {

        Path path = Paths.get("D:\\JO");

        BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
        Element root = new Element(String.valueOf(path), new Date(attr.creationTime().toMillis()), 0);

        Set set;
        Map<Element, Integer> map;

        if(args[0]=="1") map = new HashMap();
        else if(args[0]=="2") map = new TreeMap();
        else map = new TreeMap(new ElementComparator());

        create(root, 1, args[0]);
        print(root, 0);

        count(map, root);

        System.out.print("-----------------------------\n");
        System.out.print("-----------------------------\n");
        System.out.print("-----------------------------\n");
        for (Map.Entry<Element, Integer> i : map.entrySet()) {
            System.out.print(i.getKey() + "  =  " + i.getValue()+"\n");
        }


       }

    }

