# The Crawler Technical Design

# Classes

## Entity

<details>
<summary>Entity</summary>
<pre>

### User

* This class contains the information about user.

### Role

* This class contains the information about roles which can be given to user.

### User Role

* This class contains the information of mapping between users and their roles.

### Cart

* This class contains the information about cart.

### Wishlist

* This class contains the information about wishlist.

### Brand

* This class contains the information about brand.

### Type

* This class contains the information about type.

### Product

* This class contains the information about product.

### Order

* This class contains the information about order.


</pre>
</details>


## Repository

<details>
<summary>Repository</summary>
<pre>


### UserRepository extends JpaRepository

* This interface helps to get user from the database.

<details>
<summary>Methods</summary>
<pre>

##### method findByUsername()

* `input` : username
* This method allows us to find user from database by his username.

##### method existsByUsername()

* `input` : username
* This method returns True if user with such username exists in database, otherwise false.

</pre>
</details>

### RoleRepository extends JpaRepository

* This interface helps to get role from the database.

<details>
<summary>Methods</summary>
<pre>

##### method findByName()

* `input` : name
* This method allows us to find role from database by name.

</pre>
</details>

## BrandRepository extends JpaRepository

* This interface helps to get brand from the database.

<details>
<summary>Methods</summary>
<pre>

##### method findByName()

* `input` : name
* This method allows us to find brand from database by name.

</pre>
</details>

## TypeRepository extends JpaRepository

* This interface helps to get type from the database.

<details>
<summary>Methods</summary>
<pre>

##### method findByName()

* `input` : name
* This method allows us to find type from database by name.

</pre>
</details>

## CartRepository extends JpaRepository

* This interface helps to get cart from the database.

<details>
<summary>Methods</summary>
<pre>

##### method findByUser()

* `input` : name
* This method allows us to find cart from database by user.

</pre>
</details>

## WishlistRepository extends JpaRepository

* This interface helps to get wishlist from the database.

<details>
<summary>Methods</summary>
<pre>

##### method findByUser()

* `input` : name
* This method allows us to find wishlist from database by user.

</pre>
</details>

## OrderRepository extends JpaRepository

* This interface helps to get order from the database.

<details>
<summary>Methods</summary>
<pre>

##### method findByUser()

* `input` : name
* This method allows us to find order from database by user.

</pre>
</details>

## ProductRepository extends JpaRepository

* This interface helps to get product from the database.

<details>
<summary>Methods</summary>
<pre>

##### method findByName()

* `input` : name
* This method allows us to find product from database by name.

##### method findByNameStartsWith()

* `input` : str
* This method allows us to find product that starts with str from database.

</pre>
</details>

</pre>
</details>

## Service

<details>
<summary>Service</summary>
<pre>

### UserService

* This interface contains the methods which helps to create, update and get the
  information about user.

<details>
<summary>Methods</summary>
<pre>

##### method getById()

* `input` : id
* This method allows us to get user by id.

##### method getByUsername()

* `input` : username
* This method allows us to get user by username.

##### method getUsers()

* `input` : none
* This method allows us to get all users.

##### method addUserDetails()

* `input` : newUser, id
* This method allows us to add user details.

</pre>
</details>

### BrandService

* This interface contains the methods which helps to create, update, delete and get the
  information about brand.

<details>
<summary>Methods</summary>
<pre>

##### method getById()

* `input` : id
* This method allows us to get brand by id.

##### method getByName()

* `input` : name
* This method allows us to get brand by name.

##### method getBrands()

* `input` : none
* This method allows us to get all brands.

##### method addBrand()

* `input` : brand
* This method allows us to add brand.

##### method deleteById()

* `input` : id
* This method allows us to delete brand by id.

##### method updateBrand()

* `input` : newBrand, id
* This method allows us to update brand by id.

</pre>
</details>

### TypeService

* This interface contains the methods which helps to create, update, delete and get the
  information about type.

