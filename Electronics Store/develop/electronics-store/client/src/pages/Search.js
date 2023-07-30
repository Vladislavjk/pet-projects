import React, {useState} from 'react';
import Header from "../components/Header";
import {findProducts} from "../api/ProductAPI";
import MyButton from "../components/UI/button/MyButton";
import ProductItem from "../components/ProductItem";

const Search = () => {
    const [searchValue, setSearchValue] = useState('');
    const [products, setProducts] = useState([]);

    const click = () => {
        findProducts(searchValue)
            .then(data => setProducts(data))
            .catch(err => console.log(err));
    }

    let productItems = [];
    for (let i = 0; i < products.length; i++) {
        productItems[i] = <ProductItem key={products[i].id} product={products[i]}/>
    }

    return (
        <div align="center">
            <Header/>
            <div className="search_form">
                <input type="text" placeholder="Search" name="Search" value={searchValue}
                       onChange={e => setSearchValue(e.target.value)}/>
            </div>
            <MyButton onClick={click}>Find</MyButton>
            <div className="header_style">Products found: </div>
            <div>{Object.values(productItems)}</div>
        </div>
    );
};

export default Search;