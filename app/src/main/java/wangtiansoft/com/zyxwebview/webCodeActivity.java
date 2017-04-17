package wangtiansoft.com.zyxwebview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;

public class webCodeActivity extends AppCompatActivity implements TextWatcher {

    @Bind(R.id.edit_code)
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_code);
        ButterKnife.bind(this);
        String content = getIntent().getExtras().getString(config.webContent);
        String url = getIntent().getExtras().getString(config.webUrl);
        if (content != null){
            editText.setText(content);
        }else if (url != null){
            editText.setText(url);
        }
        editText.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        webShareManager.getInstance().webContent = editText.getText().toString();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
