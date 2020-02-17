# Zadanie polega na napisaniu serwisu REST, który będzie zwracał dane produktu oraz ilość wyświetleń danego produktu.
## Dane produktu które powinny zostać zwrócone:
* nazwa
* opis
* typ (MALE, FEMALE, KID)
* cena (cena powinna być wyliczana z uwzględnieniem rabatu)

## Ilość wyświetleń danego produktu (ilość odwołań do danego produktu za pośrednictwem REST API)

Rabaty liczone są w zależności od typu produktu.
W najbliższym czasie rabaty naliczane będą następująco:
* MALE, FEMALE - 5%
* KID - 10%

## 2ga część zadania.
W dotychczasowym rozwiązaniu należy zmienić metodę rabatowania. Od teraz rabat powinien być liczony następująco:
* jeśli cena produktu jest powyżej 2 000 zł naliczamy rabat 5%
* produkty w przedziale cenowym 500 zł - 1999 zł rabat 3%
