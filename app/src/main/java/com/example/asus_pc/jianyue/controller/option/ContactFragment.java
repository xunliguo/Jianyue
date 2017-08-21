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

public class ContactFragment extends Fragment {

    private EditText edit_name;
    private Button tijioa;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viwe1 = inflater.inflate(R.layout.contact, null);

        tijioa= (Button) viwe1.findViewById(R.id.tijioa);
        edit_name=(EditText)viwe1.findViewById(R.id.edit_name);
           tijioa.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   submit();
               }
           });
        return viwe1;
    }

private  void submit(){
    String phone = edit_name.getText().toString().trim();
    if (TextUtils.isEmpty(phone)) {
        Toast.makeText(getActivity(), "手机号不能为空", Toast.LENGTH_SHORT).show();
        return;
    }else if(11<phone.length()|| phone.length()<11){
        Toast.makeText(getActivity(), "手机号格式不正确！", Toast.LENGTH_SHORT).show();
    }else{
        Toast.makeText(getActivity(), "提交成功", Toast.LENGTH_SHORT).show();

    }

}
}
