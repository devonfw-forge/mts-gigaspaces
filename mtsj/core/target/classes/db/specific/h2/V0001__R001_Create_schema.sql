-- This is the SQL script for setting up the DDL for the h2 database
-- In a typical project you would only distinguish between main and test for flyway SQLs
-- However, in this sample application we provde support for multiple databases in parallel
-- You can simply choose the DB of your choice by providing -Pmysql, -Ppostgresql, ... in your maven build

CREATE SEQUENCE HIBERNATE_SEQUENCE START WITH 1000000;

-- *** Table ***
CREATE TABLE "Table" (
  id BIGINT NOT NULL AUTO_INCREMENT,
  modificationCounter INTEGER NOT NULL,
  seatsNumber INTEGER NOT NULL,
  CONSTRAINT PK_Table PRIMARY KEY(id)
);

-- *** UserRole ***
CREATE TABLE UserRole (
  id BIGINT NOT NULL AUTO_INCREMENT,
  modificationCounter INTEGER NOT NULL,
  name VARCHAR (255),
  active BOOLEAN,
  CONSTRAINT PK_UserRole PRIMARY KEY(id)
);

-- *** User ***
CREATE TABLE User (
  id BIGINT NOT NULL AUTO_INCREMENT,
  modificationCounter INTEGER NOT NULL,
  username VARCHAR (255) NULL,
  password VARCHAR (255) NULL,
  secret VARCHAR (255) NULL,
  twoFactorStatus BOOLEAN NULL DEFAULT ((0)),
  email VARCHAR (120) NULL,
  idRole BIGINT NOT NULL,
  CONSTRAINT PK_User PRIMARY KEY(id),
  CONSTRAINT PK_User_idRole FOREIGN KEY(idRole) REFERENCES UserRole(id) NOCHECK
);

-- *** Booking ***
CREATE TABLE Booking (
  id BIGINT NOT NULL AUTO_INCREMENT,
  modificationCounter INTEGER NOT NULL,
  idUser BIGINT,
  name VARCHAR (255) NOT NULL,
  bookingToken VARCHAR (255),
  comment VARCHAR (4000),
  email VARCHAR(255) NOT NULL,
  bookingDate TIMESTAMP NOT NULL,
  expirationDate TIMESTAMP,
  creationDate TIMESTAMP,
  canceled BOOLEAN NOT NULL DEFAULT ((0)) ,
  bookingType INTEGER,
  idTable BIGINT,
  idOrder BIGINT,
  assistants INTEGER,
  CONSTRAINT PK_Booking PRIMARY KEY(id),
  CONSTRAINT FK_Booking_idUser FOREIGN KEY(idUser) REFERENCES User(id) NOCHECK,
  CONSTRAINT FK_Booking_idTable FOREIGN KEY(idTable) REFERENCES "Table"(id) NOCHECK
);

-- *** InvitedGuest ***
CREATE TABLE InvitedGuest (
  id BIGINT NOT NULL AUTO_INCREMENT,
  modificationCounter INTEGER NOT NULL,
  idBooking BIGINT NOT NULL,
  guestToken VARCHAR (255),
  email VARCHAR (60),
  accepted BOOLEAN,
  modificationDate TIMESTAMP,
  idOrder BIGINT,
  CONSTRAINT PK_InvitedGuest PRIMARY KEY(id),
  CONSTRAINT FK_InvitedGuest_idBooking FOREIGN KEY(idBooking) REFERENCES Booking(id) NOCHECK
);

-- *** OrderDish ***
CREATE TABLE Orders (
  id BIGINT NOT NULL AUTO_INCREMENT,
  modificationCounter INTEGER NOT NULL,
  idBooking BIGINT NOT NULL,
  idInvitedGuest BIGINT,
  idHost BIGINT,
  CONSTRAINT PK_Order PRIMARY KEY(id),
  CONSTRAINT FK_Order_idBooking FOREIGN KEY(idBooking) REFERENCES Booking(id) NOCHECK,
  CONSTRAINT FK_Order_idInvitedGuest FOREIGN KEY(idInvitedGuest) REFERENCES InvitedGuest(id) NOCHECK
);

-- *** Category ***
CREATE TABLE Category (
  id BIGINT NOT NULL AUTO_INCREMENT,
  modificationCounter INTEGER NOT NULL,
  name VARCHAR (255),
  description VARCHAR (4000),
  showOrder INTEGER,
  CONSTRAINT PK_Category PRIMARY KEY(id)
);

-- *** Image ***
CREATE TABLE Image (
  id BIGINT NOT NULL AUTO_INCREMENT,
  modificationCounter INTEGER NOT NULL,
  name VARCHAR(255),
  content blob,
  contentType INTEGER,
  mimeType VARCHAR(255),
  CONSTRAINT PK_Image PRIMARY KEY(id)
);

-- *** Dish ***
CREATE TABLE Dish (
  id BIGINT NOT NULL AUTO_INCREMENT,
  modificationCounter INTEGER NOT NULL,
  name VARCHAR (255),
  description VARCHAR (4000),
  price DECIMAL (16,10),
  idImage BIGINT UNIQUE NOT NULL,
  CONSTRAINT PK_Dish PRIMARY KEY(id),
  CONSTRAINT FK_Dish_idImage FOREIGN KEY(idImage) REFERENCES Image(id) NOCHECK
);

