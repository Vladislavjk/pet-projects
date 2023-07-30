import React, {useEffect, useState} from 'react';
import Header from "../components/Header";
import {fetchProductsFromWishlist} from "../api/ProductAPI";
import WishlistItem from "../components/WishlistItem";

const Wishlist = () => {
    const [products, setProducts] = useState({info: []});
    useEffect(() => {
        fetchProductsFromWishlist().then(data => setProducts(data.products));
    }, []);

    let wishlistItems = [];
    for (let i = 0; i < products.length; i++) {
        wishlistItems[i] = <WishlistItem key={products[i].id} product={products[i]}/>
    }

    return (
        <div align="center">
            <Header/>
            <div className="header_style">My Wishlist</div>
            <div>{Object.values(wishlistItems)}</div>
        </div>
    );
};

export default Wishlist;