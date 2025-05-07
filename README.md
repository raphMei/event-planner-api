# EventPlanner

Une API REST de gestion d’événements et de réservations, sécurisée avec Spring Boot, JWT, et gestion de rôles.  
Projet full back-end orienté production, avec architecture propre et séparation claire des responsabilités.

---

## Technologies

- Java 17
- Spring Boot 3
- Spring Security (JWT)
- JPA (Hibernate)
- Base de données H2 (peut être branchée à PostgreSQL facilement)
- Maven

---

## Fonctionnalités

- Inscription & Connexion (JWT)
- Rôles : `USER`, `ADMIN`
- CRUD Événements
- Réservations avec vérification du nombre de places
- Sécurité stateless (JWT + filtre perso)
- Contrôle d'accès avec `@PreAuthorize`
- Validation des entrées avec `@Valid`
- DTOs pour toutes les entités
- Gestion d’erreurs via exceptions

---

## Authentification

- Les utilisateurs reçoivent un **token JWT** à la connexion
- Ce token doit être ajouté dans les headers pour accéder aux routes sécurisées

---

##  Endpoints principaux

<<<<<<< HEAD
### Authentification

- `POST /api/auth/register` – Inscription
- `POST /api/auth/login` – Connexion

### Utilisateurs

- `GET /api/users`
- `GET /api/users/{id}`
- `DELETE /api/users/{id}`

### Événements

- `GET /api/events` – public
- `POST /api/events` – **ADMIN only**
- `DELETE /api/events/{id}` – **ADMIN only**

### Réservations

- `GET /api/reservations`
- `POST /api/reservations` – réserver une place
- `DELETE /api/reservations/{id}`
=======
  ### Authentification

  - `POST /api/auth/register` – Inscription
  - `POST /api/auth/login` – Connexion

  ### Utilisateurs

  - `GET /api/users`
  - `GET /api/users/{id}`
  - `DELETE /api/users/{id}`

  ### Événements

  - `GET /api/events` – public
  - `POST /api/events` – **ADMIN only**
  - `DELETE /api/events/{id}` – **ADMIN only**

  ### Réservations

  - `GET /api/reservations`
  - `POST /api/reservations` – réserver une place
  - `DELETE /api/reservations/{id}`

---

##  Exemple de JSON pour inscription

{
"username": "raph",
"email": "raph@example.com",
"password": "securepass",
"role": "USER"
}

## Lancement de l'application

- mvn spring-boot:run

## Auteur

- Développé par Raphael Meimoun – Développeur backend en apprentissage, avec envie de casser du Java.


