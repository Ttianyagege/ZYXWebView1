package wangtiansoft.com.zyxwebview;


/**
 * Created by apple on 2016/12/22.
 */
public class webShareManager {

    public String webContent;

    private static webShareManager instance;

    public static webShareManager getInstance() {
        if (instance == null) {
            instance = new webShareManager();
        }
        return instance;
    }


}