-- *** DishCategory ***
CREATE TABLE DishCategory (
  id BIGINT NOT NULL AUTO_INCREMENT,
  modificationCounter INTEGER NOT NULL,
  idDish BIGINT NOT NULL,
  idCategory BIGINT NOT NULL,
  CONSTRAINT PK_DishCategory PRIMARY KEY(id),
  CONSTRAINT FK_DishCategory_idDish FOREIGN KEY(idDish) REFERENCES Dish(id) NOCHECK,
  CONSTRAINT FK_DishCategory_idCategory FOREIGN KEY(idCategory) REFERENCES Category(id) NOCHECK
);

-- *** Ingredient ***
CREATE TABLE Ingredient (
  id BIGINT NOT NULL AUTO_INCREMENT,
  modificationCщ0d�a0H�?��y��0�N�E>+���3�E�>��&�]6M��7���p��9�^(�R�>o37Mf�2�o���A��C�`���<ѿ���s���vV��?�SL?,r���A֘�zU�R��G{�$�	�� ��v��X���C���8�/ �ά�C��̷��ќ	g �S6{G�����!"[�_��ϣ����t��:��]� U�u�\:��2?JУ	�+H���@`��� &<�ktl?�VZ� 3BK���d /���!��I�:#���)B�.��q���.���O^77&?q�6ڂ��3�4����
&<h�l��o�#���J,QfR�8��W�h�n�:�����R�
��{I�?�~�8�jތr?���8;&�s�C�mH�D��S)�>�L�?����t�:n��/a��"�3���q#ڵ�= �� ���?I{ӎ�^V�wDM hG�~�Z�s$_{�����o�h�uugP��D��ϩ�q�*��� �\ܔ��,�"pRlm?�p�����b��?e�O�&��@�-�3[K����3��,�$V�����?��ւ{���]�SA��7�F>��<m?�q?�=�d�?\��cvCU2kn���&������qω*?�B�r��F̫wpx�7q�q  KǶi?�S��N�?�g)Tk/�@��^���^ٕ���~�'H���t a~�	�[�a$�?_�F������m�b��R?��E��b�9��?V`�Z�e�1Q�fT�x���A��r%��VeOr�"�a���)S�k�����j�P�?y�9�ϟ'�OE�a�?3�A�)\I��M7����SȌ;p����ז�j����$_d@lлl�;��m��j?�ˀp
g/G����$�;w�P�ٵ�#��51E*]���	>�kS��7Śe]u�n
�a�o:�i�A�9��?��a(�O�ˋ�yAh���[U_͸����?$�(��GV'N;��M�񈖫$e�Xri�DjBZV���p�c|�Ƕ|�SE�$�"H��)tUfD_?�}��`JA|2��?�[�T��ж!��F2����?��!���_����fgW? w�/��M�<ΡLO0Y��e ��r�?EE_�&���W"D��v
����ob�o,Je���s��ϥ=�K9b�	2��d��@�|��8���$P�JE��A�Ʈ�ZEy����.�}�Cl�!zB��w�)���]��>��f��l���mW"�S�u���<j?�?�Uq�?�°%�����y۷���M�s�}d�%��,��z$)i�>���l�;��$�?i%��ۆǑA��CZ,��Nm�z2�F6_'�[��'Hs�?�?QAr�����E�Rv?1Ë�����}�	?oA�8�'�wH�H�U_�a�p�7h�'�; ��W4|����g����cJ��
J'�t�<k �0]0��A6��_�N����-)��[��1ԥ�?|��D��`�?��a](S�գ��ӱ=�'V˧�1?uϬ?l�ծcX�}�)���8�k�)m^�'�r����=�?\H�l������(��3F]���uE @�n4�B��ghoT"t�P�}���+��h�<_�?��1��N�V�:�Ô;��?P���{5�$�Z�_~��C&>�����<�Y��h
,�P�	߳� ;q=s�t�����MDv;X�?5
\��e�����r���#A�r��IԼ��W��X�h<%%�;�j��جr430����N݅��`b�� �q�������w��&$�2�>`I1SZi,���c�9�6�[�n>M��T?[��M.$Xj:wni���;pzQ�Tz_���c��+fx�WP�j�W ��Y�;�|ޅ!y��; ݉ ��?�A�I�Ha��T������,���9m�t�5X?�'U���R�kX?���;�h̒�׾(jn ��|m���LM�C	�W�X�����}�j?�#k5Bڧ���G���9���S��@l�@�`GAP����D�1�W*$D�0�?u�^-�$�;�G�bMޫN>i�UT�[l���>U"�ʚ��v�S;���W O?�e�Wq�[�x�I�4p Ru�iS�IΪ��@���ǻ}"��۳u�I����&\+���QH�3��3�?�&�@]�Y��{�-��RH����?t(Bb
�e��?a���^��[�T�.��o�����L�L@�~�0Z;����'��n�B�ѓχ�����ԣ��^u`�僟�I��/�M؀Q?]O��߯���״Q�a�O}�0�?rA:U_��<O]ݷQ�1,�������!i0K`�����r�����z?��wXi�U��r��5|������?��m�jk�L�I�e��J���?�Ҡƴ �`?�&{	