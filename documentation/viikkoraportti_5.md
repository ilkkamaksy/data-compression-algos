# Viikkoraportti 5

Tällä viikolla olen korjannut LZW-algoritmin tallennusta, tosin overheadin takia tallennettu tiedosto on pienillä syötteillä isompi kuin lähde. Suuremmilla syötteillä pakkaus näyttää toimivan hyvin, paitsi että jonkin tuntemattoman pisteen jälkeen dekoodattu teksti alkaa mennä sekaisin. Tämä testattu Mary Shelleyn Frankenstein ja Tolstoin Sota ja rauha -teoksilla. Ensimmäiset pari sataa sivua dekoodatusta tekstitä ovat ok, mutta sitten teksti sekoaa. En tiedä mistä on kyse, kuten normaalisti. Epäilen hajautustaulu-implementaatiotani, merkistökoodausta, epätarkkaa tallennusta, itse asiassa en luota mihinkään koko toteutuksessani. 

LZW-sähellyksen lisäksi olen yrittänyt keksiä, miten tallentaa Huffman -koodin puurakenne bitteinä jollakin järkevällä systeeemillä pakattuun tiedostoon. Toistaiseksi olen epäonnistunut. Suunnitelmani testaamisen laajentamisesta tällä viikolla menivät siis täysin pieleen, mutta jatketaan konttaamista taas ensi viikolla. 

## Käytetyt tunnit

Tällä viikolla olen käyttänyt noin 8 tuntia tähän työhön.