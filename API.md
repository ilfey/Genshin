## Genshin jorney API

API работает на cockpit CMS, поэтому для начала ознакомитесь с его [документацией](https://getcockpit.com/documentation).

### Collections

+ charactersv2
+ dict
+ gacha

### Singletons

+ about

### Примеры

Выведет все поля, сортировка по id(по умолчанию):
```
https://sushicat.pp.ua/api/genshin/api/collections/get/charactersv2?token=a4191046104f8f3674f788e804c2d0
```

Выведет только 4 поля, редкие персонажи впереди, начинает с 1 элемента и отобразит только 36 штук(полезно для страниц):
```
https://sushicat.pp.ua/api/genshin/api/collections/get/charactersv2?sort[rarity]=-1&skip=0&limit=36&fields[name]=1&fields[nameeng]=1&fields[rarity]=1&fields[ico]=1&token=a4191046104f8f3674f788e804c2d0
```

Если хочешь посылать пост запросы читай [документацию](https://getcockpit.com/documentation).

### Авторизация (без любого доступа)

login: test
password: test123