#  Mise en place d'oauth2

Dans cette section nous allons aborder la délégation d'authentification via oauth
- Dans une premier temps allons repartir sur le contenu de la `step0`.


# 1 Installation et configuration d'un serveur Keycloak
## 1.1 Démarrage d'une instance
  - Démarrer une instance KeyCloak comme suit: 
      ```bash
        docker run -p 8080:8080 -e KC_BOOTSTRAP_ADMIN_USERNAME=admin -e KC_BOOTSTRAP_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:26.1.0 start-dev
      ```
## 1.2 Configurer KeyCloak
  ## 1.2.1 Créer un nouveau realm
  <img src='./img/1_admin_auth.png' alt= "Auth Request Succeded" width="600" />
  <img src='./img/2_realm_creation_1.png' alt= "Auth Request Succeded" width="600" />
  <img src='./img/2_realm_creation_2.png' alt= "Auth Request Succeded" width="600" />

  ## 1.2.2 Créer un nouveau client d'authentification
  <img src='./img/3_client_creation_1.png' alt= "Auth Request Succeded" width="600" />
  <img src='./img/3_client_creation_2.png' alt= "Auth Request Succeded" width="600" />
  <img src='./img/3_client_creation_3.png' alt= "Auth Request Succeded" width="600" />
  <img src='./img/3_client_creation_4.png' alt= "Auth Request Succeded" width="600" />
  <img src='./img/3_client_creation_5.png' alt= "Auth Request Succeded" width="600" />

  ## 1.2.3 Créer un nouveau Role pour le client créé
  <img src='./img/4_role_creation_1.png' alt= "Auth Request Succeded" width="600" />
  <img src='./img/4_role_creation_2.png' alt= "Auth Request Succeded" width="600" />

  ## 1.2.4 Créer un nouveau Utilisateur
  <img src='./img/5_user_creation_1.png' alt= "Auth Request Succeded" width="600" />
  <img src='./img/5_user_creation_2.png' alt= "Auth Request Succeded" width="600" />
  <img src='./img/5_user_creation_3.png' alt= "Auth Request Succeded" width="600" />
  <img src='./img/5_user_creation_4.png' alt= "Auth Request Succeded" width="600" />
  <img src='./img/5_user_creation_5.png' alt= "Auth Request Succeded" width="600" />
  <img src='./img/5_user_creation_6.png' alt= "Auth Request Succeded" width="600" />

  ## 1.2.5 Récupération d'un token d'access JWT
  - Plus de détails sur la configuration de Keycloak sont disponibles ici https://www.keycloak.org/getting-started/getting-started-docker
  - Récupérer un token JWT valide en lançant la commande suivante:
    ```bash
          curl -d 'client_id=<Your Client Name>' -d 'client_secret= <Your Client Secret>' -d 'username=<Your User name>' -d 'password=<Your User password>' -d 'grant_type=password' 'http://localhost:8080/realms/realmtp/protocol/openid-connect/token'
    ```
  - Exemple de retour du serveur KeyCloak
    ```json
      {"access_token":"eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJKTEYtQk5WOHBsWmhUdzFoU3ZVeVdPMDVJQXkweDhKUjhWN1lSZEdRV180In0.eyJleHAiOjE3MzgwNzYyNTIsImlhdCI6MTczODA3NTk1MiwianRpIjoiZDBhZWY1OWItNjY1Ni00ZmYxLThkY2UtNTU1NzIwYjk5MjI5IiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL3JlYWxtcy9yZWFsbXRwIiwiYXVkIjoiYWNjb3VudCIsInN1YiI6ImUzODgwMzFlLWEyN2QtNDNlNy1hNTA3LWIxNjI0MDcxMWUyMCIsInR5cCI6IkJlYXJlciIsImF6cCI6Im15X3NiX2FwcCIsInNpZCI6IjM5MzkxZGM4LTAxZGItNGEwZi04NTViLTU5ZGM1YWFhZTc2OSIsImFjciI6IjEiLCJhbGxvd2VkLW9yaWdpbnMiOlsiaHR0cDovL2xvY2FsaG9zdDo4MDg4Il0sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJkZWZhdWx0LXJvbGVzLXJlYWxtdHAiLCJvZmZsaW5lX2FjY2VzcyIsInVtYV9hdXRob3JpemF0aW9uIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJlbWFpbCBwcm9maWxlIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJuYW1lIjoidXNlcl90cF9mbiB1c2VyX3RwX2xuIiwicHJlZmVycmVkX3VzZXJuYW1lIjoidXNlcl90cCIsImdpdmVuX25hbWUiOiJ1c2VyX3RwX2ZuIiwiZmFtaWx5X25hbWUiOiJ1c2VyX3RwX2xuIiwiZW1haWwiOiJ1c2VyX3RwQGNtcC5mciJ9.haESeXgEYyuoIhnkPetHKIigi_EBgIp5HiG2hk957ttNJvoVQESOWGle3yH8Bszk2NLPnOZTlNEw2RtzHNCts8QIbXugsrBcCzBZn1exPaRfaJTjKGRJUIX0iQyreIh91NmUUCBXi0IfkxkWHeCewKRJXiW3jkVmYE7o-MfPcpPoxV1UfKEwQhNQbXiZJVagOUB2cw4aOjmOuwGSENnuZBxUeSzV4B__oUa2K3s7ENRWzu__NevSgkDHdDpqcMrGDNboS8dhIRVmJ4o8TLx9_yzkaLxQ9geD56ZAkQA4HHaeAQ-mY1ehHahH2-apbg_A_UVGIZ4jHeQglwkHtBmsDw","expires_in":300,"refresh_expires_in":1800,"refresh_token":"eyJhbGciOiJIUzUxMiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJmZTgyMzNjMi0wMDFlLTQ1ZDgtYjA3Ny0xMTM0NGYyNDg1YTEifQ.eyJleHAiOjE3MzgwNzc3NTIsImlhdCI6MTczODA3NTk1MiwianRpIjoiMDZiMWJhZDItNjNkMS00ODAxLWFlOWItODZiMjVmZTM3ZGU3IiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL3JlYWxtcy9yZWFsbXRwIiwiYXVkIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL3JlYWxtcy9yZWFsbXRwIiwic3ViIjoiZTM4ODAzMWUtYTI3ZC00M2U3LWE1MDctYjE2MjQwNzExZTIwIiwidHlwIjoiUmVmcmVzaCIsImF6cCI6Im15X3NiX2FwcCIsInNpZCI6IjM5MzkxZGM4LTAxZGItNGEwZi04NTViLTU5ZGM1YWFhZTc2OSIsInNjb3BlIjoiYmFzaWMgd2ViLW9yaWdpbnMgZW1haWwgcm9sZXMgcHJvZmlsZSBhY3IifQ.f4CtlItmFf71uQWUjTpCOjmofpuMFM2vv2cpEC7ftkFg37xYrCdE6zyfAqOF1k0Yw8PDxzlnKSpXDT23jraeeA","token_type":"Bearer","not-before-policy":0,"session_state":"39391dc8-01db-4a0f-855b-59dc5aaae769","scope":"email profile"}
    ```
