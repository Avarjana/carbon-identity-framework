CREATE TABLE IF NOT EXISTS IDN_CLAIM_DIALECT (
  ID INTEGER NOT NULL AUTO_INCREMENT,
  DIALECT_URI VARCHAR (255) NOT NULL,
  TENANT_ID INTEGER NOT NULL,
  PRIMARY KEY (ID),
  CONSTRAINT DIALECT_URI_CONSTRAINT UNIQUE (DIALECT_URI, TENANT_ID)
);

CREATE TABLE IF NOT EXISTS IDN_CLAIM (
  ID INTEGER NOT NULL AUTO_INCREMENT,
  DIALECT_ID INTEGER NOT NULL,
  CLAIM_URI VARCHAR (255) NOT NULL,
  TENANT_ID INTEGER NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (DIALECT_ID) REFERENCES IDN_CLAIM_DIALECT(ID) ON DELETE CASCADE,
  CONSTRAINT CLAIM_URI_CONSTRAINT UNIQUE (DIALECT_ID, CLAIM_URI, TENANT_ID)
);

CREATE TABLE IF NOT EXISTS IDN_CLAIM_MAPPED_ATTRIBUTE (
  ID INTEGER NOT NULL AUTO_INCREMENT,
  LOCAL_CLAIM_ID INTEGER,
  USER_STORE_DOMAIN_NAME VARCHAR (255) NOT NULL,
  ATTRIBUTE_NAME VARCHAR (255) NOT NULL,
  TENANT_ID INTEGER NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (LOCAL_CLAIM_ID) REFERENCES IDN_CLAIM(ID) ON DELETE CASCADE,
  CONSTRAINT USER_STORE_DOMAIN_CONSTRAINT UNIQUE (LOCAL_CLAIM_ID, USER_STORE_DOMAIN_NAME, TENANT_ID)
);

CREATE TABLE IF NOT EXISTS IDN_CLAIM_PROPERTY (
  ID INTEGER NOT NULL AUTO_INCREMENT,
  LOCAL_CLAIM_ID INTEGER,
  PROPERTY_NAME VARCHAR (255) NOT NULL,
  PROPERTY_VALUE VARCHAR (255) NOT NULL,
  TENANT_ID INTEGER NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (LOCAL_CLAIM_ID) REFERENCES IDN_CLAIM(ID) ON DELETE CASCADE,
  CONSTRAINT PROPERTY_NAME_CONSTRAINT UNIQUE (LOCAL_CLAIM_ID, PROPERTY_NAME, TENANT_ID)
);

CREATE TABLE IF NOT EXISTS IDN_CLAIM_MAPPING (
  ID INTEGER NOT NULL AUTO_INCREMENT,
  EXT_CLAIM_ID INTEGER NOT NULL,
  MAPPED_LOCAL_CLAIM_ID INTEGER NOT NULL,
  TENANT_ID INTEGER NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (EXT_CLAIM_ID) REFERENCES IDN_CLAIM(ID) ON DELETE CASCADE,
  FOREIGN KEY (MAPPED_LOCAL_CLAIM_ID) REFERENCES IDN_CLAIM(ID) ON DELETE CASCADE,
  CONSTRAINT EXT_TO_LOC_MAPPING_CONSTRN UNIQUE (EXT_CLAIM_ID, TENANT_ID)
);
