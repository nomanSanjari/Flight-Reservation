DROP DATABASE IF EXISTS AirlineCompany;
CREATE DATABASE AirlineCompany;
USE AirlineCompany;


-- Create User Table with UserType column
DROP TABLE IF EXISTS User;
CREATE TABLE User (
    UserID INT AUTO_INCREMENT PRIMARY KEY,
    UserName VARCHAR(50) NOT NULL,
    UserAddress VARCHAR(100),
    Email VARCHAR(100) NOT NULL,
    UserType VARCHAR(20) NOT NULL -- "Guest", "Registered", "FlightAttendant", "Admin", "AirlineAgent"
    -- Add other user-related fields as needed
);

-- Insert 20 users into User table
INSERT INTO User (UserID, UserName, UserAddress, Email, UserType)
VALUES
    (1, 'John Doe', '123 Main St', 'john.doe@example.com', 'Guest'),
    (2, 'Jane Smith', '456 Oak Ave', 'jane.smith@example.com', 'Registered'),
    (3, 'Bob Johnson', '789 Elm Blvd', 'bob.johnson@example.com', 'FlightAttendant'),
    (4, 'Alice Brown', '321 Pine Ln', 'alice.brown@example.com', 'Admin'),
    (5, 'Charlie Davis', '654 Maple Dr', 'charlie.davis@example.com', 'AirlineAgent'),
    (6, 'Eva White', '987 Cedar Rd', 'eva.white@example.com', 'Guest'),
    (7, 'Frank Black', '234 Birch St', 'frank.black@example.com', 'Registered'),
    (8, 'Grace Taylor', '567 Spruce Ave', 'grace.taylor@example.com', 'FlightAttendant'),
    (9, 'David Lee', '876 Fir Blvd', 'david.lee@example.com', 'Admin'),
    (10, 'Helen Miller', '109 Pine Ln', 'helen.miller@example.com', 'AirlineAgent'),
    (11, 'Ivan Garcia', '210 Elm Dr', 'ivan.garcia@example.com', 'Guest'),
    (12, 'Jessica Hall', '543 Oak Rd', 'jessica.hall@example.com', 'Registered'),
    (13, 'Kevin Wilson', '876 Maple St', 'kevin.wilson@example.com', 'FlightAttendant'),
    (14, 'Laura Turner', '987 Birch Ave', 'laura.turner@example.com', 'Admin'),
    (15, 'Michael Adams', '432 Cedar Blvd', 'michael.adams@example.com', 'AirlineAgent'),
    (16, 'Nancy Davis', '765 Spruce Ln', 'nancy.davis@example.com', 'Guest'),
    (17, 'Oliver White', '123 Fir Dr', 'oliver.white@example.com', 'Registered'),
    (18, 'Patricia Brown', '456 Pine Rd', 'patricia.brown@example.com', 'FlightAttendant'),
    (19, 'Quentin Tarantino', '789 Maple St', 'quentin.tarantino@example.com', 'Admin'),
    (20, 'Rachel Taylor', '321 Elm Ave', 'rachel.taylor@example.com', 'AirlineAgent');


-- Create GuestUser Table
DROP TABLE IF EXISTS GuestUser;
CREATE TABLE GuestUser (
    GuestUserID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT UNIQUE,
    -- Add other guest user-related fields as needed
    FOREIGN KEY (UserID) REFERENCES User(UserID) ON DELETE CASCADE
);

-- Insert data into GuestUser table based on User table
INSERT INTO GuestUser (UserID)
SELECT
    u.UserID
    FROM
    User u
WHERE
    u.UserType = 'Guest';


-- Create RegisteredUser Table with Password
DROP TABLE IF EXISTS RegisteredUser;
CREATE TABLE RegisteredUser (
    RegisteredUserID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT UNIQUE,
    Pwd_RegisteredUser VARCHAR(50) NOT NULL, -- You may adjust the size based on your security requirements
    CreditCardNumber VARCHAR(50),
    MonthlyPromotionNews BOOLEAN,
    AirportLoungeDiscount BOOLEAN,
    CompanionTicketCount INT DEFAULT 1,
    -- Add other registered user-related fields as needed
    FOREIGN KEY (UserID) REFERENCES User(UserID) ON DELETE CASCADE
);

