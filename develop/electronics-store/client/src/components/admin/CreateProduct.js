import React, {useEffect, useState} from 'react';
import {addProduct, fetchBrands, fetchTypes} from "../../api/ProductAPI";
import Option from "./Option";
import MyButton from "../UI/button/MyButton";
import {Card} from "reactstrap";

const CreateProduct = () => {
    const [name, setName] = useState('')
    const [title, setTitle] = useState('')
    const [description, setDescription] = useState('')
    const [price, setPrice] = useState('')
    const [types, setTypes] = useState('')
    const [brands, setBrands] = useState('')
    const [selectedBrand, setSelectedBrand] = useState('Lenovo')
    const [selectedType, setSelectedType] = useState('laptop')

    useEffect(() => {
        fetchTypes().then(data => setTypes(data))
        fetchBrands().then(data => setBrands(data))
    }, [])

    const createProduct = () => {
        addProduct(name, title, description, price, {"name": selectedBrand}, {"name": selectedType})
            .then(data => console.log(data))
            .catch(err => console.log(err));
    }

    let brandOptions = [];
    for (let i = 0; i < brands.length; i++) {
        brandOptions[i] = <Option key={brands[i].id} value={brands[i].name}/>
    }

    let typeOptions = [];
    for (let i = 0; i < types.length; i++) {
        typeOptions[i] = <Option key={types[i].id} value={types[i].name}/>
    }


    return (
        <div className="login_form_username">
            <Card style={{width: 500, cursor: 'pointer', marginBottom: "50px"}} border={"light"}>
                <div className="card_header_style">Product</div>
                Name:
                <input type="text" placeholder="name" name="name" value={name} onChange={e => setName(e.target.value)}/>
                Title:
                <input type="text" placeholder="title" name="title" value={title} onChange={e => setTitle(e.target.value)}/>
                Description:
                <input type="text" placeholder="description" name="description" value={description} onChange={e => setDescription(e.target.value)}/>
                Price:
                <input type="text" placeholder="price" name="price" value={price} onChange={e => setPrice(e.target.value)}/>
                Brand: <select value={selectedBrand} onChange={e => setSelectedBrand(e.target.value)}>
                    {Object.values(brandOptions)}
                </select>
                Type: <select value={selectedType} onChange={e => setSelectedType(e.target.value)}>
                    {Object.values(typeOptions)}
                </select>
                <MyButton onClick={createProduct}>Add product</MyButton>
            </Card>
        </div>
    );
};

export default CreateProduct;