--drop table PRODUCT if exists;
--create table PRODUCT (ID bigint identity primary key, 
						--NAME varchar(50) not null,
						--TYPE varchar(50) not null, 
                       -- PRICE decimal(8,2),
						--BRAND varchar(50) not null,
						--COLOR varchar(20) not null,
						--SIZE varchar(20) not null,
						--sku varchar(50) not null,
						--sellerid varchar(255) not null);
						
						
drop table ORDERS if exists;
create table ORDERS (ID int identity primary key, 
						TYPE varchar(50) not null, 
                        PRICE decimal(8,2),
						quantity int not null,
						ordertime timestamp  not null
						);							
                        