import React, {useState} from 'react';
import MyButton from "./UI/button/MyButton";
import HeaderLogin from "./HeaderLogin";
import axios from "axios";
import {useNavigate} from "react-router-dom";

const RegisterForm = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const register = e => {
        e.preventDefault();
        axios({
            method: 'post',
            url: '/api/auth/signup',
            data: {
                username: username,
                password: password,
                role: ["user"]
            }
        }).then(r => {
            console.log('Successful sign up', r);
            navigate("/");
        }).catch(err => console.log(err));
    }

    return (
        <div>
            <HeaderLogin/>
            <form className="login_form_username">
                <label>
                    <input type="text" placeholder="Username" name="username" value={username} onChange={e => setUsername(e.target.value)}/>
                </label>
            </form>

            <form className="login_form_password">
                <label>
                    <input type="text" placeholder="Password" name="password" type="password" value={password} onChange={e => setPassword(e.target.value)}/>
                </label>
            </form>
            <MyButton onClick={register}>Sign up</MyButton>
        </div>
    );
};

export default RegisterForm;