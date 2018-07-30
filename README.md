1 git checkout -f master
2 git status
3 git branch -d test
4 git check out test
---------------------
git cherry-pick


 1. **Generating the Server Keystore:**
`keytool -genkeypair -alias secure-server -keyalg RSA -dname "CN=localhost,OU=d8,O=d8,L=Riga,S=Latvia,C=lv" -keypass secret -keystore server-keystore.jks -storepass secret`

 2. **Generating the Client Keystore:** 
`keytool -genkeypair -alias secure-client -keyalg RSA -dname "CN=client,OU=Inna,O=Inna,L=Riga,S=Latvia,C=lv" -keypass secret -keystore client-keystore.jks -storepass secret`

 3. **Import the supported client's public certificates intro the server truststore:**
  - **Export the client public certificate**: `keytool -exportcert -alias secure-client -file client-public.cer -keystore client-keystore.jks -storepass secret`
  - **Import it in the server truststore**: `keytool -importcert -keystore server-truststore.jks -alias clientcert -file client-public.cer -storepass secret`

 4. **Import the server's public certificate into the client truststores:**
   - **Export the server public certificate**: `keytool -exportcert -alias secure-server -file server-public.cer -keystore server-keystore.jks -storepass secret`
   - **Import it in the client truststore**: `keytool -importcert -keystore client-truststore.jks -alias servercert -file server-public.cer -storepass secret` 
  
browser: `keytool -importkeystore -srckeystore client-keystore.jks -destkeystore client.p12 -deststoretype PKCS12`