-- Insert data into RegisteredUser table based on User table
INSERT INTO RegisteredUser (UserID, Pwd_RegisteredUser, CreditCardNumber, MonthlyPromotionNews, AirportLoungeDiscount, CompanionTicketCount)
SELECT
    u.UserID,
    'defaultPassword',  -- Set a default password or generate one based on your requirements
    'defaultCreditCard', -- Set a default credit card or leave it NULL
    true,               -- Set default values for MonthlyPromotionNews
    true,               -- Set default values for AirportLoungeDiscount
    1                   -- Set default values for CompanionTicketCount
FROM
    User u
WHERE
    u.UserType = 'Registered';



-- Create FlightAttendant Table with Password
DROP TABLE IF EXISTS FlightAttendant;
CREATE TABLE FlightAttendant (
    FlightAttendantID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT UNIQUE,
    Pwd_FlightAttendant VARCHAR(50) NOT NULL ,
    -- Add other flight attendant-related fields as needed
    FOREIGN KEY (UserID) REFERENCES User(UserID) ON DELETE CASCADE
);

-- Insert data into FlightAttendant table based on User table
INSERT INTO FlightAttendant (UserID, Pwd_FlightAttendant)
SELECT
    u.UserID,
    'defaultPassword'  -- Set a default password or generate one based on your requirements
FROM
    User u
WHERE
    u.UserType = 'FlightAttendant';

-- Create Admin Table with Password
DROP TABLE IF EXISTS AdminTable;
CREATE TABLE AdminTable (
    AdminID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT UNIQUE,
    Pwd_Admin VARCHAR(50) NOT NULL,
    -- Add other admin-related fields as needed
    FOREIGN KEY (UserID) REFERENCES User(UserID) ON DELETE CASCADE
);

-- Insert data into Admin table based on User table
INSERT INTO AdminTable (UserID, Pwd_Admin)
SELECT
    u.UserID,
    'defaultPassword'  -- Set a default password or generate one based on your requirements
FROM
    User u
WHERE
    u.UserType = 'Admin';

-- Create AirlineAgent Table with Password
DROP TABLE IF EXISTS AirlineAgent;
CREATE TABLE AirlineAgent (
    AirlineAgentID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT UNIQUE,
    Pwd_AirlineAgent VARCHAR(50) NOT NULL,
    -- Add other airline agent-related fields as needed
    FOREIGN KEY (UserID) REFERENCES User(UserID) ON DELETE CASCADE
);

-- Insert data into AirlineAgent table based on User table
INSERT INTO AirlineAgent (UserID, Pwd_AirlineAgent)
SELECT
    u.UserID,
    'defaultPassword'  -- Set a default password or generate one based on your requirements
FROM
    User u
WHERE
    u.UserType = 'AirlineAgent';

-- Create Aircraft Table
DROP TABLE IF EXISTS Aircraft;
CREATE TABLE Aircraft (
	AircraftID INT AUTO_INCREMENT PRIMARY KEY,
    AircraftModel VARCHAR(50) NOT NULL,
    NumEconomySeats INT,
    NumComfortSeats INT,
    NumBusinessSeats INT,
    InUse BOOLEAN
);

-- Populate Aircraft table
INSERT INTO Aircraft (AircraftID, AircraftModel, NumEconomySeats, NumComfortSeats, NumBusinessSeats, InUse)
VALUES
    (1, 'Boeing 747', 350, 50, 30, FALSE),
    (2, 'Airbus A320', 150, 20, 10, FALSE),
    (3, 'Boeing 787', 240, 35, 25, FALSE),
    (4, 'Airbus A330', 220, 40, 20, FALSE),
    (5, 'Boeing 737', 140, 15, 10, FALSE),
    (6, 'Airbus A350', 270, 45, 30, FALSE),
    (7, 'Boeing 777', 300, 50, 40, FALSE),
    (8, 'Airbus A380', 400, 60, 50, FALSE),
    (9, 'Boeing 767', 180, 30, 20, FALSE),
    (10, 'Airbus A319', 120, 10, 5, FALSE);


-- Create Flight Table with AircraftID as Foreign Key
DROP TABLE IF EXISTS Flight;
CREATE TABLE Flight (
    FlightID INT NOT NULL PRIMARY KEY,
    Origin VARCHAR(50) NOT NULL,
    Destination VARCHAR(50) NOT NULL,
    DepartureDate DATE NOT NULL,
    FlightNumber VARCHAR(50) NOT NULL,
    AircraftID INT, -- Foreign key referencing Aircraft table
    -- Add other flight-related fields as needed
    FOREIGN KEY (AircraftID) REFERENCES Aircraft(AircraftID)
);

