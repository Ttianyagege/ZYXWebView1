package wangtiansoft.com.zyxwebview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class webViewActivity extends AppCompatActivity {

    @Bind(R.id.web_view)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        String content = webShareManager.getInstance().webContent;
        String url = getIntent().getExtras().getString(config.webUrl);
        if (content != null){
            webView.loadDataWithBaseURL(null,content,"text/html","UTF-8",null);
        }else if (url != null){
            webView.loadUrl(url);
        }


    }
}
