create table if not exists PRODUCTS
    (ID_PRODUCT serial primary key,
     CODE int,
     NAME_PRODUCT varchar(20),
     EXPIRATION varchar(10),
     CATEGORY varchar(20),
     isVISIBLE boolean,
     PRICE int);    
    


