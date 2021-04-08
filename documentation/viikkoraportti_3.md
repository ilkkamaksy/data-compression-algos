# Viikkoraportti 3

Tällä viikolla olen toteuttanut LZW-algoritmin datan pakkaukseen ja purkamiseen, sekä tietorakenteista listan ja hajautustaulun. Lisäsin myös CI-putken GitHub action -työkalulla, joka ajaa testit ja checkstylen push-operaation yhteydessä. Onnistuneiden testien jälkeen GH-action siirtää testikattavuus-raportin Codecov-palveluun. Eniten olen käyttänyt aikaa sen opiskeluun, miten Javalla voidaan tallentaa dataa tavuina tiedostoon. 

Pakattujen tiedostojen tallentaminen onkin ollut vaikein asia tällä viikolla. Aluksi tallensin pakatun datan tiedostoon suoraan Java-listana, jolloin "pakattu" tiedosto olikin huomattavasti suurempi kuin alkuperäinen tiedosto. Kun sitten vihdoin onnistuin tallentamaan datan tavuina, huomasin että tallennetut lzw-tiedostot ovat edelleen suurempia kuin alkuperäiset tekstitiedostot. Toisaalta, jos pakatun tiedoston tallentaa txt -formaatissa, tällöin tiedosto on pienempi kuin alkuperäinen. Pakattujen tiedostojen suurempi koko liittyy siis ilmeisesti tiedostopäätteeseen, mutta en tiedä vielä mitä tehdä asialle. Haluaisin lopulta kuitenkin erottaa pakatut tiedostot jollain tavalla pakkaamattomista, jotta käyttäjät ja ohjelmani kykenevät erottamaan ne toisistaan.

Ohjelmani on nyt LZW-algoritmin kannalta valmis ainakin siinä mielessä, että tekstin pakkaus ja purkaminen näyttää toimivan kuten pitääkin. Olen myös tyytyväinen ohjelman käytettävyyteen, jonka olen yrittänyt pitää mahdollisimman intuitiivisena. LZW:n optimointia ajatellen, [yhdessä artikkelissa](https://sites.google.com/site/datacompressionguide/lzw) mainittiin tietorakenne Ternary Search Tree, johon ajattelin tutustua jossakin vaiheessa. Kenties TST on toteuttamaani ketjuhajautustaulua tehokkaampi. 

Seuraavaksi alan toteuttamaan Huffman-algoritmia käyttäen Javan valmiita tietorakenteita.  

## Käytetyt tunnit

Tällä viikolla olen käyttänyt noin 16 tuntia tähän työhön.