-- Populate Flight table
INSERT INTO Flight (FlightID, Origin, Destination, DepartureDate, FlightNumber, AircraftID)
VALUES
    (1, 'New York', 'Los Angeles', '2023-12-01', 'NYLAX123', 1),
    (2, 'Los Angeles', 'Chicago', '2023-12-02', 'LACHI456', 2),
    (3, 'Chicago', 'Miami', '2023-12-03', 'CHMIA789', 3),
    (4, 'Miami', 'Seattle', '2023-12-04', 'MIASEA101', 4),
    (5, 'Seattle', 'Denver', '2023-12-05', 'SEADEN202', 5),
    (6, 'Denver', 'Houston', '2023-12-06', 'DENHOU303', 6),
    (7, 'Houston', 'New York', '2023-12-07', 'HOUNYK404', 7),
    (8, 'New York', 'Dallas', '2023-12-08', 'NYDAL505', 8),
    (9, 'Dallas', 'San Francisco', '2023-12-09', 'DALSFO606', 9),
    (10, 'San Francisco', 'Phoenix', '2023-12-10', 'SFOPHX707', 10);


-- Create Seat Table
DROP TABLE IF EXISTS Seat;
CREATE TABLE Seat (
    SeatNumber VARCHAR(3) NOT NULL,
    FlightID INT NOT NULL,
    SeatType VARCHAR(20) NOT NULL, -- Regular, Business-Class, Comfort, etc.
    Price DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (SeatNumber, FlightID), -- Composite key
    FOREIGN KEY (FlightID) REFERENCES Flight(FlightID)
);

