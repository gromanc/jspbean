select u, uc, c from UserCredential uc, User u, Credential c  where uc in elements(u.userCredentials) and c.password = 'welcome'