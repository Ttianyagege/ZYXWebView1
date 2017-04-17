package wangtiansoft.com.zyxwebview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    @Bind(R.id.btn_code)
    Button btnCode;
    @Bind(R.id.btn_view)
    Button btnView;
    @Bind(R.id.web_view)
    WebView webView;
    @Bind(R.id.edit_view)
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        webShareManager.getInstance().webContent = ReadTxtFile();
        btnCode.setOnClickListener(this);
        btnView.setOnClickListener(this);
        webView.getSettings().setDefaultTextEncodingName("UTF-8");
        editText.addTextChangedListener(this);
    }
    //读取文本文件中的内容
    public String ReadTxtFile() {
        String content = ""; //文件内容字符串
        try {
            InputStream instream = getResources().getAssets().open("info.txt");
            if (instream != null) {
                InputStreamReader inputreader = new InputStreamReader(instream,"UTF-8");
                BufferedReader buffreader = new BufferedReader(inputreader);
                String line;
                //分行读取
                while ((line = buffreader.readLine()) != null) {
                    content += line + "\n";
                }
                instream.close();
            }
        } catch (java.io.FileNotFoundException e) {
            Log.d("TestFile", "The File doesn't not exist.");
        } catch (IOException e) {
            Log.d("TestFile", e.getMessage());
        }
        return content;
    }
    @Override
    protected void onResume() {
        super.onResume();
        editText.setText(webShareManager.getInstance().webContent);
        webView.reload();
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()){
            case R.id.btn_code:
                bundle.putString(config.webContent,editText.getText().toString());
                bundle.putString(config.webUrl,"");
                JumpItent.jump(this,webCodeActivity.class,bundle);
                break;
            case R.id.btn_view:
                bundle.putString(config.webContent,editText.getText().toString());
                bundle.putString(config.webContent,"");
                webShareManager.getInstance().webContent = editText.getText().toString();
                JumpItent.jump(this,webViewActivity.class,bundle);
                break;
            default:
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        webView.loadDataWithBaseURL(null,editText.getText().toString(),"text/html","UTF-8",null);
        webShareManager.getInstance().webContent = editText.getText().toString();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
