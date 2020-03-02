# polydroid

## Description
Projet Android réalisé en 3e année du cycle d'ingénieur à Polytech Nice

Cette application permet de déclarer des incidents intervenus sur le campus (vitre cassée, ampoule grillée, objet perdu...etc)

Le but du projet était de montrer la capacité d'utilisation de plusieurs technologies Android (intent, fragment, permissions, utilisation de différents layout, services, requêtes)

Plusieurs défauts existent sur l'application:

- L'esthétique de l'application n'était pas une priorité, ce projet était initialement destiné à une équipe de 3 personnes mais j'ai finalement dû le déveloper seul.

- La partie serveur de ce projet manque évidemment de sécurité, les requêtes SQL concatenent des valeurs sans vérifier leur contenu

## Technologies utilisées

Android (intent, fragment, permissions, utilisation de différents layout, services, requêtes)

Firebase

Node.js

## Comment essayer le projet

Démarrez le serveur présent dans le dossier server avec ```node index.js```

Compilez l'application présente dans le dossier app
