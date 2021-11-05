DROP DATABASE  IF EXISTS superMarket;
CREATE DATABASE IF NOT EXISTS superMarket;
USE superMarket;

DROP TABLE IF EXISTS customer;
CREATE TABLE IF NOT EXISTS customer(
    cusID VARCHAR(6),
    cusTitle VARCHAR(5),
    cusName VARCHAR(30) NOT NULL DEFAULT 'Unknown',
    cusAddress VARCHAR(30),
    city VARCHAR(20),
    province VARCHAR(20),
    postalCode VARCHAR(9),
    CONSTRAINT PRIMARY KEY (cusID)

);

DROP TABLE IF EXISTS employ;
CREATE TABLE IF NOT EXISTS employ(
    employID VARCHAR(10),
    employName VARCHAR(45),
    employTitle VARCHAR(45),
    employAddress VARCHAR(45),
    employPassWord VARCHAR(15),
    CONSTRAINT PRIMARY KEY (employID)

);

DESC customer;
DESC employ;

DROP TABLE  IF EXISTS `order`;
CREATE TABLE IF NOT EXISTS `order`(
        orderId VARCHAR(6),
        orderDate DATE,
        orderTime VARCHAR(15),
        cusID VARCHAR(6),
        employID VARCHAR(10),
        totalCost DOUBLE,
        CONSTRAINT PRIMARY KEY (orderId),
        CONSTRAINT FOREIGN KEY (cusID) REFERENCES customer(cusID) ON DELETE CASCADE ON UPDATE CASCADE,
        CONSTRAINT FOREIGN KEY (employID) REFERENCES employ(employID) ON DELETE CASCADE ON UPDATE CASCADE

);


SHOW TABLES;
DESC `order`;

DROP TABLE IF EXISTS item;
CREATE TABLE IF Not EXISTS item(
    itemCode VARCHAR(6),
    Description VARCHAR(50),
    PackSize VARCHAR(20),
    buyIngPrice DOUBLE,
    unitPrice DOUBLE,
    discount VARCHAR(15),
    qtyOnHand int,
    CONSTRAINT PRIMARY KEY(itemCode)

);
SHOW TABLES;

DROP TABLE  IF EXISTS orderDetail;
CREATE TABLE IF NOT EXISTS orderDetail(
    orderId VARCHAR(6),
    itemCode VARCHAR(6),
    orderQty int,
    itemDiscount DOUBLE,
    cost DOUBLE,
    itemProfit DOUBLE,
    CONSTRAINT PRIMARY KEY (orderId,itemCode),
    CONSTRAINT FOREIGN KEY (orderId) REFERENCES `order`(orderId) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (itemCode) REFERENCES item(itemCode) ON DELETE CASCADE ON UPDATE CASCADE

);

SHOW TABLES;































