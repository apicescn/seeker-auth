INSERT INTO oauth_client_details (client_id,resource_ids,client_secret,scope,authorized_grant_types,web_server_redirect_uri,authorities,access_token_validity,refresh_token_validity,additional_information,autoapprove,bind)
VALUES ('test', 'whyt-seeker,seeker-web,seeker-auth-server', 'test', 'read,write', 'password', NULL, NULL, 100000, NULL, NULL, NULL,0);

INSERT INTO oauth_client_details (client_id,resource_ids,client_secret,scope,authorized_grant_types,web_server_redirect_uri,authorities,access_token_validity,refresh_token_validity,additional_information,autoapprove,bind)
VALUES ('admin', 'whyt-seeker,seeker-web,seeker-auth-server', '7a57a5a743894a0e', 'read,write', 'client_credentials', NULL, 'ADMIN', 100000, NULL, NULL, NULL,0);

COMMIT;
