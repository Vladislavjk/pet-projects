import React, {useEffect, useState} from 'react';
import Header from "../components/Header";
import {useParams} from "react-router-dom";
import MyButton from "../components/UI/button/MyButton";
import {addToCart, addToWishlist, fetchProduct} from "../api/ProductAPI";

const ProductDescription = () => {
    const { id } = useParams();
    const [product, setProduct] = useState('');
    const [brand, setBrand] = useState('');
    const [type, setType] = useState('');

    useEffect(() => {
        fetchProduct(id)
            .then(r => {
                setProduct(r);
                setBrand(r.brand);
                setType(r.type);
                console.log(r);
            }).catch(err => console.log(err));
    }, []);

    const clickCart = () => {
        addToCart(product.id)
            .then(r => {
                console.log("Successfully added to cart", r);
            }).catch(err => console.log(err));
    };

    const clickWishlist = () => {
        addToWishlist(product.id)
            .then(r => {
                console.log("Successfully added to wishlist", r);
            }).catch(err => console.log(err));
    };

    return (
        <div align="center">
            <Header/>
            <div className="header_style">{product.name}</div>
            <div className="brand_name">Brand: {brand.name}</div>
            <div className="brand_name">Type: {type.name}</div>
            <div className="price">Price: {product.price + "$"}</div>
            <div className="header_style">Title</div>
            <div className="padding_description">{product.title}</div>
            <div className="header_style" >Description</div>
            <div className="padding_description">{product.description}</div>
            <MyButton style={{marginBottom: "50px", fontSize: "25px"}} onClick={clickWishlist}>Add to Wishlist</MyButton>
            <MyButton style={{marginBottom: "50px", fontSize: "25px"}} onClick={clickCart}>Add to Cart</MyButton>
        </div>
    );
};

export default ProductDescription;