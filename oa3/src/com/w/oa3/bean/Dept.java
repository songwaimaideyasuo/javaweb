package com.w.oa3.bean;

import java.util.Objects;

/**
*一个普通的java类，这个类可以封装零散的数据，代表一个部门对象
 */

public class Dept {
    private String deptno;
    private String dname;
    private String loc;

    public Dept(String deptno, String dname, String loc) {
        this.deptno = deptno;
        this.dname = dname;
        this.loc = loc;
    }

    public Dept() {
    }

    public void setDeptno(String deptno) {
        this.deptno = deptno;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getDeptno() {
        return deptno;
    }

    public String getDname() {
        return dname;
    }

    public String getLoc() {
        return loc;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "deptno='" + deptno + '\'' +
                ", dname='" + dname + '\'' +
                ", loc='" + loc + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dept dept = (Dept) o;
        return Objects.equals(deptno, dept.deptno) &&
                Objects.equals(dname, dept.dname) &&
                Objects.equals(loc, dept.loc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deptno, dname, loc);
    }
}