<details>
<summary>Methods</summary>
<pre>

##### method getById()

* `input` : id
* This method allows us to get type by id.

##### method getByName()

* `input` : name
* This method allows us to get type by name.

##### method getTypes()

* `input` : none
* This method allows us to get all types.

##### method addType()

* `input` : type
* This method allows us to add type.

##### method deleteById()

* `input` : id
* This method allows us to delete type by id.

##### method updateType()

* `input` : newType, id
* This method allows us to update type by id.

</pre>
</details>

### OrderService

* This interface contains the methods which helps to create, update, delete and get the
  information about order.

<details>
<summary>Methods</summary>
<pre>

##### method getById()

* `input` : id
* This method allows us to get order by id.

##### method getUserOrders()

* `input` : user_id
* This method allows us to get all orders by user_id.

##### method getOrders()

* `input` : none
* This method allows us to get all orders.

##### method addOrder()

* `input` : order
* This method allows us to add order.

##### method deleteById()

* `input` : id
* This method allows us to delete order by id.

##### method updateOrder()

* `input` : newOrder, id
* This method allows us to update order by id.

</pre>
</details>

### ProductService

* This interface contains the methods which helps to create, update, delete and get the
  information about product.

<details>
<summary>Methods</summary>
<pre>

##### method getById()

* `input` : id
* This method allows us to get product by id.

##### method searchProducts()

* `input` : str
* This method allows us to find all products, that starts with str.

##### method getProducts()

* `input` : none
* This method allows us to get all products.

##### method addProduct()

* `input` : product
* This method allows us to add product.

##### method deleteById()

* `input` : id
* This method allows us to delete product by id.

##### method updateProduct()

* `input` : newProduct, id
* This method allows us to update product by id.

</pre>
</details>

### CartService

* This interface contains the methods which helps to create, update, delete and get the
  information about cart.

<details>
<summary>Methods</summary>
<pre>

##### method getById()

* `input` : id
* This method allows us to get cart by id.

##### method getByUserId()

* `input` : user_id
* This method allows us to get cart by user_id.

##### method createCartForUser()

* `input` : user_id
* This method allows us to create cart for user with id = user_id.

##### method addProductToCart()

* `input` : cart_id, product_id
* This method allows us to add product to cart.

##### method deleteProductByIdFromCart()

* `input` : cart_id, product_id
* This method allows us to delete product from cart by product_id.

##### method clearCart()

* `input` : cart_id
* This method allows us to clear cart by id.

</pre>
</details>

### WishlistService

* This interface contains the methods which helps to create, update, delete and get the
  information about wishlist.

<details>
<summary>Methods</summary>
<pre>

##### method getById()

* `input` : id
* This method allows us to get wishlist by id.

##### method getByUserId()

* `input` : user_id
* This method allows us to get wishlist by user_id.

##### method createWishlistForUser()

* `input` : user_id
* This method allows us to create wishlist for user with id = user_id.

##### method addProductToWishlist()

* `input` : wishlist_id, product_id
* This method allows us to add product to wishlist.

##### method deleteProductByIdFromWishlist()

* `input` : wishlist_id, product_id
* This method allows us to delete product from wishlist by product_id.

</pre>
</details>

</pre>
</details>

## Controller

<details>
<summary>Controller</summary>
<pre>

### AuthController

* This class contains methods which are responsible for signing in,
  signing up and logout.

<details>
<summary>Methods</summary>
<pre>

##### method authenticateUser()

* `input` : LoginRequest
* `output` : Status 200 - Ok
* `url pattern` : {{url}}/signin
* `method` : POST
* `description` : Method to sign in.

##### method registerUser()

* `input` : SignUpRequest
* `output` : Status 200 - Ok
* `url pattern` : {{url}}/signup
* `method` : POST
* `description` : Method to sign up.

##### method logout()

* `input` : none
* `output` : Status 200 - Ok
* `url pattern` : {{url}}/logout
* `method` : POST
* `description` : Method to logout.

