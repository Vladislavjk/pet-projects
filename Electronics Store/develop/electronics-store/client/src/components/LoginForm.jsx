import React, {useState} from 'react';
import MyButton from "./UI/button/MyButton";
import axios from "axios";
import {useNavigate } from 'react-router-dom';
import SignUpHelp from "./SignUpHelp";
import HeaderLogin from "./HeaderLogin";

const LoginForm = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const login = e => {
        e.preventDefault();
        axios({
            method: 'post',
            url: '/api/auth/signin',
            data: {
                username: username,
                password: password
            }
        }).then(r => {
            console.log('Successful sign in', r);
            localStorage.setItem('user_id', r.data.id)
            localStorage.setItem('cart_id', r.data.cart_id)
            localStorage.setItem('wishlist_id', r.data.wishlist_id)
            if (r.data.roles.includes("ROLE_ADMIN")) {
                navigate("/admin");
            } else {
                navigate("/home");
            }
        }).catch(err => console.log('Wrong username or password, please try again', err));
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

            <MyButton onClick={login}>Sign in</MyButton>
            <SignUpHelp/>
            <MyButton onClick={() => navigate("/register")}>Sign Up</MyButton>
        </div>
    );
};

export default LoginForm;