import React, {useEffect, useState} from 'react';
import Header from "../components/Header";
import {addOrder, clearCart, fetchProductsFromCart} from "../api/ProductAPI";
import CartItem from "../components/CartItem";
import MyButton from "../components/UI/button/MyButton";

const Cart = () => {
    const [products, setProducts] = useState({info: []});
    const [totalPrice, setTotalPrice] = useState(0);
    const [user, setUser] = useState({});
    useEffect(() => {
        fetchProductsFromCart().then(data => {
            setProducts(data.products);
            setTotalPrice(data.totalPrice);
            setUser(data.user)
        })
    }, []);

    let cartItems = [];
    for (let i = 0; i < products.length; i++) {
        cartItems[i] = <CartItem key={products[i].id} product={products[i]}/>
    }

    const order = () => {
        addOrder(user, products, totalPrice)
            .then(r => console.log(r))
            .catch(err => console.log(err));

        clearCart()
            .then(r => console.log(r))
            .catch(err => console.log(err));
    }

    return (
        <div align="center">
            <Header/>
            <div className="header_style">My Cart</div>
            <div>{Object.values(cartItems)}</div>
            <div className="brand_name">
                <span className="product_item">Total: </span>
                <span className="price">{totalPrice + "$"}</span>
            </div>
            <MyButton style={{marginTop: "25px", marginBottom: "50px", fontSize: "25px"}}
                      onClick={order}>Order</MyButton>
        </div>
    );
};

export default Cart;