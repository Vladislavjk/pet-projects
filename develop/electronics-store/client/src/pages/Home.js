import React, {useEffect, useState} from 'react';
import Header from "../components/Header";
import {fetchProducts} from "../api/ProductAPI";
import ProductItem from "../components/ProductItem";

const Home = () => {
    const [products, setProducts] = useState({info: []});
    useEffect(() => {
        fetchProducts().then(data => setProducts(data))
    }, []);

    let productItems = [];
    for (let i = 0; i < products.length; i++) {
        productItems[i] = <ProductItem key={products[i].id} product={products[i]}/>
    }

    return (
        <div align="center">
            <Header/>
            <form className="header_style">
                All available products
            </form>
            <div>{Object.values(productItems)}</div>
        </div>
    );
};

export default Home;