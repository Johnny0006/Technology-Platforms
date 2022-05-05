package pg.edu.pl;

import java.util.Comparator;

public class ElementComparator implements Comparator<Element> {

    @Override
    public int compare(Element m1, Element m2){
        int  ret = m1.getCreateDate().compareTo(m2.getCreateDate());

        if(ret==0){
            ret = m1.getLevel()-m2.getLevel();
        }

        if(ret==0){
            ret = m1.getName().compareTo(m2.getName());
        }

        return ret;
    }
}