# 2 Mise à jour des dépendances
- Ajouter la dépendance suivante dans votre `pom.xml`

  ```xml
    ...
      <!-- Add dependency to add security  -->
          <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-security</artifactId>
          </dependency>
          <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-oauth2-resource-server</artifactId>
          </dependency>
    ...
  ```
    - Explications
      - `spring-boot-starter-security` permet d'importer le starter spring security et tous les outils attenants
      - `spring-boot-starter-oauth2-resource-server` permet d'importer les ressources nécessaires pour l'oAuth

# 3 Première Configuration simple
  - Dans le package `com.security.app` créer le package `config`
  - Dans le package `com.security.app.config` créer la classe `SecurityConfig` comme suit:
  ```java
    import ...
    @Configuration
    public class SecurityConfig {
    
      @Bean
      public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/hero/**").authenticated()
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer.jwt(Customizer.withDefaults()));
        return http.build();
      }
    }
  ```
 - Explications:
   - `.requestMatchers("/hero/**").authenticated()`: Toutes les requètes portant sur le schéma d'url `/hero/**` nécessitent d'être authentifiées
   - `.anyRequest().authenticated()`: Toutes les autres requètes nécessitent une authentification
   - `.oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer.jwt(Customizer.withDefaults()));`: Définit la configuration par default de l'oauth2. Spring security ira chercher les paramètres nécessaires dans le fichier `application.properties`
 - Modifier le fichier `application.properties` commme suit:
   ```yaml
     # Logging configuration
     logging.level.org.springframework.security=trace
    
     # Keycloak configuration for OAuth2 client
     spring.security.oauth2.client.registration.keycloak.client-id=my_sb_app
     spring.security.oauth2.client.registration.keycloak.scope=openid
    
     #Refers to the Keycloak realm that handles authentication.
     spring.security.oauth2.client.provider.keycloak.issuer-uri=http://localhost:8080/realms/realmtp
    
     # Use to identify the client on the oAuth server
     spring.security.oauth2.client.registration.keycloak.client-secret=X3dxU5rOm6k4LIPqB5P3nuNAVr2lT9Ty
    
     # Keycloak configuration for resource server
     spring.security.oauth2.resourceserver.jwt.issuer-uri=http://localhost:8080/realms/realmtp
   ```
   - Avec votre outils de requêtes HTTP (e.g Postman) essayer de vous connecter à l'url  `http://localhost:8080/realms` en http GET
   - Sans token le serveur vous rejette (e.g return code 401)
   - Avec votre outils de requêtes HTTP (e.g Postman) effectuer une demande de token
     <img src='./img/oauth_1_postman_get_token.png' alt= "Auth Request Succeded" width="600" />
     <img src='./img/oauth_2_postman_get_token.png' alt= "Auth Request Succeded" width="600" />
   - Copier la valeur du `access-token` et ajouter ce token dans le champ `authorization` de votre requête HTTP (mode `Bearer`)
     <img src='./img/oauth_3_postman_pass_token.png' alt= "Auth Request Succeded" width="600" />
   - Vérifier que vous avez bien accès à l'url demandée

