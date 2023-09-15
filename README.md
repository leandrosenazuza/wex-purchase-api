<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
</head>
<body>
<h1>WEX PURCHASE API</h1> 
<a> Technical Test - Leandro Sena Zuza</a>
<hr>

<h2>#About</h2>
<p>This API it's a technical test to Software Development Engineer position at Wex Company.</p>
<h2>#Objective</h2>
<p>My application, called wex-purchase-api must be able to accept and store (i.e., persist) a purchase transaction with a description, transaction
date, and a purchase amount in United States dollars. When the transaction is stored, it will be assigned a unique identifier.</p>

<h2>#Technologies</h2>
<ol>
<li>Back-end: Java 17</li>
<li>Database: H2</li>
</ol>


<h2>#Author</h2>
<ul><li>Name: Leandro Sena Zuza</li>
<li>Job: software developer full stack</li>
</ul>



<h2>#How to acess the WEX PURCHASE API and API's Database?</h2>
<ul>
<li>If want to acess the Swagger, please, run application and acess: <a>http://localhost:8080/wex-purchase-api/swagger-ui/index.html</a></li>
</ul>


<h2>#How to acess API's Database?</h2>
<ul>
<li>To access the database, please, run application and acess: <a>http://localhost:8080/wex-purchase-api/h2</a> </li>
<li>The credentials to conect to H2 Database are in the Application Properties.</li>
</ul>

<h2>#How to use the request?</h2>
<ul>
<li><b>/save</b> request persit one purchase in the database.</li>
<li><b>/listAllPurchase</b> list all transactions persisted in the database </li>
<li><b>/getOnePurchase/{id}</b> get id's transaction </li>
<li><b>/getPurchaseWithCurrency/{id}</b> get id's transaction <i>converted</i> to respective exchange date, if it exist.</li>
</ul>

<h2>#Scripts?</h2>
<p>There is some scripts if you want to test with some data.</p>
INSERT INTO TAB_TRANSACTION ( DATE_TRANSACTION, PURCHASE_AMOUNT, DESCRIPTION)
VALUES ('2022-12-31', 100321.0, 'New York Cap');

INSERT INTO TAB_TRANSACTION ( DATE_TRANSACTION, PURCHASE_AMOUNT, DESCRIPTION)
VALUES ('2023-09-06', 150.0, 'Milk Happy Cow 1L');

INSERT INTO TAB_TRANSACTION ( DATE_TRANSACTION, PURCHASE_AMOUNT, DESCRIPTION)
VALUES ('2023-09-07', 75.0, 'Lego Set');

INSERT INTO TAB_TRANSACTION ( DATE_TRANSACTION, PURCHASE_AMOUNT, DESCRIPTION)
VALUES ('2023-06-01', 70.456, 'Bread');

INSERT INTO TAB_TRANSACTION ( DATE_TRANSACTION, PURCHASE_AMOUNT, DESCRIPTION)
VALUES ('2023-06-30', 63.456, 'Flip flop');

INSERT INTO TAB_TRANSACTION ( DATE_TRANSACTION, PURCHASE_AMOUNT, DESCRIPTION)
VALUES ('2022-12-31', 63.456, 'Whopper');

</body>
</html>


