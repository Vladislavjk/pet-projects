import React, {useState} from 'react';
import MyButton from "../UI/button/MyButton";
import {addType} from "../../api/ProductAPI";

const CreateType = () => {
    const [type, setType] = useState('')

    const click = () => {
        addType(type)
            .then(r => console.log(r.data))
            .catch(err => console.log(err));
    }

    return (
        <div className="login_form_username">
            <input type="text" placeholder="Type" name="Type" value={type} onChange={e => setType(e.target.value)}/>
            <MyButton onClick={click}>Add Type</MyButton>
        </div>
    );
};

export default CreateType;