</pre>
</details>

### BrandController

* This class contains methods which are responsible for create, get,
  update and delete brand.

<details>
<summary>Methods</summary>
<pre>

##### method addBrand()

* `input` : brand
* `output` : Status 200 - Ok
* `url pattern` : {{url}}/api/brand
* `method` : POST
* `description` : Method that creates brand

##### method updateBrand()

* `input` : newBrand, id
* `output` : Status 200 - Ok
* `url pattern` : {{url}}/api/brand/{{id}}
* `method` : PUT
* `description` : Method that updates brand by id

##### method getBrand()

* `input` : id
* `output` : Status 200 - Ok
* `url pattern` : {{url}}/api/brand/{{id}}
* `method` : GET
* `description` : Method that gets brand by id

##### method getBrands()

* `input` : none
* `output` : Status 200 - Ok
* `url pattern` : {{url}}/api/brand/
* `method` : GET
* `description` : Method that gets all brands

##### method deleteBrand()

* `input` : id
* `output` : Status 200 - Ok
* `url pattern` : {{url}}/api/brand/{{id}}
* `method` : DELETE
* `description` : Method that deletes brand by id

</pre>
</details>

### TypeController

* This class contains methods which are responsible for create, get,
  update and delete type.

<details>
<summary>Methods</summary>
<pre>

##### method addType()

* `input` : type
* `output` : Status 200 - Ok
* `url pattern` : {{url}}/api/type
* `method` : POST
* `description` : Method that creates type

##### method updateType()

* `input` : newType, id
* `output` : Status 200 - Ok
* `url pattern` : {{url}}/api/type/{{id}}
* `method` : PUT
* `description` : Method that updates type by id

##### method getType()

* `input` : id
* `output` : Status 200 - Ok
* `url pattern` : {{url}}/api/type/{{id}}
* `method` : GET
* `description` : Method that gets type by id

##### method getTypes()

* `input` : none
* `output` : Status 200 - Ok
* `url pattern` : {{url}}/api/type/
* `method` : GET
* `description` : Method that gets all types

##### method deleteType()

* `input` : id
* `output` : Status 200 - Ok
* `url pattern` : {{url}}/api/type/{{id}}
* `method` : DELETE
* `description` : Method that deletes type by id

</pre>
</details>

### UserController

* This class contains methods which are responsible for create, get,
  update and delete user.

<details>
<summary>Methods</summary>
<pre>

##### method getUser()

* `input` : id
* `output` : Status 200 - Ok
* `url pattern` : {{url}}/api/user/{{id}}
* `method` : GET
* `description` : Method that gets user by his id.

##### method getUsers()

* `input` : none
* `output` : Status 200 - Ok
* `url pattern` : {{url}}/api/user/
* `method` : GET
* `description` : Method that gets all users.

##### method addUserDetails()

* `input` : newUser, id
* `output` : Status 200 - Ok
* `url pattern` : {{url}}/api/user/{{id}}
* `method` : PUT
* `description` : Method that add user details user by id.

</pre>
</details>

### ProductController

* This class contains methods which are responsible for create, get,
  update and delete product.

<details>
<summary>Methods</summary>
<pre>

##### method addProduct()

* `input` : type
* `output` : Status 200 - Ok
* `url pattern` : {{url}}/api/product
* `method` : POST
* `description` : Method that creates product

##### method updateProduct()

* `input` : newProduct, id
* `output` : Status 200 - Ok
* `url pattern` : {{url}}/api/product/{{id}}
* `method` : PUT
* `description` : Method that updates product by id

##### method getProduct()

* `input` : id
* `output` : Status 200 - Ok
* `url pattern` : {{url}}/api/product/{{id}}
* `method` : GET
* `description` : Method that gets product by id

##### method getProducts()

* `input` : none
* `output` : Status 200 - Ok
* `url pattern` : {{url}}/api/product/
* `method` : GET
* `description` : Method that gets all products

