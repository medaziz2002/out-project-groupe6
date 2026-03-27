Product Management Application
-------------------------------
- Description du projet : 
Cette application web permet la gestion de produits organisés par catégories, en offrant un système complet permettant de créer, modifier, supprimer et consulter des produits ainsi que leurs catégories associées.

- Objectif
Mettre en pratique les concepts vus en cours :
Développement d’une API REST avec Spring Boot
Mise en place d’un frontend structuré avec Angular
Utilisation de GitHub avec des Pull Requests et les différents branches
Implémentation des tests unitaires, d'intégrationet d’une pipeline CI

------- Partie Backend---------
-------------------------------
Technologies : Java 21, Spring Boot, Spring Data JPA, PostgreSQL, Maven
Architecture : Le backend suit une architecture MVC avec une séparation claire des packages :
- entity : représentation des données
- repository : accès aux données
- service : logique métier
- controller : exposition des endpoints REST
- mapper : transformation des objets entre entity et DTO
- dto : objets de transfert de données entre frontend et backend
- config : configuration Spring Boot (ex : sécurité, CORS, etc.)
- ProductManagementApplication : classe principale pour lancer l’application Spring Boot

Fonctionnalités (CRUD) : 
Produits : Ajouter un produit - Modifier un produit - Supprimer un produit - Afficher la liste des produits - Rechercher des produits par catégorie
Catégories : Ajouter une catégorie - Modifier une catégorie - Supprimer une catégorie - Afficher les catégories

Tests : - Tests unitaires : JUnit (produits et catégories)
         - Tests d’intégration : ProductManagementTest


--------Partie Frontend----------
---------------------------------
Technologies : Angular, Node.js
Organisation du frontend : structure Angular classique avec : Un fichier .ts (logique) + Un fichier .html (vue)
Pages principales : Liste des produits - Liste des catégories - Formulaire d’ajout/modification de produit - Formulaire d’ajout/modification de catégorie -Recherche de produits par catégorie
Communication avec l’API : - Appels API testés via Postman
                          - Intégration frontend/backend via requêtes HTTP



--------Partie intégration continue----------
---------------------------------------------
Outil : Jenkins (pipeline multibranch)

Fonctionnement de la pipeline
Backend : Exécution des tests + Vérification du coverage
Frontend : Installation des dépendances + Build de l’application

La pipeline échoue si : Les tests backend échouent et le build frontend échoue


----------Partie Git & Organisation--------
-------------------------------------------
Convention de branches: 
- feature/back-end
- feature/front-end
- feature/ci-cd
- feature/docker
Des branches supplémentaires ont été créées par fonctionnalité puis supprimées après merge.

Pull Requests

Le projet a été développé via des Pull Requests permettant la revue du code et la validation avant intégration

Exemples :
PR Update Product (mise en place du fonctionnalité de modification d'un produit)
PR Unit tests  (mise en place des tests unitaires)




------------Utilisation de l’IA---------
----------------------------------------
Outils utilisés : 
Par Safae :  Claude, Recherches Google
- je l'ai utilisé dans un objectif d’apprentissage et de montée en compétence notamment sur des technologies nouvelles comme Spring Boot, Angular....et pour avoir également une assistance pour la configuration de Jenkins
Parmi les diifficultés que j'ai rencontré, il y'a mise en place des tests et la configuration de la pipeline Jenkins.

Par Mohamed : 
- J’ai utilisé l’intelligence artificielle pour la résolution des conflits de versions, ainsi que lors des merges après les pull requests.

Par Abdias : 
- J’ai utilisé l’intelligence artificielle pour configurer Jacoco et pour faciliter un peu la mise en place des tests unitaires et d’intégration.

