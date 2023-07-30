import axios from "axios";

export const fetchTypes = async () => {
    const {data} = await axios.get('api/type/')
    return data
}

export const fetchBrands = async () => {
    const {data} = await axios.get('api/brand/')
    return data
}

export const fetchOrders = async () => {
    const {data} = (await axios({
        method: 'get',
        url: '/api/order',
        params: {
            user_id: localStorage.getItem('user_id')
        }
    }))
    return data
}

export const fetchProducts = async () => {
    const {data} = (await axios.get('api/product/'))
    return data
}

export const fetchProductsFromWishlist = async () => {
    const {data} = (await axios.get('api/wishlist/' + localStorage.getItem('wishlist_id')))
    return data
}

export const fetchProductsFromCart = async () => {
    const {data} = (await axios.get('api/cart/' + localStorage.getItem('cart_id')))
    return data
}

export const fetchProduct = async (id) => {
    const {data} = (await axios.get('../api/product/' + id))
    return data
}

export const findProducts = async (name) => {
    const {data} = (await axios.get("../api/product/search/" + name))
    return data
}

export const addToWishlist = async (product_id) => {
    await axios({
        method: 'post',
        url: '/api/wishlist',
        params: {
            wishlist_id: localStorage.getItem('wishlist_id'),
            product_id: product_id
        }
    });
}

export const addToCart = async (product_id) => {
    await axios({
        method: 'post',
        url: '/api/cart',
        params: {
            cart_id: localStorage.getItem('cart_id'),
            product_id: product_id
        }
    });
}

export const addUserDetails = async (fullName, country, city, street, phone) => {
    await axios({
        method: 'put',
        url: '/api/user/' + localStorage.getItem('user_id'),
        data: {
            full_name: fullName,
            country: country,
            city: city,
            street: street,
            phone: phone
        }
    });
}

export const addOrder = async (user, products, price) => {
    await axios({
        method: 'post',
        url: '/api/order',
        data: {
            user: user,
            products: products,
            price: price
        }
    });
}

export const addType = async (name) => {
    await axios({
        method: 'post',
        url: '/api/type',
        data: {
            name: name
        }
    });
}

export const addBrand = async (name) => {
    await axios({
        method: 'post',
        url: '/api/brand',
        data: {
            name: name
        }
    });
}

export const addProduct = async (name, title, description, price, brand, type) => {
    await axios({
        method: 'post',
        url: '/api/product',
        data: {
            name: name,
            title: title,
            description: description,
            price: price,
            brand: brand,
            type: type
        }
    });
}

export const deleteFromWishlist = async (product_id) => {
    await axios({
        method: 'delete',
        url: '/api/wishlist',
        params: {
            wishlist_id: localStorage.getItem('wishlist_id'),
            product_id: product_id
        }
    });
}

export const deleteFromCart = async (product_id) => {
    await axios({
        method: 'delete',
        url: '/api/cart',
        params: {
            cart_id: localStorage.getItem('cart_id'),
            product_id: product_id
        }
    });
}

export const deleteOrder = async (order_id) => {
    await axios({
        method: 'delete',
        url: '/api/order/' + order_id
    });
}

export const clearCart = async () => {
    await axios({
        method: 'delete',
        url: '/api/cart/' + localStorage.getItem('cart_id')
    });
}