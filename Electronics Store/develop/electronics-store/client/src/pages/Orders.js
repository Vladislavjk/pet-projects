import React, {useEffect, useState} from 'react';
import Header from "../components/Header";
import {fetchOrders} from "../api/ProductAPI";
import Order from "../components/Order";

const Orders = () => {
    const [orders, setOrders] = useState({info: []});
    useEffect(() => {
        fetchOrders().then(data => setOrders(data))
    }, []);

    let orderItems = [];
    for (let i = 0; i < orders.length; i++) {
        orderItems[i] = <Order key={orders[i].id} order={orders[i]}/>
    }


    return (
        <div align="center">
            <Header/>
            <div className="header_style">My orders</div>
            <div>{Object.values(orderItems)}</div>
        </div>
    );
};

export default Orders;