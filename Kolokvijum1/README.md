# Kolokvijum1 - Android projekat

## Kako otvoriti projekat

1. Otvori **Android Studio**
2. Klikni **File → Open**
3. Izaberi folder `Kolokvijum1`
4. Sačekaj da Gradle sinhronizuje projekat (moze potrajati nekoliko minuta)
5. Pokreni na emulatoru ili fizičkom uređaju

> **Napomena:** Kada Android Studio prvi put otvori projekat, automatski će preuzeti Gradle wrapper. Potrebna je internet konekcija.

## Struktura projekta

```
app/src/main/
├── java/com/example/kolokvijum1/
│   ├── MainActivity.java          - Toolbar, meni, prikaz fragmenta
│   ├── RecipeFragment.java        - RecyclerView, ImageView (GONE), dugme Dodaj
│   ├── Recipe.java                - Model podataka
│   ├── RecipeAdapter.java         - Adapter za RecyclerView
│   ├── RecipeAddedReceiver.java   - BroadcastReceiver (zbir vremena, Toast > 120 min)
│   └── CameraCheckService.java    - Servis (provera kamere svaki minut)
└── res/
    ├── layout/
    │   ├── activity_main.xml      - Toolbar + LinearLayout narandžasta pozadina
    │   ├── fragment_recipe.xml    - RecyclerView + ImageView + Dugme
    │   ├── item_recipe.xml        - Izgled jednog recepta u listi
    │   └── dialog_add_recipe.xml  - Forma za dodavanje recepta
    ├── menu/main_menu.xml         - Meni sa stavkom Recipe
    ├── values/strings.xml
    ├── values/colors.xml
    ├── values/themes.xml
    └── drawable/ic_food.xml       - Placeholder slika
```

## Implementirani zadaci

| # | Zadatak | Implementacija |
|---|---------|----------------|
| 1 | Toolbar + LinearLayout narandžasta | `activity_main.xml` |
| 2 | RecipeFragment | `RecipeFragment.java` |
| 3 | RecyclerView, dugme Dodaj, ImageView (GONE) | `fragment_recipe.xml` |
| 4 | Meni sa stavkom Recipe | `main_menu.xml` |
| 5 | Klik na Recipe → prikazuje fragment | `MainActivity.java` |
| 6 | Klik na Dodaj → otvara formu | `RecipeFragment.java` |
| 7 | Forma (naziv, vreme, Checkbox, potvrdi/odustani) | `dialog_add_recipe.xml` |
| 8 | BroadcastReceiver → zbir > 120 min → Toast | `RecipeAddedReceiver.java` |
| 9 | Servis svaki minut, provera kamere, prikaz ImageView | `CameraCheckService.java` |
