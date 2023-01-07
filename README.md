# Henri Potech

*Rémi BARDON, Simon SASSI*  
*6 janvier 2022*

## To-Do list

- [x] Les détails
  - Affichage d'une page de détails lors du clic sur un livre
- [x] Paysage (affichage différent) + panier
  - Grille avec nombre de colonnes qui s'adaptent à la largeur disponible
  - Panier sous forme d'une *bottom sheet*, présente sur les deux écrans
- [ ] Test (bonus)
  - Non réalisé
- [x] Calcul de la meilleure promotion (bonus)
  - Calcul pouvant être déclenché manuellement depuis le panier, qui affiche la réduction appliquée ainsi que le prix associé

## Choix techniques

Nous avons fait le choix d'utiliser Jetpack Compose afin de découvrir
une autre manière de développer des applications Android, non vue en cours.
Ce fut plus compliqué que prévu au départ,
mais nous avons finalement réussi à atteindre nos objectifs.

Nos réflexions architecturales ont été faites dans [`model.plantuml`](./model.plantuml).
Elles sont consultables sous forme d'images dans [`out/model/`](./out/model/).

## À améliorer

- Nous n'avons pas fini de transformer toutes les constantes "hard-codées" en valeurs dynamiques
- Certains fichiers pourraient être redécoupés en fragments plus petits
- Nous avons interprété la réduction "slice" comme "X € de réduction dès Y € d'achat" (non cumulable),
  mais après relecture de nos échanges par mail,
  ç'aurait du être "X € de réduction par tranches de Y €" (cumulable)
