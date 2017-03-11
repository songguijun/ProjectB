package gift.songguijun.lanou.com.projectb.subscibe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import java.util.List;

import gift.songguijun.lanou.com.projectb.R;

/**
 * 语音
 */
public class VoiceActivity extends AppCompatActivity {
    private RecognizerDialog iatDialog ;
    private EditText etText ;
    private ImageView button ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice);
        etText = (EditText) findViewById(R.id.etText_voice);
        button = (ImageView) findViewById(R.id.btn_voice);
        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                SpeechUtility.createUtility(VoiceActivity.this, SpeechConstant.APPID + "=58b4e81d");
                iatDialog = new RecognizerDialog(VoiceActivity.this, mInitListener);
                iatDialog.setListener(new RecognizerDialogListener() {
                    String resultJson = "[";

                    @Override
                    public void onResult(RecognizerResult recognizerResult, boolean b) {
                        if (!b) {
                            resultJson += recognizerResult.getResultString() + ",";
                        } else {
                            resultJson += recognizerResult.getResultString() + "]";
                        }
                        if (b) {
                            Gson gson = new Gson();
                            List<DictationResult> resultList = gson.fromJson(resultJson,
                                    new TypeToken<List<DictationResult>>() {
                                    }.getType());
                            String result = "";
                            for (int i = 0; i < resultList.size() - 1; i++) {
                                result += resultList.get(i).toString();
                            }
                            etText.setText(result);
                            //获取焦点
                            etText.requestFocus();
                            //将光标定位到文字最后，以便修改
                            etText.setSelection(result.length());
                        }
                    }


                    @Override
                    public void onError(SpeechError speechError) {
                        speechError.getPlainDescription(true);
                    }
                });
                iatDialog.show();
                return true;
            }
        });

    }
        public static final String TAG = "VoiceActivity";
        private InitListener mInitListener = new InitListener() {
            @Override
            public void onInit(int code) {
                Log.d(TAG, "SpeechRecognizer init() code = " + code);
                if (code != ErrorCode.SUCCESS) {
                    Toast.makeText(VoiceActivity.this, "初始化失败，错误码：" + code, Toast.LENGTH_SHORT).show();
                }
            }
        };

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}



