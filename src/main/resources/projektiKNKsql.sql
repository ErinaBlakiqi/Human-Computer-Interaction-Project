
create table Products(
	ProductId int auto_increment primary key,
    ProductName varchar(255) NOT NULL,
    SellerId int NOT NULL,
    Price int NOT NULL,
    Quantity int,
    CategoryId int,
    status ENUM('Active', 'Sold Out') NOT NULL,
    foreign key (SellerId) references Users(UserId),
    foreign key (CategoryId) references Categories(CategoryId)
);

create table Users(
	UserId int auto_increment primary key,
    FirstName varchar(255),
    LastName varchar(255),
    UserName varchar(255),
    Email varchar(255),
    Salt varchar(255),
    PasswordHash varchar(300),
    Roli varchar(255)
);

create table Categories(
	CategoryID INT AUTO_INCREMENT PRIMARY KEY,
    CName VARCHAR(255) NOT NULL UNIQUE
);

create table Orders(
	OrderId int PRIMARY KEY AUTO_INCREMENT,
    ProductId int,
    BuyerId int,
    TotalPrice int not null,
    PaymentMethod varchar(255),
    OrderStatus varchar(255) NOT NULL,
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (BuyerID) REFERENCES Users(UserID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);

create table Cart(
    CartId INT PRIMARY KEY AUTO_INCREMENT,
    UserId INT,
    ProductId INT,
    Quantity INT,
    TotalPrice int,
    FOREIGN KEY (UserId) REFERENCES Users(UserId),
    FOREIGN KEY (ProductId) REFERENCES Products(ProductId)
);


CREATE TABLE Profiles(
    ProfileId INT,
    Location VARCHAR(255),
    ContactNumber VARCHAR(15),
    Bio TEXT,
    FOREIGN KEY (ProfileId) REFERENCES Users(UserId)
);

    
