import React, {useEffect, useState} from 'react';
import ProductItem from "./ProductItem";
import {Card} from "reactstrap";
import MyButton from "./UI/button/MyButton";
import {deleteOrder} from "../api/ProductAPI";

const Order = ({order}) => {
    const [products, setProducts] = useState({info: []});
    useEffect(() => {
        setProducts(order.products);
    }, []);

    let productItems = [];
    for (let i = 0; i < products.length; i++) {
        productItems[i] = <ProductItem key={products[i].id} product={products[i]}/>
    }

    const click = () => {
        deleteOrder(order.id)
            .then(r => {
                console.log("Successfully deleted from wishlist", r);
            }).catch(err => console.log(err));
    };

    return (
        <div>
            <Card style={{width: 1000, cursor: 'pointer', marginBottom: "50px"}} border={"light"}>
                <div className="header_style">Order</div>
                <div>{Object.values(productItems)}</div>
                <MyButton onClick={click}>Delete order</MyButton>
            </Card>
        </div>
    );
};

export default Order;