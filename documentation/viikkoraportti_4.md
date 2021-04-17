# Viikkoraportti 4

Tällä viikolla olen pääasiassa työskennellyt Huffman code -algoritmin parissa. Toteutukseni ei vielä perjantaina toiminut niin kuin sen pitäisi. Enkoodaus-dekoodaus -operaation tuloksena oli kyllä oikeat merkit, mutta ne olivat väärässä järjestyksessä ja kukin merkki esiintyi vain kerran vaikka toistuvia merkkejä syötteessä olisikin. 

Esimerkiksi merkkijonon "abc" tuloksena oli "bac" ja merkkijonon "test" taas "tes". Pidemmissä syötteissä merkit olivat enkoodaus-dekoodauksen jälkeen täysin sekaisin.

Kokeilin kaikenlaista säätöä, mutta mikään ei toiminut. Lauantaiaamuna sain algoritmin korjattua siten, että se toimii kuten pitääkin. Seuraavaksi alan tekemään yksikkötestejä Huffman-toteutukseeni.

Viikon toisena läpimurtona olen ymmärtänyt LZW-pakkausongelmani syyn, tai ainakin luulisin niin. Käytän kaikkien merkkien tallennukseen 4 tavua, joten tottakai tiedoston koko paisuu suureksi. Testasin tallentamalla 2 tavua per merkki, jolloin tulos on oikea ja tiedoston koko on pienempi kuin lähdetiedosto. En ole vielä tosin aloittanut korjaamaan tätä.

Ensi viikolla pyrin lisäämään kaikkien yksikkötestien kattavuutta siten, että myös ns. edge caset tulee otettua huomioon. 

## Käytetyt tunnit

Tällä viikolla olen käyttänyt noin 9 tuntia tähän työhön.