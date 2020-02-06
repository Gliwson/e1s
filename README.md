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
