# E‑Commerce Spring Application

## Description
This is an online goods shop website where users can log in and purchase various kinds of goods. The website allows users to browse products by sorting them based on their categories. Users can add items to their online cart and make payments via a Paytm gateway. The website also supports Google OAuth2 authentication for user convenience. Additionally, an admin panel is available for administrators to manage products and categories.

## Key Features
- User authentication via email, username, or Google Accounts using Google OAuth2.
- Role-based Access Control (RBAC) for secure login system.
- Browse products by filtering through categories.
- Personalized shopping cart for each user to manage items.
- Admin panel for adding new categories and products (with images).
- Integrated Paytm payment gateway for seamless transactions.

## Models and Entities
The project utilizes the following models/entities:
1. Category
2. Product
3. User
4. Role
5. Custom User Details

## Repository and Service Layers
The repository interfaces extend `JpaRepository` to provide basic CRUD functionality for models. The service layer encapsulates business logic and interacts with the repository to perform operations on the database.

## Controllers
The project contains the following controllers:
1. Admin Controller - For managing product categories and items.
2. Cart Controller - Handling user shopping cart related operations.
3. Home Controller - Home page and product browsing.
4. Login Controller - User authentication and registration.

## DTO (Data Transfer Object)
DTOs facilitate data transfer between different layers of the application. The project uses the `ProductDTO` to transfer required product data.

## Security
- Unauthorised users can browse the shop but need to create an account and log in to make purchases.
- Users can sign up with their email or use Google OAuth2 for sign-up.
- Passwords are securely stored using `BCryptPasswordEncoder`.
- Cookies and session data are cleared on logout for security.

## Payment and Checkout
- Users are redirected to a payment page with the PayTM gateway for transactions.
- Images are stored locally, and the database only stores references to the images for efficient loading.

## Licensing
This project is licensed under the MIT License. Please see the LICENSE file for more details.

Feel free to use and contribute to this project! Happy shopping! 🛒🛍️