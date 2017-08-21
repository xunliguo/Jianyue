package com.example.asus_pc.jianyue.controller.option;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus_pc.jianyue.R;

/**
 * Created by ASUS-PC on 2017/8/18.
 */

public class FeedbackFragment extends Fragment implements View.OnClickListener {
    private EditText edit_name;
    private Button tijioa;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.feedback, null);

        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        edit_name = (EditText) inflate.findViewById(R.id.edit_name);
        tijioa = (Button) inflate.findViewById(R.id.tijioa);

        tijioa.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tijioa:
       submit();
                break;
        }
    }

    private void submit() {
        // validate
        String name = edit_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(getContext(), "留下你宝贵的意见吧：", Toast.LENGTH_SHORT).show();
            return;
        }else{
            Toast.makeText(getActivity(), "已提交给客服", Toast.LENGTH_SHORT).show();

        }

        // TODO validate success, do something


    }
}