# 4 Redirection du login
- Afin de faciliter l'usage de l'oauth2, nous allons rediriger le client sur la page de login du serveur d'authorisation si ce dernier ne possède pas de token
- Ajouter la dépendance suivante dans votre `pom.xml`

  ```xml
    ...
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-oauth2-client</artifactId>
		</dependency>
    ...
  ```
  - Les fonctionnalités du client OAuth 2.0 prennent en charge le rôle du client tel qu'il est défini dans le cadre d'autorisation OAuth 2.0. (pour plus d'information https://docs.spring.io/spring-security/reference/reactive/oauth2/client/index.html)
  - Modifier le fichier `application.properties` comme suit:
     ```yaml
        ...
          # Specifies where Keycloak sends the user after login (back to the app).
          spring.security.oauth2.client.registration.keycloak.redirect-uri={baseUrl}/login/oauth2/code/{registrationId}
        ...
     ```
  - Modifier la classe `SecurityConfig` comme suit pour permettre une redirection du login.
    ```java
      import ...
        @Configuration
        public class SecurityConfig {
        
          @Bean
          public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    .authorizeHttpRequests(authorize -> authorize
                            .requestMatchers("/hero/**").authenticated()
                            .anyRequest().authenticated()
                    )
                    .oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer.jwt(Customizer.withDefaults()))
                    .oauth2Login(withDefaults());
            return http.build();
          }
        }
    ```
    - Explications 
      - `.oauth2Login(withDefaults());`: permet d'activer la redirection de l'authentification en cas de non réception de token JWT ou de JWT invalide
    - Recompiler votre application et redémarrer.
    - Dans un navigateur web enter l'URL suivante: `http:\\localhost:8088\hero`, si vous n'avez pas de token valide le résultat suivant devrait apparaitre:
      <img src='./img/oauth_4_login_keycloak.png' alt= "Auth Request Succeded" width="600" />
    - Une fois les bons crédentials rentrés ou avez accès à l'URL demandée

# 4 Lecture des Roles
- Dans cette partie nous allons modifiée notre configuration afin de mettre en place des règles d'accès basées sur les roles utilisateurs
- Dans `com.security.app` créer le package `auth.controller`
- Dans `com.security.app.auth.controller` créer la classe `JwtAuthConverter` comme suit:
```java
    @Component
    public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {

      @Value("${spring.security.oauth2.client.registration.keycloak.client-id}")
      private String oAuthClientName;

      private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

      @Override
      public AbstractAuthenticationToken convert(Jwt jwt) {
          Collection<GrantedAuthority> authorities = Stream.concat(
                  jwtGrantedAuthoritiesConverter.convert(jwt).stream(),
                  extractRoles(jwt).stream()
          ).collect(Collectors.toSet());

          return new JwtAuthenticationToken(jwt, authorities);
      }

      private Collection<? extends GrantedAuthority> extractRoles(Jwt jwt) {
          Set<String> roles = new HashSet<>();

          // Extract roles from realm_access
          Map<String, Object> realmAccess = jwt.getClaim("realm_access");
          if (realmAccess != null && realmAccess.containsKey("roles")) {
              roles.addAll((Collection<? extends String>) realmAccess.get("roles"));
          }

          // Extract roles from resource_access.demo
          Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
          if (resourceAccess != null && resourceAccess.containsKey(oAuthClientName)) {
              Map<String, Object> demoAccess = (Map<String, Object>) resourceAccess.get(oAuthClientName);
              if (demoAccess != null && demoAccess.containsKey("roles")) {
                  roles.addAll((Collection<? extends String>) demoAccess.get("roles"));
              }
          }

          // Debugging extracted roles
          System.out.println("Extracted roles: " + roles);

          return roles.stream()
                  .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
                  .collect(Collectors.toSet());
      }
    }
```
  - Explications
    - `...implements Converter<Jwt, AbstractAuthenticationToken>`: offre les interfaces permettants de transformer un token JWT en AbstractAuthenticationToken. Cela permet de convertir le JWT en objet Spring Security qui pourra être utilisé dans la chaine d'authentification et d'authorisation de Spring.
    - ```java
        private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
      ```
      - Crée une instance d'un objet permettant d'extraire un ensemble de GrantedAuthority(roles attribués au token) d'un token JWT brut

    - `jwt.getClaim("resource_access");` permet de récupérer toutes les ressources complémentaires telles que les information relatives aux clients (`(Map<String, Object>) resourceAccess.get(oAuthClientName)`) et ses roles associés `demoAccess.containsKey("roles")`
    - `new SimpleGrantedAuthority("ROLE_" + role.toUpperCase())`: une fois la liste des roles extraite du JWT, chacun des rôles est transformé en `SimpleGrantedAuthority` qui sera utilisée pour appliquer les droits sur les différents end points, entre autres, de l'application.


  - Modifier le fichier SecurityConfig comme suit:
    ```java
      @Configuration
      public class SecurityConfig {
          @Autowired
          JwtAuthConverter authConverter;

          @Bean
          public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
              http
                      .authorizeHttpRequests(authorize -> authorize
                              .requestMatchers(HttpMethod.GET,"/hero/**").authenticated()
                              .requestMatchers(HttpMethod.POST,"/hero/**").hasRole("ADMIN")
                              .anyRequest().authenticated()
                      )
                      .oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer.jwt(Customizer.withDefaults()))


                      //Enable Login redirection to oauth server
                      .oauth2Login(withDefaults())
                      //Use to extract Role from JWT
                      .oauth2ResourceServer(oauth2 -> oauth2
                              .jwt(jwt -> jwt
                                      .jwtAuthenticationConverter(authConverter)
                              )
                      );
              return http.build();
          }
      }
    
    ```
    - Explications:
      - `JwtAuthConverter authConverter;` injection de notre Converter
      - `.requestMatchers(antMatcher(HttpMethod.POST,"/hero/**")).hasRole("ADMIN")` : Ajout d'une URL protégée. Ici seul les utilisateurs possédants le rôle `ADMIN` pour effecter une requête http POST sur l'URL de type `/hero/**`.

      - ```java
        .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt
                              .jwtAuthenticationConverter(authConverter)
                              )
                      );
        ```

        - Transmet notre token JWT à notre converter pour extraire les rôles de ce dernier.
   
# Références
- Keycloak
  - https://www.keycloak.org/getting-started/getting-started-docker
- Spring and oAuth
  - https://medium.com/@Mo72/securing-spring-boot-applications-with-keycloak-a-step-by-step-guide-to-user-authentication-and-fa057b9de929
  - https://www.linkedin.com/pulse/spring-security-6-boot-3-keycloak-marwa-ali-62ttf
  - https://www.baeldung.com/spring-boot-keycloak
  - https://developers.redhat.com/articles/2023/07/24/how-integrate-spring-boot-3-spring-security-and-keycloak#test_the_application
  