import React, {useState} from 'react';
import Header from "../components/Header";
import MyButton from "../components/UI/button/MyButton";
import {addUserDetails} from "../api/ProductAPI";

const User = () => {
    const [fullName, setFullName] = useState('');
    const [country, setCountry] = useState('');
    const [city, setCity] = useState('');
    const [street, setStreet] = useState('');
    const [phone, setPhone] = useState('');

    const click = () => {
        addUserDetails(fullName, country, city, street, phone)
            .then(r => console.log(r))
            .catch(err => console.log(err));
    }

    return (
        <div align="center">
            <Header/>
            <div className="header_style">User Settings</div>

            <div className="login_form_username">
                <input type="text" placeholder="Full name" name="Full name" value={fullName} onChange={e => setFullName(e.target.value)}/>
            </div>

            <div className="login_form_username">
                <input type="text" placeholder="Country" name="Country" value={country} onChange={e => setCountry(e.target.value)}/>
            </div>

            <div className="login_form_username">
                <input type="text" placeholder="City" name="City" value={city} onChange={e => setCity(e.target.value)}/>
            </div>

            <div className="login_form_username">
                <input type="text" placeholder="Street" name="Street" value={street} onChange={e => setStreet(e.target.value)}/>
            </div>

            <div className="login_form_password">
                <input type="text" placeholder="Phone" name="Phone" value={phone} onChange={e => setPhone(e.target.value)}/>
            </div>

            <MyButton onClick={click}>Apply</MyButton>
        </div>
    );
};

export default User;