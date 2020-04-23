package business;

import com.ego.pojo.TbItem;

/**
 * @program: ego-parent
 * @author: ShyBlue
 * @create: 2020-04-09 13:09
 **/
public class TbItemChild extends TbItem {

    private String [] images;

    private boolean enough = true;

    public boolean isEnough() {
        return enough;
    }

    public void setEnough(boolean enough) {
        this.enough = enough;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }
}