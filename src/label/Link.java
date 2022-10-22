package label;

public class Link extends Element {
    public String linkname;
    public String linkcontent;
    public int times; //用来表示阅读次数
    public int flag; //0代为没有读过，1代表读过

    public Link(String linkname, String linkcontent) {
        this.type = "link";
        this.linkname = linkname;
        this.linkcontent = linkcontent;
        this.times = 0;
        this.flag = 0;
    }

    @Override
    public String getType() {
        return super.getType();
    }

    public String getLinkname() {
        return linkname;
    }

    public void setLinkname(String linkname) {
        this.linkname = linkname;
    }

    public String getLinkcontent() {
        return linkcontent;
    }

    public void setLinkcontent(String linkcontent) {
        this.linkcontent = linkcontent;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getFlag() {
        return flag;
    }

    public String getName(){
        return linkname;
    }
}
