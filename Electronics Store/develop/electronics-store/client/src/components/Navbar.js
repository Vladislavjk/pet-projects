import React from 'react';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Login from "../pages/Login";
import Register from "../pages/Register";
import Home from "../pages/Home";
import Search from "../pages/Search";
import Wishlist from "../pages/Wishlist";
import User from "../pages/User";
import Cart from "../pages/Cart";
import ProductDescription from "../pages/ProductDescription";
import Orders from "../pages/Orders";
import Admin from "../pages/Admin";

const Navbar = () => {
    return (
        <div>
            <BrowserRouter>
                <Routes>
                    <Route path="" element={<Login/>}/>
                    <Route path="register" element={<Register/>}/>
                    <Route path="home" element={<Home/>}/>
                    <Route path="/logout" element={<Login/>}/>
                    <Route path="/home" element={<Home/>}/>
                    <Route path="/search" element={<Search/>}/>
                    <Route path="/wishlist" element={<Wishlist/>}/>
                    <Route path="/user" element={<User/>}/>
                    <Route path="/cart" element={<Cart/>}/>
                    <Route path="/orders" element={<Orders/>}/>
                    <Route path="/admin" element={<Admin/>}/>
                    <Route path="/product/:id" element={<ProductDescription/>}/>
                </Routes>
            </BrowserRouter>
        </div>
    );
};

export default Navbar;