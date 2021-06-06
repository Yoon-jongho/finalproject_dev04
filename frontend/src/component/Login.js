import React, { Component } from "react";
import '../css/login.css';

function Login() {
    return(
        
            <div id="content">
                <div id="header">
                <a href="/" target="_self" title="선빵 회원가입 페이지 보러가기">
                    <span id="sunbbang">선 빵</span>
                </a>
                </div>
                <span className="box id">
                    <input type="text" id="id" className= "var" maxLength="20" placeholder="아이디">                 
                    </input>
                </span>
                <span className="box password">
                    <input type="password" id="password" className="var" maxLength="20" placeholder="비밀번호">                     
                    </input>
                </span>
                <div id="btn_area">
                    <button type="submit" id="login_btn">
                        <span>로 그 인</span>
                    </button>
                </div>
            </div>


       


    );
}

export default Login;