INSERT INTO Seat (SeatNumber, FlightID, SeatType, Price) VALUES
    -- Flight 1
    ('A1', 1, 'Regular', 100.00),
    ('A2', 1, 'Regular', 100.00),
    ('A3', 1, 'Regular', 100.00),
    ('B1', 1, 'Comfort', 145.00),
    ('B2', 1, 'Comfort', 145.00),
    ('B3', 1, 'Comfort', 145.00),
    ('C1', 1, 'Business-Class', 225.00),
    ('C2', 1, 'Business-Class', 225.00),
    ('C3', 1, 'Business-Class', 225.00),
    ('D1', 1, 'Business-Class', 225.00),
    ('D2', 1, 'Business-Class', 225.00),
    ('D3', 1, 'Business-Class', 225.00),
	('A1', 2, 'Regular', 100.00),
    ('A2', 2, 'Regular', 100.00),
    ('A3', 2, 'Regular', 100.00),
    ('B1', 2, 'Comfort', 145.00),
    ('B2', 2, 'Comfort', 145.00),
    ('B3', 2, 'Comfort', 145.00),
    ('C1', 2, 'Business-Class', 225.00),
    ('C2', 2, 'Business-Class', 225.00),
    ('C3', 2, 'Business-Class', 225.00),
    ('D1', 2, 'Business-Class', 225.00),
    ('D2', 2, 'Business-Class', 225.00),
    ('D3', 2, 'Business-Class', 225.00),
	('A1', 3, 'Regular', 100.00),
    ('A2', 3, 'Regular', 100.00),
    ('A3', 3, 'Regular', 100.00),
    ('B1', 3, 'Comfort', 145.00),
    ('B2', 3, 'Comfort', 145.00),
    ('B3', 3, 'Comfort', 145.00),
    ('C1', 3, 'Business-Class', 225.00),
    ('C2', 3, 'Business-Class', 225.00),
    ('C3', 3, 'Business-Class', 225.00),
    ('D1', 3, 'Business-Class', 225.00),
    ('D2', 3, 'Business-Class', 225.00),
    ('D3', 3, 'Business-Class', 225.00),
	('A1', 4, 'Regular', 100.00),
    ('A2', 4, 'Regular', 100.00),
    ('A3', 4, 'Regular', 100.00),
    ('B1', 4, 'Comfort', 145.00),
    ('B2', 4, 'Comfort', 145.00),
    ('B3', 4, 'Comfort', 145.00),
    ('C1', 4, 'Business-Class', 225.00),
    ('C2', 4, 'Business-Class', 225.00),
    ('C3', 4, 'Business-Class', 225.00),
    ('D1', 4, 'Business-Class', 225.00),
    ('D2', 4, 'Business-Class', 225.00),
    ('D3', 4, 'Business-Class', 225.00),
	('A1', 5, 'Regular', 100.00),
    ('A2', 5, 'Regular', 100.00),
    ('A3', 5, 'Regular', 100.00),
    ('B1', 5, 'Comfort', 145.00),
    ('B2', 5, 'Comfort', 145.00),
    ('B3', 5, 'Comfort', 145.00),
    ('C1', 5, 'Business-Class', 225.00),
    ('C2', 5, 'Business-Class', 225.00),
    ('C3', 5, 'Business-Class', 225.00),
    ('D1', 5, 'Business-Class', 225.00),
    ('D2', 5, 'Business-Class', 225.00),
    ('D3', 5, 'Business-Class', 225.00),
    ('A1', 6, 'Regular', 100.00),
    ('A2', 6, 'Regular', 100.00),
    ('A3', 6, 'Regular', 100.00),
    ('B1', 6, 'Comfort', 145.00),
    ('B2', 6, 'Comfort', 145.00),
    ('B3', 6, 'Comfort', 145.00),
    ('C1', 6, 'Business-Class', 225.00),
    ('C2', 6, 'Business-Class', 225.00),
    ('C3', 6, 'Business-Class', 225.00),
    ('D1', 6, 'Business-Class', 225.00),
    ('D2', 6, 'Business-Class', 225.00),
    ('D3', 6, 'Business-Class', 225.00),
    ('A1', 7, 'Regular', 100.00),
    ('A2', 7, 'Regular', 100.00),
    ('A3', 7, 'Regular', 100.00),
    ('B1', 7, 'Comfort', 145.00),
    ('B2', 7, 'Comfort', 145.00),
    ('B3', 7, 'Comfort', 145.00),
    ('C1', 7, 'Business-Class', 225.00),
    ('C2', 7, 'Business-Class', 225.00),
    ('C3', 7, 'Business-Class', 225.00),
    ('D1', 7, 'Business-Class', 225.00),
    ('D2', 7, 'Business-Class', 225.00),
    ('D3', 7, 'Business-Class', 225.00),
	('A1', 8, 'Regular', 100.00),
    ('A2', 8, 'Regular', 100.00),
    ('A3', 8, 'Regular', 100.00),
    ('B1', 8, 'Comfort', 145.00),
    ('B2', 8, 'Comfort', 145.00),
    ('B3', 8, 'Comfort', 145.00),
    ('C1', 8, 'Business-Class', 225.00),
    ('C2', 8, 'Business-Class', 225.00),
    ('C3', 8, 'Business-Class', 225.00),
    ('D1', 8, 'Business-Class', 225.00),
    ('D2', 8, 'Business-Class', 225.00),
    ('D3', 8, 'Business-Class', 225.00),
	('A1', 9, 'Regular', 100.00),
    ('A2', 9, 'Regular', 100.00),
    ('A3', 9, 'Regular', 100.00),
    ('B1', 9, 'Comfort', 145.00),
    ('B2', 9, 'Comfort', 145.00),
    ('B3', 9, 'Comfort', 145.00),
    ('C1', 9, 'Business-Class', 225.00),
    ('C2', 9, 'Business-Class', 225.00),
    ('C3', 9, 'Business-Class', 225.00),
    ('D1', 9, 'Business-Class', 225.00),
    ('D2', 9, 'Business-Class', 225.00),
    ('D3', 9, 'Business-Class', 225.00),
	('A1', 10, 'Regular', 100.00),
    ('A2', 10, 'Regular', 100.00),
    ('A3', 10, 'Regular', 100.00),
    ('B1', 10, 'Comfort', 145.00),
    ('B2', 10, 'Comfort', 145.00),
    ('B3', 10, 'Comfort', 145.00),
    ('C1', 10, 'Business-Class', 225.00),
    ('C2', 10, 'Business-Class', 225.00),
    ('C3', 10, 'Business-Class', 225.00),
    ('D1', 10, 'Business-Class', 225.00),
    ('D2', 10, 'Business-Class', 225.00),
    ('D3', 10, 'Business-Class', 225.00)
    ;



