import React from 'react';
import {Card} from "reactstrap";
import MyButton from "./UI/button/MyButton";
import {useNavigate} from "react-router-dom";
import {deleteFromCart} from "../api/ProductAPI";

const CartItem = ({product}) => {
    const navigate = useNavigate();

    const deleteProduct = () => {
        deleteFromCart(product.id)
            .then(r => {
                console.log("Successfully deleted from cart", r);
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
                <MyButton onClick={deleteProduct}>Delete from cart</MyButton>
            </Card>
        </div>
    );
};

export default CartItem;