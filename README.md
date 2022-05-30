# Delivery_api
 Sistema de controle de Delivery de um restaurante
Fora utilizado o Firebird 3.0.9 para sincronizar.

Em caso de problemas devido o JWT impossibilitar o swagger ou impedir algum acesso, é preciso ignorar as classes e interfaces:
data\UserDataDetail.java
security\JWTValidationFilter.java
security\JWTConfiguration.java
security\JWTAuthentitactionFilter.java
service\UserDetailServiceImplemented.java
E adicionar ao lado da anotação @SpringBootApplication (exclude = {SecurityAutoConfiguration.class})

Logo após, é possível criar um usuário e posteriormente ao habilitar as clasees e remover o que fora adicionado. Assim é possível utilizar o usuário criado para gerar um token e operar sobre as ações.

Caminhos das APIs:

Customers

To register a customer
http://localhost:8080/customers/register

To update any customer information
http://localhost:8080/customers/update/{customerID}

To see the customer information
http://localhost:8080/customers/{customerID}

To see all registered customers
http://localhost:8080/customers/list

To delete a customer
http://localhost:8080/customers/delete

---
Products

To register a product
http://localhost:8080/products/register

To update a product information
http://localhost:8080/products/update/{productID}

To list all products
http://localhost:8080/products/list

To delete a product
http://localhost:8080/products/delete/{productID}

---
Product Order

To register a product
http://localhost:8080/orders/register

To update any order information
http://localhost:8080/orders/update/{orderID}

To list all orders
http://localhost:8080/orders/list

To see all orders made by an specific customer
http://localhost:8080/orders/{customerID}

To delete an order
http://localhost:8080/orders/delete/{orderID}

---
Delivery

To register a delivery
http://localhost:8080/delivery/register

To update a delivery information
http://localhost:8080/delivery/update/{deliveryID}

To list all deliveries
http://localhost:8080/delivery/list

To delete a delivery
http://localhost:8080/delivery/delete/{deliveryID}

---
Users

To register a user
http://localhost:8080/user

To login
http://localhost:8080/user/enter

