/*==============================================================*/
/* Table: oauth_client_details                                  */
/*==============================================================*/
/*resource_id通过erureka接口获取*/
/*scope用来表示client_id的用途：web/api */
DROP TABLE IF EXISTS oauth_client_details;
CREATE TABLE oauth_client_details (
  client_id               NVARCHAR(255) NOT NULL PRIMARY KEY,
  resource_ids            NVARCHAR(256) NULL,
  client_secret           NVARCHAR(256) NULL,
  scope                   NVARCHAR(256) NULL,
  authorized_grant_types  NVARCHAR(256) NULL,
  web_server_redirect_uri NVARCHAR(256) NULL,
  authorities             NVARCHAR(256) NULL,
  access_token_validity   INT           NULL,
  refresh_token_validity  INT           NULL,
  additional_information  NVARCHAR(256) NULL,
  autoapprove             NVARCHAR(256) NULL,
  bind                    SMALLINT      DEFAULT '1'
);

/*==============================================================*/
/* Table: oauth_client_token                                    */
/*==============================================================*/
DROP TABLE IF EXISTS oauth_client_token;
CREATE TABLE oauth_client_token (
  token_id          NVARCHAR(256)   NULL,
  token             VARBINARY(8000) NULL,
  authentication_id NVARCHAR(255)   NOT NULL PRIMARY KEY,
  user_name         NVARCHAR(256)   NULL,
  client_id         NVARCHAR(256)   NULL
);

/*==============================================================*/
/* Table: oauth_access_token                                    */
/*==============================================================*/
DROP TABLE IF EXISTS oauth_access_token;
CREATE TABLE oauth_access_token (
  token_id          NVARCHAR(256)   NOT NULL PRIMARY KEY,
  token             VARBINARY(8000) NULL,
  authentication_id NVARCHAR(255)   NOT NULL,
  user_name         NVARCHAR(256)   NULL,
  client_id         NVARCHAR(256)   NULL,
  authentication    VARBINARY(8000) NULL,
  refresh_token     NVARCHAR(256)   NULL
);

/*==============================================================*/
/* Table: oauth_refresh_token                                   */
/*==============================================================*/
DROP TABLE IF EXISTS oauth_refresh_token;
CREATE TABLE oauth_refresh_token (
  token_id       NVARCHAR(256)   NULL,
  token          VARBINARY(8000) NULL,
  authentication VARBINARY(8000) NULL
);

/*==============================================================*/
/* Table: oauth_code                                            */
/*==============================================================*/
DROP TABLE IF EXISTS oauth_code;
CREATE TABLE oauth_code (
  code           NVARCHAR(256)   NULL,
  authentication VARBINARY(8000) NULL
);


/*==============================================================*/
/* Table: oauth_approvals                                       */
/*==============================================================*/
DROP TABLE IF EXISTS oauth_approvals;
CREATE TABLE oauth_approvals (
  userId         NVARCHAR(256) NULL,
  clientId       NVARCHAR(256) NULL,
  scope          NVARCHAR(256) NULL,
  status         NVARCHAR(10)  NULL,
  expiresAt      DATE          NULL,
  lastModifiedAt DATE          NULL
);

/*==============================================================*/
/* Table: customized oauth_client_details table                 */
/*==============================================================*/
create table ClientDetails (
    appId VARCHAR(256) PRIMARY KEY,
    resourceIds VARCHAR(256),
    appSecret VARCHAR(256),
    scope VARCHAR(256),
    grantTypes VARCHAR(256),
    redirectUrl VARCHAR(256),
    authorities VARCHAR(256),
    access_token_validity INTEGER,
    refresh_token_validity INTEGER,
    additionalInformation VARCHAR(4096),
    autoApproveScopes VARCHAR(256)
);