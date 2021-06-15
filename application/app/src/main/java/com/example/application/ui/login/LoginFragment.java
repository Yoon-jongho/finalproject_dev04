package com.example.application.ui.login;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.application.R;
import com.example.application.databinding.FragmentLoginBinding;
import com.example.application.model.AuthVO;
import com.example.application.model.MemberVO;
import com.example.application.ui.alarm.AlarmListFragment;
import com.example.application.ui.base.ViewModelFactory;
import com.example.application.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private LoginViewModel loginViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Data Binding 연결
        loginViewModel = new ViewModelProvider(this, new ViewModelFactory()).get(LoginViewModel.class);
        binding.setViewModel(loginViewModel);

        final Button loginButton = view.findViewById(R.id.login);

        loginButton.setOnClickListener(v -> {
            // ID, PW 비어있는지 확인
            if (!loginViewModel.validateLoginInfo()) {
                Toast.makeText(requireActivity().getApplicationContext(), "아이디, 비밀번호는 반드시 입력해야합니다.", Toast.LENGTH_SHORT).show();
                return;
            }

            loginViewModel.login(new Callback<MemberVO>() {
                private Object AlarmListFragment;

                /**
                 * 로그인 성공
                 */
                @Override
                public void onResponse(Call<MemberVO> call, Response<MemberVO> response) {
                    if (response.isSuccessful()) {
                        MemberVO memberVO = response.body();
                        // 로그인된 회원 정보 저장
                        AuthVO.getInstance().setMemberVO(memberVO);

             //           if (requireActivity() != null) {
               //            Toast.makeText(requireActivity().getApplicationContext().getApplicationContext(), String.format("%s 회원님, 환영합니다.", memberVO.getName()), Toast.LENGTH_SHORT).show();
              //            Intent intent = new Intent();
             //              (LoginFragment,AlarmListFragment)
             //               (intent.setClass(LoginFragment.this, AlarmListFragment.class);
             //              startActivity(intent);}



                        // 팝업 메세지
                        if (requireActivity() != null) {
                            Toast.makeText(requireActivity().getApplicationContext().getApplicationContext(), String.format("%s 회원님, 환영합니다.", memberVO.getName()), Toast.LENGTH_SHORT).show();
                        }

                    } else if (requireActivity() != null) {
                        Toast.makeText(requireActivity().getApplicationContext(), "회원가입을 해주세요.", Toast.LENGTH_SHORT).show();
                    }
                }


                /**
                 * 로그인 실패
                 */
                @Override
                public void onFailure(Call<MemberVO> call, Throwable t) {
                    if (requireActivity() != null) {
                        Toast.makeText(requireActivity().getApplicationContext(), "로그인 실패. 인터넷 연결 상태를 확인해주세요.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });


    }
}
