import React from 'react';
import {Link} from "react-router-dom";

const Header = () => {
    return (
        <div>
            <nav>
                <h1 className="header_style">The Crawler</h1>
                <Link to="/logout">
                    <img className="img_header" src="https://cdn-icons-png.flaticon.com/512/32/32205.png" width={50} alt="logout"/>
                </Link>
                <Link to="/home">
                    <img className="img_header" src="https://cdn-icons-png.flaticon.com/512/25/25694.png" width={50} alt="home"/>
                </Link>
                <Link to="/search">
                    <img className="img_header" src="https://cdn-icons-png.flaticon.com/512/54/54481.png" width={50} alt="search"/>
                </Link>
                <Link to="/wishlist">
                    <img className="img_header" src="https://cdn-icons-png.flaticon.com/512/1828/1828970.png" width={50} alt="wishlist"/>
                </Link>
                <Link to="/user">
                    <img className="img_header" src="https://cdn-icons-png.flaticon.com/512/747/747376.png" width={50} alt="user"/>
                </Link>
                <Link to="/orders">
                    <img className="img_header" src="https://cdn-icons-png.flaticon.com/512/1008/1008010.png" width={50} alt="orders"/>
                </Link>
                <Link to="/cart">
                    <img className="img_header" src="https://cdn-icons-png.flaticon.com/512/2838/2838895.png" width={50} alt="cart"/>
                </Link>
            </nav>
        </div>
    );
};

export default Header;