-- Create Booking Table
DROP TABLE IF EXISTS Booking;
CREATE TABLE Booking (
    BookingID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT,
    FlightID INT,
    SeatNumber VARCHAR(3),
    InsuranceSelected BOOLEAN,
    PaymentAmount DECIMAL(10, 2) NOT NULL,
    IsCancelled BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (UserID) REFERENCES User(UserID),
    FOREIGN KEY (FlightID) REFERENCES Flight(FlightID),
    FOREIGN KEY (SeatNumber) REFERENCES Seat(SeatNumber)
);

-- Populate Booking table for Guest and Registered Users
INSERT INTO Booking (BookingID, UserID, FlightID, SeatNumber, InsuranceSelected, PaymentAmount, IsCancelled) VALUES
    -- Guest Users
    (1, 1, 1, 'A1', true, 100.00, false),   -- John Doe books seat A1 on Flight 1
    (2, 6, 2, 'B2', false, 145.00, false),  -- Eva White books seat B2 on Flight 2
    (3, 11, 3, 'C3', true, 225.00, false),  -- Ivan Garcia books seat C3 on Flight 3
    (4, 16, 4, 'D1', false, 225.00, false), -- Nancy Davis books seat D1 on Flight 4

    -- Registered Users
    (5, 2, 5, 'A2', true, 100.00, false),   -- Jane Smith books seat A2 on Flight 5
    (6, 7, 6, 'B3', false, 145.00, false),  -- Frank Black books seat B3 on Flight 6
    (7, 12, 7, 'C1', true, 225.00, false),  -- Jessica Hall books seat C1 on Flight 7
    (8, 17, 8, 'D2', false, 225.00, false)  -- Oliver White books seat D2 on Flight 8
    -- Add more rows as needed
;


-- Create Crew Table
DROP TABLE IF EXISTS Crew;
CREATE TABLE Crew (
    CrewID INT PRIMARY KEY,
    FlightID INT,
    CrewName VARCHAR(50) NOT NULL,
    -- Add other crew-related fields as needed
    FOREIGN KEY (FlightID) REFERENCES Flight(FlightID)
);

-- Populate Crew table with 10 rows
INSERT INTO Crew (CrewID, FlightID, CrewName)
VALUES
    (1, 1, 'Crew 1 - NYLAX123'),
    (2, 2, 'Crew 2 - LACHI456'),
    (3, 3, 'Crew 3 - CHMIA789'),
    (4, 4, 'Crew 4 - MIASEA101'),
    (5, 5, 'Crew 5 - SEADEN202'),
    (6, 6, 'Crew 6 - DENHOU303'),
    (7, 7, 'Crew 7 - HOUNYK404'),
    (8, 8, 'Crew 8 - NYDAL505'),
    (9, 9, 'Crew 9 - DALSFO606'),
    (10, 10, 'Crew 10 - SFOPHX707');

-- Create Promotions Tables
DROP TABLE IF EXISTS Promotions;
CREATE TABLE Promotions (
    UserID INT,
    PromoNewsSubscription BOOLEAN,
    CompanionTicketCount INT DEFAULT 1,
    -- Add other promotion-related fields as needed
    FOREIGN KEY (UserID) REFERENCES RegisteredUser(UserID)
);

-- Populate Promotions table based on RegisteredUser
INSERT INTO Promotions (UserID, PromoNewsSubscription, CompanionTicketCount)
VALUES
    (2, true, 2),    -- Jane Smith
    (7, true, 1),    -- Frank Black
    (12, false, 1),  -- Jessica Hall
    (17, true, 3)   -- Oliver White
    -- Add more rows as needed
    ;

-- Create AirportLoungeDiscount Table
DROP TABLE IF EXISTS AirportLoungeDiscount;
CREATE TABLE AirportLoungeDiscount (
    UserID INT,
    DiscountPercentage DECIMAL(5, 2),
    -- Add other airport lounge discount-related fields as needed
    FOREIGN KEY (UserID) REFERENCES RegisteredUser(UserID)
);

-- Populate AirportLoungeDiscount table based on RegisteredUser
INSERT INTO AirportLoungeDiscount (UserID, DiscountPercentage)
VALUES
    (2, 15.00),    -- Jane Smith
    (7, 10.00),    -- Frank Black
    (12, 5.00),    -- Jessica Hall
    (17, 12.50)   -- Oliver White
    -- Add more rows as needed
    ;