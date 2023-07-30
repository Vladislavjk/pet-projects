import React, {useState} from 'react';
import {addBrand} from "../../api/ProductAPI";
import MyButton from "../UI/button/MyButton";

const CreateBrand = () => {
    const [brand, setBrand] = useState('')

    const click = () => {
        addBrand(brand)
            .then(r => console.log(r.data))
            .catch(err => console.log(err));
    }

    return (
        <div className="login_form_username">
            <input type="text" placeholder="Brand" name="Brand" value={brand} onChange={e => setBrand(e.target.value)}/>
            <MyButton onClick={click}>Add Brand</MyButton>
        </div>
    );
};

export default CreateBrand;