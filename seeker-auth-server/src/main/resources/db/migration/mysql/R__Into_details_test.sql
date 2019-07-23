INSERT INTO oauth_client_details (client_id,resource_ids,client_secret,scope,authorized_grant_types,web_server_redirect_uri,authorities,access_token_validity,refresh_token_validity,additional_information,autoapprove,bind)
VALUES ('test', 'whyt-seeker,seeker-web,seeker-auth-server', '$2a$10$7PtegA6OsLb402nVZ1C4oOTFvgE42kfnBuDuSGY7JV5plnSH7BMRm', 'read,write', 'password', NULL, NULL, 100000, NULL, NULL, NULL,0);

INSERT INTO oauth_client_details (client_id,resource_ids,client_secret,scope,authorized_grant_types,web_server_redirect_uri,authorities,access_token_validity,refresh_token_validity,additional_information,autoapprove,bind)
VALUES ('seeker', 'whyt-seeker,seeker-web,seeker-auth-server', '$2a$10$7PtegA6OsLb402nVZ1C4oOTFvgE42kfnBuDuSGY7JV5plnSH7BMRm', 'read,write', 'client_credentials', NULL, 'ADMIN', 100000, NULL, NULL, NULL,0);

COMMIT;
