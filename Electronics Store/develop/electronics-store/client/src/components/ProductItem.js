import React from 'react';
import {useNavigate} from "react-router-dom";
import {Card} from "reactstrap";
import MyButton from "./UI/button/MyButton";
import {addToCart, addToWishlist} from "../api/ProductAPI";

const ProductItem = ({product}) => {
    const navigate = useNavigate();

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
        <div>
                <Card style={{width: 400, cursor: 'pointer', marginBottom: "50px"}} border={"light"}>
                    <div onClick={() => navigate("/product/" + product.id)}>
                        <div className="product_item">{product.brand.name}</div>
                        <div className="product_item">{product.type.name}</div>
                        <div className="product_item">{product.name}</div>
                        <div className="price">{product.price + "$"}</div>
                    </div>
                    <MyButton onClick={clickWishlist}>Add to Wishlist</MyButton>
                    <MyButton onClick={clickCart}>Add to Cart</MyButton>
                </Card>
        </div>
    );
}

export default ProductItem;