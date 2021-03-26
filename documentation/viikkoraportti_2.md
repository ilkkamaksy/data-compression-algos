# Viikkoraportti 2

Tällä viikolla olen pääasiassa keskittynyt käyttöliittymään ja JavaFX:n konfigurointiin. 

Aloitin ensin komentoriviin perustuvasta käyttöliittymästä, missä lähdetiedosto, kohde ja algoritmi määritellään yksinkertaisesti argumenteilla, mutta tulkitsin tämän olevan liian yksinkertainen tätä projektia varten. Päätinkin siis implementoida JavaFX-käyttöliittymän, mutta kirjaston lisääminen Gradle-projektiin osoittautui huomattavasti mutkikkaammaksi kuin Maven-projektiin. 

JavaFX:n Konfigurointiin kuluikin huomattavasti enemmän aikaa kuin odotin ja vasta useamman epäonnistuneen yrityksen jälkeen sain vihdoin luotua projektin uudelleen siten, että myös JavaFX toimi. Jälkeen päin ajatellen mietin jälleen kerran, että olisiko Electron ollut sittenkin järkevämpi käyttöliittymän alusta, sillä JavaFX on jämähtänyt jonnekin 2000-luvulle. Toisaalta tätä projektia varten JavaFX on ihan kelvollinen, sillä käyttöliittymä on todella yksinkertainen.

Olen edistynyt projektissa kuten suunnittelin, eli sovelluksella on toimiva käyttöliittymä ja lisäksi tiedostojen avaaminen ja tallentaminen toimii. Toisaalta yksikkötestaus on vielä alkutekijöissä, mutta en ole kuitenkaan huolissani tästä, sillä projekti on elänyt alkuvaiheessa hyvin paljon ja liian varhaiset yksikkötestit olisivat menneet joka tapauksessa roskiin. 

Alustavia yksikkötestejä rakentaessani tulin siihen tulokseen, että olisi järkevää välttää void-metodeja ja palauttaa kaikista julkisista metodeista jotakin, vaikka palautettuja arvoja ei missään lukisikaan. Ainakin tällä tavalla junit-testeissä on helpompi varmistua, että kukin metodi toimii kuten sen odotan toimivan ja että testit todella mittaavat sitä mitä oletan niiden mittaavan. 

Päätin aloittaa algoritmien toteutuksen LZW -algoritmista, mutta päätin lykätä implementaation ensi viikkoon. Käyn toimeen jo viikonloppuna, jotta pääsen hyvään vauhtiin ensi viikkoa ajatellen.


## Käytetyt tunnit

Tällä viikolla olen käyttänyt noin 9 tuntia tähän työhön.