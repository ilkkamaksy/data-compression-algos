# Viikkoraportti 1

Tällä viikolla olen pääasiassa etsinyt algoritmeja joita voisin työssäni toteuttaa ja tutkia. Muutaman päivän tiedonhaun jälkeen ajattelin keskittyä kuvankäsittelyyn, missä erityisesti sobel ja canny edge detection -algoritmit vaikuttivat kiinnostavilta. Nämä eivät kuitenkaan lopulta olleet kelvollisia tähän työhön, joten hylkäsin ajatuksen. Seuraavaksi sain idean toteuttaa ohjelman, joka tuottaa png- ja jpg-kuvista optimoituja WebP-formaatin kuvia. Tällainen kirjasto voisi olla hyödyllinen esimerkiksi Java Spring -kehykselle. Mutta lopulta tarkemman tutustumisen jälkeen tämä näytti tämän kurssin kannalta aivan liian työläältä. 

Tämän jälkeen olin hukasssa, kunnes lopulta noin 8 tuntia ennen viikkopalautuksen aikarajaa valitsin Huffmanin ja Lempel-Ziv-Welch -pakkausalgoritmien vertailun. Tämä tuntuu kiinnostavimmalta, sillä haluan ymmärtää paremmin kyseisten pakkausalgoritmien variantteja, kuten esimerkiksi LZ77-Huffmania, jota on sovellettu mm. WebP-pakkauksessa.

Tähän mennessä olen saanut aikaan suhteellisen vähän. Näkyvimpänä tuloksena on toistaiseksi tämä Gradle-projektirunko ja dokumentaatiotiedostot. Lisäksi olen tehnyt jonkin verran taustatutkimusta ymmärtääkseni miten valitsemani algoritmit toimivat, mitä tietorakenteita ne hyödyntävät ja miten tehokkaita ne ovat. Tässä ei toistaiseksi ole ollut kummoisiakaan haasteita.

Molemmat algoritmit vaikuttavat suhteellisen suoraviivaisilta toteuttaa, joten voi olla että lisään projektin aikana vertailuuun vielä jonkin kiinnostavan variantin, kuten esim. juuri edellä mainitsemani LZ77-Huffmanin. Toisaalta, tietorakenteiden toteuttaminen alusta asti voi osoittautua hyvinkin työlääksi, joten aika näyttää mihin resurssit riittävät.   

Seuraavaksi alan toteuttamaan sovellukselle alustavaa käyttöliittymää, joka helpottaa ja nopeuttaa kehitystä ja testauta. Kun käyttöliittymä on kelvollinen, aloitan toteuttamaan Huffmania Javan valmiisiin tietorakenteisiin nojautuen. Toivon, että seuraavan viikon aikana ehtisin myös käynnistämään myös LZW algoritmin toteutuksen.

## Käytetyt tunnit

Tällä viikolla olen käyttänyt noin 8 tuntia tähän työhön.