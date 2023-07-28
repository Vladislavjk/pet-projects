import React from 'react';
import Header from "../components/Header";
import CreateType from "../components/admin/CreateType";
import CreateBrand from "../components/admin/CreateBrand";
import CreateProduct from "../components/admin/CreateProduct";

const Admin = () => {
    return (
        <div align="center">
            <Header/>
            <div className="header_style">Admin page</div>
            <CreateType/>
            <CreateBrand/>
            <CreateProduct/>
        </div>
    );
};

export default Admin;