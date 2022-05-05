package pg.edu.pl;

import java.util.*;


public class Element implements Comparable<Element>{
    private String name;
    private int level;
    private Date createDate;
    private Set<Element> childs;


    public Element(String name, Date createDate, int level) {
        this.name = name;
        this.createDate=createDate;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public int getLevel() { return level; }

    public Date getCreateDate() { return createDate; }

    public Set<Element> getChilds() { return childs; }



    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setCreateDate(Date createDate) { this.createDate = createDate; }

    public void setChilds(Set<Element> childs) { this.childs = childs; }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Element element = (Element) o;

        if (getLevel() != element.getLevel()) return false;
        if (!getName().equals(element.getName())) return false;
        if (!getCreateDate().equals(element.getCreateDate())) return false;
        return getChilds() != null ? getChilds().equals(element.getChilds()) : element.getChilds() == null;
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getLevel();
        result = 31 * result + getCreateDate().hashCode();
        result = 31 * result + (getChilds() != null ? getChilds().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name + "(level=" + level + ", createDate="+ createDate+");";

    }

    @Override
    public int compareTo(Element o){
        int ret = getName().compareTo(o.getName());

        if (ret == 0) {
            ret = getLevel()-o.getLevel();
        }

        if (ret == 0) {
            ret = getCreateDate().compareTo(o.getCreateDate());
        }

        return ret;
    }



}