##### method searchProducts()

* `input` : str
* `output` : Status 200 - Ok
* `url pattern` : {{url}}/api/product/search/{{str}}
* `method` : GET
* `description` : Method that gets all products that starts with str.

##### method deleteProduct()

* `input` : id
* `output` : Status 200 - Ok
* `url pattern` : {{url}}/api/product/{{id}}
* `method` : DELETE
* `description` : Method that deletes product by id

</pre>
</details>

### OrderController

* This class contains methods which are responsible for create, get,
  update and delete order.

<details>
<summary>Methods</summary>
<pre>

##### method addOrder()

* `input` : type
* `output` : Status 200 - Ok
* `url pattern` : {{url}}/api/order
* `method` : POST
* `description` : Method that creates order

##### method updateOrder()

* `input` : newOrder, id
* `output` : Status 200 - Ok
* `url pattern` : {{url}}/api/order/{{id}}
* `method` : PUT
* `description` : Method that updates order by id

##### method getOrder()

* `input` : id
* `output` : Status 200 - Ok
* `url pattern` : {{url}}/api/order/{{id}}
* `method` : GET
* `description` : Method that gets order by id

##### method getUserOrders()

* `input` : user_id
* `output` : Status 200 - Ok
* `url pattern` : {{url}}/api/order?user_id=user_id
* `method` : GET
* `description` : Method that gets all orders for user with id = user_id


##### method getOrders()

* `input` : none
* `output` : Status 200 - Ok
* `url pattern` : {{url}}/api/order/
* `method` : GET
* `description` : Method that gets all orders

##### method deleteOrder()

* `input` : id
* `output` : Status 200 - Ok
* `url pattern` : {{url}}/api/order/{{id}}
* `method` : DELETE
* `description` : Method that deletes order by id

</pre>
</details>

### CartController

* This class contains methods which are responsible for create, get,
  update and delete cart.

<details>
<summary>Methods</summary>
<pre>

##### method addProductToCart()

* `input` : cart_id, product_id
* `output` : Status 200 - Ok
* `url pattern` : {{url}}/api/cart?cart_id=cart_id&product_id=product_id
* `method` : POST
* `description` : Method that adds product to cart

##### method getCart()

* `input` : id
* `output` : Status 200 - Ok
* `url pattern` : {{url}}/api/cart/{{id}}
* `method` : GET
* `description` : Method that gets cart by id

##### method deleteProductFromCart()

* `input` : cart_id, product_id
* `output` : Status 200 - Ok
* `url pattern` : {{url}}/api/cart?cart_id=cart_id&product_id=product_id
* `method` : DELETE
* `description` : Method that deletes product from cart

##### method clearCart()

* `input` : cart_id
* `output` : Status 200 - Ok
* `url pattern` : {{url}}/api/cart/{{cart_id}}
* `method` : DELETE
* `description` : Method that clears cart

</pre>
</details>

### WishlistController

* This class contains methods which are responsible for create, get,
  update and delete wishlist.

<details>
<summary>Methods</summary>
<pre>

##### method addProductToWishlist()

* `input` : wishlist_id, product_id
* `output` : Status 200 - Ok
* `url pattern` : {{url}}/api/wishlist?wishlist_id=wishlist_id&product_id=product_id
* `method` : POST
* `description` : Method that adds product to wishlist

##### method getWishlist()

* `input` : id
* `output` : Status 200 - Ok
* `url pattern` : {{url}}/api/wishlist/{{id}}
* `method` : GET
* `description` : Method that gets wishlist by id

##### method deleteProductFromWishlist()

* `input` : wishlist_id, product_id
* `output` : Status 200 - Ok
* `url pattern` : {{url}}/api/wishlist?wishlist_id=wishlist_id&product_id=product_id
* `method` : DELETE
* `description` : Method that deletes product from wishlist

</pre>
</details>

</pre>
</details>
